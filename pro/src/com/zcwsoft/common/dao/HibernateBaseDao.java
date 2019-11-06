package com.zcwsoft.common.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zcwsoft.common.pub.util.DateUtil;
import com.zcwsoft.common.pub.util.Encrype;
import com.zcwsoft.common.pub.util.JackJson;
import com.zcwsoft.common.pub.util.StringUtil;
import com.zcwsoft.common.util.Json;
/**
 * 
*  <pre>    
* 类名称：BaseDao 
* 类描述：    
* 修改备注：   
* @version V1.0
* </pre>
 */
@Repository
public class HibernateBaseDao  {
	public Logger log =  LoggerFactory.getLogger(HibernateBaseDao.class);
	private static int  BATCH_SIZE=50;
	private ResultSet rs;
	private String maxValue;
	
	@Autowired
	private SessionFactory sessionfactory;
	
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}
	
    
	public void setSessionfactory(SessionFactory sessionfactory) {
	    Map<String, ClassMetadata> map = sessionfactory.getAllClassMetadata();
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            ClassMetadata cmd =map.get(key);
//            System.out.println(String.format("主键字段%s,主键类型%s", cmd.getIdentifierPropertyName(),
//                cmd.getIdentifierType().getName()
//                ));
//            System.out.println(cmd.getMappedClass().getSimpleName());// 对到映射的类
//          System.out.println(cmd.getEntityName());//对应实体完整类名
          //  System.out.println(cmd.toString());
        }
		this.sessionfactory = sessionfactory;
		log.info("注入sessionfactory");
	}
	
	public Session getSession() {
	    Session session = null;
        try
        {
            session = sessionfactory.getCurrentSession();
            log.info("只有在事务管理下，才能通过 getCurrentSession() 得到 session ，如果显示这个信息，则说明事务管理工作起作用了。");
        }
        catch(HibernateException ex)
        {           
            session = sessionfactory.openSession();     
            log.info("*************************************************************");
            log.info("*                                                           *");
            log.error("*  通过  openSession() 得到。事务管理没有起作用，需要检查配置。  *"+ex);
            log.info("*                                                           *");
            log.info("*************************************************************");
        }
        return session;
	}
	
	
	/**
	 * 保存一个对象
	 * @param obj
	 */
	public void save(Object obj) {
		Session s = getSession();
		s.save(obj);
		//s.flush();
	}
	/**
	 * 保存多个对象
	 * @param lis
	 */
	public void saveList(List<?> lis){
		Session session = getSession();
		for(int a=0;a<lis.size();a++){
			 session.save(lis.get(a));
			 if (a % BATCH_SIZE == 0) {  
               session.flush();  
               session.clear();  
           } 
		 }
	}
	/**
	 * 根据hql返回一个list  如果有参数刚hql用?来表示条件    
	 * @param hql
	 * @param args  从1开始赋值
	 * @return
	 */
	public List<?> queryForListByHql(String hql,Object...args) {
		List<?> lis =null;
		Session s = getSession();
		Query q = s.createQuery(hql);
		if (null!=args&&args.length>0) {
			for (int i = 0; i < args.length; i++) {
				q.setParameter(i, args[i]);
			}
		}
		lis = q.list();
		return lis;
	}
	/**
	 * sql查询
	 * @param sql
	 * @param args
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public List<Object[]> queryForListBySql(String sql,Object...args) {
		List<?> lis =null;
		Session s = getSession();
		Query q = s.createSQLQuery(sql);
		if (null!=args&&args.length>0) {
			for (int i = 0; i < args.length; i++) {
				q.setParameter(i, args[i]);
			}
		}
		lis = q.list();		
		return (List<Object[]>) lis;
	}
	  /**
     * 执行sql语句返回list【map【String,object】】
     * @param sql
     * @param objects
     * @return
     */
    @SuppressWarnings("unused")
    public List<?> queryForListMapBySql(String sql ,Object...objects){
        List<?> lis =null;
        Session s = getSession();
        Query q = s.createSQLQuery(sql);
        StackTraceElement[] ste = new Throwable().getStackTrace();
        StringBuffer CallStack = new StringBuffer();
		for (int i = 0; i < ste.length; i++) {
			CallStack.append(ste[i].toString() + " | ");
			if (i > 1)
				break;
		}
        if (null!=objects&&objects.length>0) {
            for (int i = 0; i < objects.length; i++) {
                q.setParameter(i, objects[i]);
            }
        }
		String press = Encrype.getMD5String();
		log.info(press + "程序执行路径：" + CallStack.toString());
		log.info(press + "程序执行脚本：" + sql);
		Date startDate = new Date();
        q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Date endDate = new Date();
        log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");    
        return lis = q.list();
    }
	/**
     * hql单一值查值
     * @param hql
     * @param args
     * @return
     */
    public Object uniqueQueryByHql(String hql,Object...args) {
        Query q = getSession().createQuery(hql);
        if (null!=args&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                q.setParameter(i, args[i]);
            }
        }
        return q.uniqueResult();
    }
    /***
     * sql 单一值查值
     * @param sql
     * @param args
     * @return
     */
    public Object uniqueQueryBySql(String sql,Map<String, Object> params) {
        Query q = getSession().createSQLQuery(sql);
        if (null!=params&&!params.isEmpty()) {
            Iterator<String> itera=params.keySet().iterator();
            while (itera.hasNext()) {
                String key=itera.next();
                q.setParameter(key, params.get(key));
                
            }
        }       
        return q.uniqueResult();
    }
    
    /**
     * sql 单一值查值
     * @param sql
     * @param args
     * @return
     */
    public Object uniqueQueryBySql(String sql,Object...args) {
        Query q = getSession().createSQLQuery(sql);
        if (null!=args&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                q.setParameter(i, args[i]);
            }
        }
        System.out.println(q.uniqueResult());
        return q.uniqueResult();
    }
    
    /**
     * 本地命名查询返回List<Map>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<?> queryForListByNameQuery(String key,Object...args)throws Exception{
        List<Map<String, Object>> lis = null;
        Session s = getSession();
        Query q = s.getNamedQuery(key); 
        if (null!=args&&args.length>0) {
            for (int i = 0; i < args.length; i++) {
                q.setParameter(i, args[i]);
            }
        }
        q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        lis = q.list();
        return lis;
    }
  
	/**
	 * 分页查询
	 * @param hql
	 * @param pageindex
	 * @param limit
	 * @param args
	 * @return
	 */
	public List<?> Pagination(String hql,int pageindex,int limit,Object...args) {
		List<?> lis =null;
		Session s = getSession();
		Query q = s.createQuery(hql);
		if (null!=args&&args.length>0) {
			for (int i = 0; i < args.length; i++) {
				q.setParameter(i, args[i]);
			}
		}
		q.setFirstResult((pageindex-1)*limit);
		q.setMaxResults(limit);
		lis = q.list();
		
		return lis;
	}
	/**
	 * 分页
	 * @param hql
	 * @param pageindex
	 * @param limit
	 * @param args
	 * @return
	 */
	public List<?> Pagination(String hql,int pageindex,int limit,Map<String, Object> args) {
        List<?> lis =null;
        Session s = getSession();
        Query q = s.createQuery(hql);
        if (null!=args) {
            Iterator<String> itera = args.keySet().iterator();
            while (itera.hasNext()) {
                String key =itera.next();
                Object value =args.get(key);
                q.setParameter(key, value);
            }
        }
        q.setFirstResult((pageindex-1)*limit);
        q.setMaxResults(limit);
        lis = q.list();
        return lis;
    }
	
	/**
	 * 修改一个实体
	 * @param obj
	 */
	public void update(Object obj) {
		getSession().update(obj);
		getSession().flush();
	}
	/**
	 * 删除一个实体
	 * @param obj
	 */
	public void delete(Object obj) {
		getSession().delete(obj);
	}
	/**
	 * 删除多个实体
	 * @param obj
	 */
	public void deleteList(List<?> lis) {
		Session session = getSession();
		for(int a=0;a<lis.size();a++){
			 session.delete(lis.get(a));
			 if (a % BATCH_SIZE == 0) {  
               session.flush();  
               session.clear();  
           } 
		}
	}
	
	/**
	 * 根据主键返回一个实体
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object get(Class<?> clazz,Object id) {
		return getSession().get(clazz, (Serializable) id);
	}
	/**
	 * 执行HQL
	 * @param hql
	 * @param obj
	 */
	public int executeHQL(String hql,Object...args) {
		Query q = getSession().createQuery(hql);
		if (null!=args&&args.length>0) {
			for (int i = 0; i < args.length; i++) {
				q.setParameter(i, args[i]);
			}
		}
		return q.executeUpdate();
	}
	/**
	 * 执行HQL
	 * @param hql
	 * @param map
	 * @return
	 */
    public int executeHQL(String hql,Map<String, Object> map) {
        Query q = getSession().createQuery(hql);
        if (null!=map) {
            Iterator<String> itera = map.keySet().iterator();
            while (itera.hasNext()) {
                String key =itera.next();
                Object value =map.get(key);
                if (value.getClass().getSimpleName().matches(".*(\\[\\])$")) {
                   Object[] array=(Object[]) value;
                    q.setParameterList(key,  array);
                }else if (value instanceof List<?>) {
                    List<?> lis =(List<?>) value;
                    q.setParameterList(key,  lis);
                }
                else{
                    q.setParameter(key, value);
                }
            }
        }
        return q.executeUpdate();
    }
	/**
	 * 执行SQL
	 * @param sql
	 * @param obj
	 */
	public int executeSQL(String sql,Object...args) {
		Query q = getSession().createSQLQuery(sql);
		if (null!=args&&args.length>0) {
			for (int i = 0; i < args.length; i++) {
				q.setParameter(i, args[i]);
			}
		}
		return q.executeUpdate();
	}
	
	/**
	* MINIUI分页,返回Json格式
	* @param sql
	* @param objects
	* @return
	*/
	public String pagingMiniUI(String sql, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields){
		List<?> lis =null;
		StackTraceElement[] ste = new Throwable().getStackTrace();
        StringBuffer CallStack = new StringBuffer();
		for (int i = 0; i < ste.length; i++) {
			CallStack.append(ste[i].toString() + " | ");
			if (i > 1)
				break;
		}
		String press = Encrype.getMD5String();
		log.info(press + "程序执行路径：" + CallStack.toString());
		log.info(press + "程序执行脚本：" + sql);
		Date startDate = new Date();
		Integer total = Integer.parseInt(uniqueQueryBySql("select count(*) from ("+sql+") a1") + "");
		String orderByStr = "";
		if (sortFields == null || sortFields.equals("")) {
			if (sortField.equals("")) {
				orderByStr = "pk desc";
			}else {
				orderByStr = ""+sortField+" "+sortOrder+"";
			}
		}else {
			JSONArray jsonArray = JSONArray.fromObject(sortFields);
			for (int i = 1; i <= jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i-1);
				if (i < jsonArray.size()) {
					orderByStr = orderByStr + jsonObject.getString("field") + " " + jsonObject.getString("dir") + ",";
				}else {
					orderByStr = orderByStr + jsonObject.getString("field") + " " + jsonObject.getString("dir");
				}
			}
		}
		String searchSql=" select ROW_NUMBER() over(order by "+orderByStr+") as rownumm,a1.* from ("+sql+") a1 ";
		searchSql=" select * from ( "+searchSql+") t1 "+
				" where rownumm>"+pageIndex*pageSize+" and rownumm<="+(pageIndex+1)*pageSize;
		Session s = getSession();
		Query q = s.createSQLQuery(searchSql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		lis = q.list();
		Date endDate = new Date();
	    log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n"); 
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("data", lis);
		resultMap.put("total", total);
		return Json.Encode(resultMap);
	}
	
	/**
	* MINIUI分页,返回第N条List格式的数据
	* @param sql
	* @param objects
	* @return
	*/
	public List<?> returnListPagingMiniUI(String sql, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields){
		List<?> lis =null;
		String orderByStr = "";
		if (sortFields == null || sortFields.equals("")) {
			if (sortField.equals("")) {
				orderByStr = "pk desc";
			}else {
				orderByStr = ""+sortField+" "+sortOrder+"";
			}
		}else {
			JSONArray jsonArray = JSONArray.fromObject(sortFields);
			for (int i = 1; i <= jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i-1);
				if (i < jsonArray.size()) {
					orderByStr = orderByStr + jsonObject.getString("field") + " " + jsonObject.getString("dir") + ",";
				}else {
					orderByStr = orderByStr + jsonObject.getString("field") + " " + jsonObject.getString("dir");
				}
			}
		}
		String searchSql=" select ROW_NUMBER() over(order by "+orderByStr+") as rownum,* from ("+sql+") as a1  ";
		searchSql=" select * from ( "+searchSql+")  as t1 "+
				" where rownum>"+pageIndex*pageSize+" and rownum<="+(pageIndex+1)*pageSize;
		Session s = getSession();
		Query q = s.createSQLQuery(searchSql);
		q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		lis = q.list();
		return lis;
	}
	
	/**
	* 统计数据条数
	* @param sql
	* @param objects
	* @return
	*/
	public Integer returnListPagingMiniUI(String sql){
		Integer total = (Integer) uniqueQueryBySql("select count(*) from ("+sql+") a1");
		return total;
	}
	
	/*以下为JJD共用方法*/

	/**
	 * 普通SQL查询
	 * 
	 * @param sql
	 * @return
	 */
	public List queryBySql(String sql) {
		final String sql1=sql;
		StackTraceElement[] ste = new Throwable().getStackTrace();
		List<Map> tempList = new ArrayList<Map>();
		try {
			ResultSet rs=this.getSession().doReturningWork(
	  	                new ReturningWork<ResultSet>() {
	  	                    @Override
	  	                    public ResultSet execute(Connection conn) throws SQLException {
	  	                       PreparedStatement preparedStatement=conn.prepareStatement(sql1);
	  	                       ResultSet resultSet=preparedStatement.executeQuery();
	  	                       return resultSet;
	  	                    }
	  	                }
	  	        );
			StringBuffer CallStack = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：" + sql);     
			Date startDate = new Date();
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue = "datetime"
							.equalsIgnoreCase(columeType) ? StringUtil
							.formatDateTime(rs.getString(i)) : rs.getString(i);
					map.put(columeName, columeValue);
				}
			 	tempList.add(map);
			}
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempList;
	}
	
	/**
	 * POJO保存
	 * 
	 * @param pojo
	 */
	public<T> void saveObj(T t) {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		StringBuffer CallStack = new StringBuffer();
		for (int i = 0; i < ste.length; i++) {
			CallStack.append(ste[i].toString() + " | ");
			if (i > 1)break;
		}
		ste = null;
		String press = Encrype.getMD5String();
		log.info(press + "程序执行路径：" + CallStack.toString());
		log.info(press + "保存：" + t.getClass().getName());
		Date startDate = new Date();
		try {
			getSession().save(t);
			//getSession().flush();
		} catch (RuntimeException re) {
			log.error("保存异常"+re.getMessage(), re);
			throw re;
		}
		Date endDate = new Date();
		log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
	}

	/**
	 * 更新POJO
	 * 
	 * @param pojo
	 * @return
	 */
	public Object updateObj(Object pojo) {
		StackTraceElement[] ste = new Throwable().getStackTrace();
		StringBuffer CallStack = new StringBuffer();
		for (int i = 0; i < ste.length; i++) {
			CallStack.append(ste[i].toString() + " | ");
			if (i > 1)break;
		}
		ste = null;
		String press = Encrype.getMD5String();
		log.info(press + "程序执行路径：" + CallStack.toString());
		log.info(press + "更新：" + pojo.getClass().getName());
		Date startDate = new Date();
		Object obj=null;
		try {
			obj= getSession().merge(pojo);
			//getSession().flush();
		} catch (RuntimeException re) {
			log.error("更新" + pojo.getClass().getName() + "异常", re);
			throw re;
		}
		Date endDate = new Date();
		log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
		return obj;
	}
	
	/**
	 * 更新
	 * 
	 * @param sql
	 * @return
	 */
	public int updateBySql(String sql) throws RuntimeException {
		int i = 0;
		try {
		    Query q=this.getSession().createSQLQuery(sql);
			i = q.executeUpdate();
			getSession().flush();
		} catch (Exception e) {
			throw new RuntimeException();
		} 
		return i;
	}
		
	/**
	 * 分页SQL查询
	 * 
	 * @param sql
	 * @return
	 */
	public List queryPageListBySql(String sql, int page, int limit) {
		List<Map> tempList = new ArrayList<Map>();
		try {
			ResultSet rs = this.getDataByCallableStatement(sql, page, limit);
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue = "datetime"
							.equalsIgnoreCase(columeType) ? StringUtil
							.formatDateTime(rs.getString(i)) : rs.getString(i);
					map.put(columeName, columeValue);
				}
				tempList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return tempList;
	}
	
	/**
	 * 分页SQL查询 数据结构为jqGrid结构
	 * 
	 * @param sql
	 * @return
	 */
	public String queryJqGridPageDataBySql(String sql, int page, int limit) {
		String totalpages = "0";
		String totalrecords = "0";		
		List<Map> tempList = new ArrayList<Map>();
		try {
			ResultSet rs =this.getDataByCallableStatement(sql, page, limit);
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue = "datetime"
							.equalsIgnoreCase(columeType) ? StringUtil
							.formatDateTime(rs.getString(i)) : rs.getString(i);
					map.put(columeName, columeValue);
				}
				if(totalpages.equals("0")&&totalrecords.equals("0")){
					totalpages = map.get("TotalPage").toString();
					totalrecords = map.get("totalCount").toString();
				}
				tempList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			db.closeCon();
//			session.close();
		}
		String json = JackJson.getBasetJsonData(tempList);
		StringBuffer bf = new StringBuffer();
	    bf.append("{");
	    bf.append("\"currpage\":"+String.valueOf(page)+",");
	    bf.append("\"totalpages\":"+totalpages+",");	    
	    bf.append("\"totalrecords\":"+totalrecords+",");
	    bf.append("\"rows\":");
	    bf.append(json); 
	    bf.append("}");
		return bf.toString();
	}
	
	/**
	 * 分页SQL查询 数据结构为Mini DataGrid结构
	 * 
	 * @param sql
	 * @return
	 */
	public String queryMiniDataGridPageDataBySql(String sql, int page, int limit) {
		String totalpages = "0";
		String totalrecords = "0";		
		List<Map> tempList = new ArrayList<Map>();
		try {
			ResultSet rs = this.getDataByCallableStatement(sql, page+1, limit);
			ResultSetMetaData lineInfo = rs.getMetaData();
			int columnCount = lineInfo.getColumnCount();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					String columeName = lineInfo.getColumnLabel(i);
					String columeType = lineInfo.getColumnTypeName(i);
					String columeValue = "datetime"
							.equalsIgnoreCase(columeType) ? StringUtil
							.formatDateTime(rs.getString(i)) : rs.getString(i);
					map.put(columeName, columeValue);
				}
				if(totalpages.equals("0")&&totalrecords.equals("0")){
					totalpages = map.get("TotalPage").toString();
					totalrecords = map.get("totalCount").toString();
				}
				tempList.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String json = JackJson.getBasetJsonData(tempList);
		StringBuffer bf = new StringBuffer();
	    bf.append("{");
	    bf.append("\"currpage\":"+String.valueOf(page)+",");
	    bf.append("\"pages\":"+totalpages+",");	    
	    bf.append("\"total\":"+totalrecords+",");
	    bf.append("\"data\":");
	    bf.append(json); 
	    bf.append("}");
		return bf.toString();
	}



	
	
	/*
	 * 调用获取主键存储过程 2010-4-23 高涛
	 */
	public String callIdGenratorProc2(String tableName) {
		String id = null;
		try {
			ResultSet rs = this.getDataByCallableStatement(tableName);
			if (rs != null && rs.next()) {
				id = rs.getString("id");
				//id=rs.getString("displaySize");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return id;
	}
	
	
	/*
	 * 调用获取编号存储过程 2012-04-08 关德辉
	 */
	public String callBillNoGenratorProc(String tableName) {
		String id = null;
		try {
			rs = this.getBillNoByCallableStatement(tableName);
			if (rs != null && rs.next()) {
				id = rs.getString("SBILLNO");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 调用获单据取编号存储过程
	 * 
	 * @return
	 */
	public String callBillNoProc(int domainId, int typeId) {
		String billNo = null;
		try {
			ResultSet rs = this.callStoreProc(domainId, typeId);
			if (rs != null && rs.next()) {
				billNo = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return billNo;
	}

	// 得编号
	public String getBillNo(String tableName, String billName) {
		String date=DateUtil.parseToString(new Date(), "yyyyMMdd");
		date=billName+"-"+date+"-";
		StringBuffer sb = new StringBuffer();
		sb.append("begin declare @id varchar(32),@maxNum int,");
		sb.append(" @sNO varchar(20) select @id=sCurSquence,@maxNum=imaxNum");
		sb.append(" from tbBillNo where sTblName ='"+tableName+"'");
		sb.append(" and sCurSquence like '%"+date+"%'");
		sb.append(" if(@id is null) begin update tbBillNo");
		sb.append(" set sCurSquence='"+date+"0001',imaxNum=1");
		sb.append(" where sTblName ='"+tableName+"'  select sCurSquence from tbBillNo where sTblName ='"+tableName+"' end");
		sb.append(" else begin set @maxNum=@maxNum+1 ");
		sb.append(" set @sNO = convert(char, @maxNum)");
		sb.append(" if char_length(@sNO)= 1 set @sNO = '000'+ @sNO");
		sb.append(" else if char_length(@sNO)= 2 set @sNO = '00'+ @sNO");
		sb.append(" else if char_length(@sNO)= 3 set @sNO = '0'+ @sNO");
		sb.append(" set @sNO='"+date+"'+@sNO");
		sb.append(" update tbBillNo set  sCurSquence=@sNO,");
		sb.append(" imaxNum=@maxNum where  sTblName ='"+tableName+"'");
		sb.append(" select sCurSquence from tbBillNo");
		sb.append(" where sTblName ='"+tableName+"' end end");
		List list=this.queryBySql(sb.toString());
		String id=null;
		if(list !=null&&list.size()>0){
			Map m=(Map)list.get(0);
			id=(String)m.get("sCurSquence");
		}
		return id;
	}
	
	//获取指标组编号
	public String getSitemcode(Long num,String sbeing){
		String sql=" SELECT dbo.PUB_FT_GETITEMCODE('"+sbeing+"',"+num+") SITEMCODE";
		String sitemcode="";
		List list= this.queryBySql(sql);	
		Iterator<Map> itList = list.iterator();
		Map map = itList.next();
		return map.get("SITEMCODE").toString();
	}
	
	
	//分页存储过程
		private synchronized ResultSet getDataByCallableStatement(String sql, int page, int limit) {
			final String sql1=sql;
			final int page1=page;
			final int limit1=limit;
			StackTraceElement[] ste = new Throwable().getStackTrace();
			StringBuffer CallStack = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			ste = null;
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：" + sql1);
			Date startDate = new Date();
			try {
				rs=this.getSession().doReturningWork(
		                new ReturningWork<ResultSet>() {
		                    @Override
		                    public ResultSet execute(Connection conn) throws SQLException {
		                    	CallableStatement callsta = conn.prepareCall("{call GetDataByPageEoms(?,?,?)}");
		            			callsta.setString(1, sql1);
		            			callsta.setInt(2, (page1 - 1) * limit1);
		            			callsta.setInt(3, limit1);
		            			ResultSet resultSet = callsta.executeQuery();
		            			return resultSet;
		                    }
		                }
		        );
			} catch (HibernateException e) {
				System.out.println(e.toString());
			} 
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
			return rs;
		}
		
		
		/*
		 * 获取主键存储过程
		 * 2010-4-23 关德辉*/
		private synchronized ResultSet getDataByCallableStatement(String sTableName) {
			final String sTableNameF=sTableName;
			StackTraceElement[] ste = new Throwable().getStackTrace();
			StringBuffer CallStack = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			ste = null;
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：call SP_GET_ID_EX(?)");
			Date startDate = new Date();
			try {
				rs=this.getSession().doReturningWork(
		                new ReturningWork<ResultSet>() {
		                    @Override
		                    public ResultSet execute(Connection conn) throws SQLException {
		                    	CallableStatement callsta = conn.prepareCall("{call SP_GET_ID_EX(?)}");
		            			callsta.setString(1, sTableNameF);
		            			ResultSet resultSet = callsta.executeQuery();
		            			return resultSet;
		                    }
		                }
		        );
			} catch (HibernateException e) {
				e.printStackTrace();
			} 
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
			return rs;
		}
		
		/*
		 * 获单据取编号存储过程
		 * 2012-4-08 */
		private synchronized ResultSet getBillNoByCallableStatement(String sTableName) {
			StackTraceElement[] ste = new Throwable().getStackTrace();
			StringBuffer CallStack = new StringBuffer();
			final String sTableNameF=sTableName;
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			ste = null;
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：call PUB_SP_GETBILLNO(?)");
			Date startDate = new Date();
			try {
				rs=this.getSession().doReturningWork(
		                new ReturningWork<ResultSet>() {
		                    @Override
		                    public ResultSet execute(Connection conn) throws SQLException {
		                    	CallableStatement callsta = conn.prepareCall("{call PUB_SP_GETBILLNO(?)}");
		            			callsta.setString(1, sTableNameF);
		            			ResultSet resultSet = callsta.executeQuery();
		            			return resultSet;
		                    }
		                }
		        );
			} catch (HibernateException e) {
				e.printStackTrace();
			} 
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
			return rs;
		}
		
		
		
		/**
		 * 功能：根据code获取编号的存储过程
		 * 作者：zcw
		 * 开发时间：2017年11月14日21:51:50
		 * @param code
		 * @return
		 */
		public String getSequenceNumber(String code) {
			String sequenceNum = null;
			try {
				sequenceNum = this.callSequenceProc(code);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return sequenceNum;
		}
		
		
		//根据code获取编号的存储过程
		public String callSequenceProc(final String code){
			StackTraceElement[] ste = new Throwable().getStackTrace();
			StringBuffer CallStack = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			ste = null;
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：call SEQUENCE_NUMBER(?,?)");
			Date startDate = new Date();
			try{
			    this.getSession().doWork(
		                new Work() {
		                    @Override
		                    public void execute(Connection conn) throws SQLException {
		            			CallableStatement callsta = conn.prepareCall("{call SEQUENCE_NUMBER(?,?)}");
		            			callsta.setString(1, code);
		            			callsta.registerOutParameter(2, java.sql.Types.VARCHAR);
		            			callsta.execute();
		            			maxValue = callsta.getString(2);
		                    }
		                }
		        );
			}catch(Exception e){
				log.equals(e);
				e.printStackTrace();
			}
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
			return maxValue;
		}
		
		
		//调用获单据取编号存储过程
		public synchronized ResultSet callStoreProc(final int domainId, final int typeId) {
			StackTraceElement[] ste = new Throwable().getStackTrace();
			StringBuffer CallStack = new StringBuffer();
			for (int i = 0; i < ste.length; i++) {
				CallStack.append(ste[i].toString() + " | ");
				if (i > 1)
					break;
			}
			ste = null;
			String press = Encrype.getMD5String();
			log.info(press + "程序执行路径：" + CallStack.toString());
			log.info(press + "程序执行脚本：call SP_tbBillNo(?,?)");
			Date startDate = new Date();
			try {
				    rs=this.getSession().doReturningWork(
			                new ReturningWork<ResultSet>() {
			                    @Override
			                    public ResultSet execute(Connection conn) throws SQLException {
			            			CallableStatement callsta = conn.prepareCall("{call SP_tbBillNo(?,?)}");
			            			callsta.setInt(1, typeId);
			            			callsta.setInt(2, domainId);
			            			ResultSet resultSet = callsta.executeQuery();
			            			return resultSet;
			                    }
			                }
			        );
			} catch (HibernateException e) {
				e.printStackTrace();
			} 
			Date endDate = new Date();
			log.info(press + "程序执行时间：" + StringUtil.getTimeInMillis(startDate, endDate) + "\n");
			return rs;
		}
		
		
		
}