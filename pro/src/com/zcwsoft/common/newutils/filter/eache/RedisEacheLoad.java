package com.zcwsoft.common.newutils.filter.eache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;
import com.zcwsoft.common.newutils.redisutils.core.RedisCore;
import com.zcwsoft.common.newutils.redisutils.core.RedisEache;

/**
 * 服务启动加载缓存类
 * 功能描述：服务启动加载，把数据库数据加入redis缓存
 * @author Administrator
 *
 */

public class RedisEacheLoad extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public CommonDao dao = null;
	public RedisEache redisEache = RedisEache.getInstance();
	
	public RedisEacheLoad() {
        super();
    }
	/**
	 * 初始化默认加载方法
	 */
    @SuppressWarnings("static-access")
	public void init() throws ServletException {
    	System.out.print("\n*********Redis_Eache_Delete Begin ********* ");
    	if(RedisCore.isNull()){
    		System.out.println(" failed *********：java.net.ConnectException: Connection refused: connect\n");
    		//return;
    	}else{
			int delN = redisEache.deleteAllEacheKey();
			System.out.println("success :num="+delN+"**************\n");
    	}
    	
    	long beginDate = System.currentTimeMillis();
    	System.out.print("\n*********Redis_Eache_Insert Begin *********");
    	try {
    		if(RedisCore.isNull()){
    		System.out.println(" failed *********：java.net.ConnectException: Connection refused: connect\n");
    		return;
    		}
			getCommonDao();//获取容器对象
			loadRedisEache();//执行数据插入缓存
			System.out.println(" seccess *********：【 "+(System.currentTimeMillis()-beginDate)/1000+"m】");
		} catch (IOException e) {
			System.out.println(" failed *********： 【"+(System.currentTimeMillis()-beginDate)/1000+"m】");
			e.printStackTrace();
		}
    	
    }
	 @SuppressWarnings("static-access")
	public boolean getCommonDao()throws ServletException, IOException {
		 while(true){
			 if(dao == null){
				 try{
					 ServletContext sc= this.getServletContext();
					WebApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
					dao = (CommonDao) ac1.getBean("CommonDaoImpl"); 
				 }catch(Exception e){
					 try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					 System.out.println(e);
				 }
			}else{
				break;
			}
		}
		/* new Thread(new Runnable() {
				@Override
				public void run() {
					
				}
			}).start();*/
		return true;
	 }
	

	@SuppressWarnings({ "static-access", "rawtypes" })
	private  void loadRedisEache() {
		redisEache.setDao(dao);
		List<Map> loadRedisTableName =null;
		List<String> tablesMap = new ArrayList<String>();//存放表名称，加入缓存
		try {
			 loadRedisTableName = dao.getListMapBySql("select data_source,table_name,database_name,joint_symbol from t_base_loadredis group by data_source,table_name,database_name,joint_symbol");
			 for (int i = 0; i < loadRedisTableName.size(); i++) 
			 {
				 String dataSource = (String)loadRedisTableName.get(i).get("DATA_SOURCE");
				 String jointSymbol = (String)loadRedisTableName.get(i).get("JOINT_SYMBOL");
				 String tableName = (String)loadRedisTableName.get(i).get("TABLE_NAME");
				 if(StringUtils.isNotBlank(dataSource))
				 {
					 if(StringUtils.isNotBlank(jointSymbol))
					 {
						 tableName = dataSource + jointSymbol + tableName;
					 }else{
						 tableName = dataSource + "_" + tableName;
					 }
				 }
				 tablesMap.add(tableName);
			}
			 
		} catch (Exception e1) {
			e1.printStackTrace();
		}//查询数据
		for (int i = 0; i < tablesMap.size(); i++) {
			String keyTableName = tablesMap.get(i);
			try {
				List<Map> rows = dao.getListMapBySql("select * from "+keyTableName);//查询数据
				redisEache.insertMultiDataToList(keyTableName, rows);//把表级所有数据放入缓存
				this.indexInterReids(keyTableName);//把表级索引关键数据放入缓存
			} catch (Exception e) {
				System.out.println("RedisEache add faild,tableName is by：*"+keyTableName+"*异常信息： "+e);
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * 缓存中索引存储格式 {索引名称={索引对应字段名称=[{索引对应字段的值=该值对应的主键id（必须是唯一）}]}}
	 * 示例单索引：{INDEX_T_S_USER_ID={USER_ID=[{VALUE=u_admin}]}}
	 * 示例组合索引：INDEX_T_S_USER_USER_ID={USER_ID=[{admin=u_admin}], PASSWORD=[{123=u_admin}]}
	 * @param tableName
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	private void indexInterReids(String tableName) throws Exception
	{
		 TableIndexUtils tbn = new TableIndexUtils(tableName,dao);
		 String primaryField = tbn.getUniquePrimaryField();//获取唯一主键字段
		 List<Map> rows = tbn.getListMap();
		 if(tbn.isHaveUniquePrimaryField() && tbn.isHaveIndex())
		 {
			 for (int i = 0; i < rows.size(); i++) 
			 {
				 Map im = new HashMap();
				String indexName =(String) rows.get(i).get("INDEX_NAME");
				String indexFieldName =(String) rows.get(i).get("COLUMN_NAME");
				if(indexFieldName.indexOf(tbn.subSy) != -1)
				{//联合索引（多字段组成的索引）
					String[] split = indexFieldName.split(tbn.subSy);//因为new TableIndexUtils()的时候已经把数据聚合使用tbn.subSy符号隔开
					for (int j = 0; j < split.length; j++) 
					{
						List<Map> appendMap = appendMap(primaryField, tbn.subSy,tableName, split[j]);
						im.put(split[j], appendMap);
					}
				}else{
					//单字段索引
					List<Map> appendMap = appendMap(primaryField, tbn.subSy, tableName, indexFieldName);
					im.put(indexFieldName, appendMap);
				}
				//redisEache.insertSingleMapDataToList(indexName.toLowerCase(), im);
				redisEache.insertSingleDataByKeyToMap(indexName, im);
			 }
		 }
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> appendMap(String primaryField,String subSy,String tableName,String indexFieldName) throws Exception
	{
		List<Map> lsm = new ArrayList<Map>();
		String sql = "select " + indexFieldName + " as key, replace(wm_concat(" + primaryField + "),',','" + subSy + "') as value  from " + tableName + " group by " + indexFieldName;
		List<Map> itrows = dao.getListMapBySql(sql);//查询数据
		for (int i = 0; i < itrows.size(); i++)
		{
			 Map itemMap = new HashMap();
			 itemMap.put(itrows.get(i).get("KEY"),itrows.get(i).get("VALUE"));
			 lsm.add(itemMap);
		}
		return lsm;
	}
	
}
