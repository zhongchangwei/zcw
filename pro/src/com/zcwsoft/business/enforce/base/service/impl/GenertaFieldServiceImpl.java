package com.zcwsoft.business.enforce.base.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.base.service.GenertaFieldService;
import com.zcwsoft.business.enforce.common.dao.impl.CommonDaoImpl;
import com.zcwsoft.common.enforce.bean.TBaseField;
import com.zcwsoft.common.enforce.bean.TSUser;
import com.zcwsoft.common.newutils.CommonManage;
import com.zcwsoft.common.newutils.MapUtils;
import com.zcwsoft.common.newutils.basebean.ReceParam;
import com.zcwsoft.common.newutils.generatemodelfile.GenerateJspLabel;
import com.zcwsoft.common.newutils.generatemodelfile.Freemarker;
import com.zcwsoft.common.util.BaseScanSqlCommand;
import com.zcwsoft.common.util.Json;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("genertaFieldService")
public class GenertaFieldServiceImpl implements GenertaFieldService{
	@Autowired
	private CommonDaoImpl dao;

    String resultPage ="";
	
	/**
	 * 作者：zcw
	 * 功能：加载列表
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String list(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "list");
		return CommonManage.getDesAttribute(sql);
	}

	/**
	 * 作者：zcw
	 * 功能：新增  修改（回显）弹出页面表单回显
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String formShowData(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "formShowData");
		sql=sql+" and t.id='"+rcep.getId()+"'";
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql.get(0));
	}
	/**
	 * 作者：zcw
	 * 功能：获取当前数据库用户的所有表
	 * 时间：2017-12-16
	 * @return [{id:?,name:?}]
	 */
	@Override
	public String getUserTable() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "genertaFieldSql.xml", "getUserTable");
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}

	/**
	 * 作者：zcw
	 * 功能：新增获取字段
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String getTableFieldByTableName(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "getTableFieldByTableName");
		//sql=sql.replace("#tableName", rcep.getTableName());
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}
	/**
	 * 作者：zcw
	 * 功能：获取字段根本表名，用于列表展示
	 * 时间：2017-11-24
	 * @return 
	 */
	public List<Map> getTableFieldByTableName(String tableName){
		String sql;
		List<Map> listMapBySql=new ArrayList<Map>();
		try {
			sql = new BaseScanSqlCommand().getSqlText(this, "genertaFieldSql.xml", "getTableFieldByTableName");
			sql=sql.replace("##replace##", "")+" and f.table_name='"+tableName+"'";
			listMapBySql = dao.getListMapBySql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMapBySql;
	}
	/**
	 * 作者：zcw
	 * 功能：获取字段根据表名，用于生成
	 * 时间：2017-11-24
	 * @return 
	 */
	public List<Map> getTableFieldByTableName2(String tableName){
		String sql;
		List<Map> listMapBySql=new ArrayList<Map>();
		try {
			sql = new BaseScanSqlCommand().getSqlText(this, "genertaFieldSql.xml", "getTableFieldByTableName2");
			sql=sql.replace("#replace#"," and f.table_name='"+tableName+"'");
			listMapBySql = dao.getListMapBySql(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listMapBySql;
	}
	/**
	 * 作者：zcw
	 * 功能：新增  修改（回显）弹出页面grid1回显
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String grid1ShowData(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "grid1ShowData");
		return CommonManage.getDesAttribute(sql);
	}
	/**
	 * 作者：zcw
	 * 功能：加载该表所有字段（新增时，输完表名触发）
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String grid1AddShowData(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "getTableFieldByTableName");
		return CommonManage.getDesAttribute(sql);
	}


	/**
	 * 作者：zcw
	 * 功能：获取最大排序根据表名
	 * 时间：2017-12-2 02:09:33
	 * @return 
	 */
	@Override
	public String getMaxSortByTabName(ReceParam rcep) throws Exception {
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "getMaxSortByTabName");
		sql=sql.replace("#tablename", rcep.getParam());
		String maxSort = (String) dao.getSingleValueBySql(sql);
		return maxSort;
	}
	
	
	/**
	 * 作者：zcw
	 * 功能：保存
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String Save(ReceParam rcep) throws Exception {
		Map msm = new HashMap();//设置key value 传回页面
		TBaseField bean = new TBaseField();
		
		String tableName = null;
		String id = null;
		String flag = "";
		Integer num =0;
		//操作Form表单
		if(StringUtils.isEmpty(rcep.getForms())){
			//如果是空就不操作，此处防止没有表单数据却传了表单字段回来
 		}else if(rcep.isUpdate()){//判断是否为修改
			tableName = rcep.getMapByJsonObj(rcep.getForms()).get("TABLENAME")+"";//获取表名
			id = rcep.getMapByJsonObj(rcep.getForms()).get("PK")+"";//特殊处理，表名作为主键
			//单个修改(表单From)
			Map obj = rcep.getMapByJsonObj(rcep.getForms());
			Map putUpdate = MapUtils.putUpdate(obj);
			num+= dao.updateBean(bean, putUpdate);
 		}else if(rcep.isSave()){//判断是否为新增
			//单个新增(表单From)
			String[] keyValue = {"ISLIST","1"};//插入值
 			TBaseField beanAdd = rcep.getBeanAdd(bean, rcep.getForms(),keyValue);
			id = beanAdd.getId();
 			tableName= beanAdd.getTablename();
			num+= dao.saveBean(beanAdd);
		}
		
		/**操作grid新增*/
		if(StringUtils.isNotEmpty(rcep.getAddRows())){
			//批量新增
			String[] keyValue = {"TABLENAME",tableName,"ISLIST","0"};//插入值
			List<TBaseField> listBeanAdd = rcep.getListBeanAdd(bean, rcep.getAddRows(),keyValue);
			num+= dao.saveBeans(listBeanAdd);
		}
		/**操作grid修改*/
		if(StringUtils.isNotEmpty(rcep.getModifyRows())){
			//批量修改
			List<Map> obj = rcep.getListMapByJsonArray(rcep.getModifyRows());
			List<Map> putUpdate = MapUtils.putListUpdate(obj);
			num+= dao.updateBeans(bean, putUpdate);
		}
		flag="操作成功："+num+"条";
		msm.put("flag", flag);
		msm.put("tableName", tableName);
		msm.put("id", id);
		return Json.Encode(msm);
	}
	/**
	 * 删除
	 */
	@Override
	public String remove(ReceParam rcep) throws Exception {
		Map msg = new HashMap();//设置key value 传回页面
	
		String sql = "update t_base_field set state = '0' where id in('"+rcep.getIds()+"')";
		int i = dao.execute(sql);
		String sql2=null;
		int i2 =0;
		if("all".equals(rcep.getType())){//list查看删除
			sql2 = "update t_base_field set state = '0'  where islist='0' and state!=0 and tablename  in('"+rcep.getTableName()+"')";
			i2= dao.execute(sql2);
		}
		msg.put("flag", "删除了"+(i+i2)+"条");
		return rcep.Encode(msg);
	}
	//根据表名称获取主数据
	public  Map getTableMap(String tableName)throws Exception{
		String sql;
		List<Map> listMapBySql=new ArrayList<Map>();
			sql = new BaseScanSqlCommand().getSqlText(this, "genertaFieldSql.xml", "formShowData");
		
		sql=sql+" and t.tableName='"+tableName+"' and t.islist='1' order by sort asc";
		 listMapBySql = dao.getListMapBySql(sql);
		return listMapBySql.get(0);
    }
	//根据表名称获取从数据
    public  List<Map> getFieldList(String tableName)throws Exception{
    	String sql;
		List<Map> listMapBySql=new ArrayList<Map>();
			sql = new BaseScanSqlCommand().getSqlText(this, "genertaFieldSql.xml", "formShowData");
		
		sql=sql+" and t.tableName='"+tableName+"' and t.islist='0'  order by sort asc";
		 listMapBySql = dao.getListMapBySql(sql);
		return listMapBySql;
    }
	
	/**
	 * 功能：生成页面标签
	 * 时间：2017年12月3日18:30:29
	 * @param rcep type 1=list编辑     2=list查看   3=form表单
	 * @return
	 * @throws Exception
	 */
	@Override
	public String generateLabel(ReceParam rcep) throws Exception{
		Map msg = new HashMap();//设置key value 传回页面
		
		//前台功能未实现，功能：用于生成选择 1=bean 2=controller 3=service 和 serviceImpl 4=sqlXml 5=formDatail 6=list
		String grenType = "1,2,3,4,5,6";//rcep.getParam4();
		String sl = "select isblank from t_base_field where id='"+rcep.getId()+"'";
		String singleValueBySql =(String) dao.getSingleValueBySql(sl);
		if(rcep.isNotEmpty(singleValueBySql)&&rcep.equals(singleValueBySql.toUpperCase(),"YES")){
			msg.put("fail", "该记录生成已设置为 禁用！请点击修改取消禁用");
			return rcep.Encode(msg);
		}
		String sql=rcep.getSqlText(this, "genertaFieldSql.xml", "formShowData");
		sql=sql+" and t.islist ='0'";
		sql = sql+" and t.tablename='"+rcep.getTableName()+"'";
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		String rs="";
		if("3".equals(rcep.getType())){
			rs=GenerateJspLabel.generateForm(listMapBySql);
		}else if("2".equals(rcep.getType())){
			rs=GenerateJspLabel.generateList(listMapBySql);
		}else if("1".equals(rcep.getType())){//生成一套  目前只有这个 未来会增加其他，，比如单个生成，多个 等等..
			this.generateLabel(rcep,this.getFieldList(rcep.getTableName()),this.getTableMap(rcep.getTableName()),grenType);
		}
		msg.put("result", resultPage);
		return rcep.Encode(msg);
	}
	
	//zhege jishi 
	/**
	 * 功能：生成文件
	 * @param rcep		
	 * @param fieldList
	 * @param tableMap
	 * @param genType	1=bean 2=controller 3=service 和 serviceImpl 4=sqlXml 5=formDatail 6=list
	 * @return
	 * @throws Exception
	 */
	  public  String generateLabel(ReceParam rcep,List<Map> fieldList,Map tableMap,String genType)throws Exception{
		resultPage ="";//初始化返回结果

	        List<Map> fieldListGrid1 =null;
	        List<Map> fieldListGrid2 =null;
	        Map tGrid1 =null;
	        Map tGrid2 =null;
	        if(tableMap.get("FIELD")==null)return null;
	        //标识符号，是否有外键（is：有外键  no：没有外键）
	        Object pkTB = tableMap.get("PK_TABLENAME");
	        String tableName = tableMap.get("TABLENAME").toString();//表名
	        String tb1 = "";//表名
	        String tb2 = "";//表名
	        String flag = "no";
	        String flag2 = "no";
	        String fi = "";
	        String fi1 = "";
	        String fi2 = "";
	        if(pkTB!=null){
	            String[] pkTbn = pkTB.toString().split(",");//如果关联有多张从表，获取存放到数组中
	            for (int i = 0; i < pkTbn.length; i++)
	            {
	                if(i==1){//只支持2个从表生成
	                    flag2 = "is";
	                    fieldListGrid2 = getFieldList(pkTbn[i]);
	                    tGrid2 = getTableMap(pkTbn[i]);
	                    tb2 = tGrid2.get("TABLENAME").toString();
	                    fi2 = this.getField(tb2,"field");//从2表字段
	                    break;
	                }else{
	                	flag = "is";
	                	fieldListGrid1 = getFieldList(pkTbn[i]);
	                	tGrid1 = getTableMap(pkTbn[i]);
	                	tb1 = tGrid1.get("TABLENAME").toString();
	                	fi1 = getField(tb1,"field");//从1表字段
	                }
	            }
	        }
	        fi = getField(tableName,"field");//主表字段
	        String   sourceFilePath=rcep.getSession().getServletContext().getRealPath("ModelPath.java");
	        String sourceFilePath2 = new Freemarker().getClass().getResource("/").getPath();
	        String firPath = "G:\\Java\\Myclis2014_code";
	        String proName =rcep.getRequest().getServletContext().getContextPath()+"/";//项目名
	        //proName = "pro/";//项目名
	        
	        String src = "src/";  //这里我是写死的  
	        String packagePath ="com/zcwsoft/business/enforce/"; //系统写死（src层）--后期放配置文件  java文件一级路径  页面追加包路径
	        String beanPath ="com/zcwsoft/common/enforce/bean/"; //系统写死（src层）--后期放配置文件(可能抛弃掉)
	        String pagePath ="WebRoot/WEB-INF/jsp/enforce/"; //系统写死（WebRoot层）--后期放配置文件   page页面文件一级路径  页面追加包路径
	        
	        
	        String field = tableMap.get("FIELD").toString();//文件名称（类名）
	        String packageName =tableMap.get("PACKAGENAME").toString(); //包名
	        String tName = tableMap.get("TNAME").toString();//标题（页面使用）
	        //设置首字母大写
	        field = field.substring(0,1).toUpperCase()+field.substring(1,field.length());
	        //设置首字母小写（主要是页面文件名称，IOC容器BeanID）
	        String lowerField = field.substring(0,1).toLowerCase()+field.substring(1,field.length());
	        
	        //controller使用： 返回页面路径 
	        String resultPagePath ="/enforce/"+(packageName+"/"+lowerField).replace(".", "/"); 
	        //页面文件存放完整路径(文件名前的路径)
	        String pageFullPath =(pagePath+packageName).replace(".", "/"); 
	        //java中包完整路径（文件前的路径com开始）
	        String javaFullPath =(packagePath+packageName).replace("/", "."); 
	        //注释设置时间
	        String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        
	        
	        
	      /*  String sourcePath = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot\\freemarker";
	        String resultPath = "E:\\zcw\\EclipseWorkspace\\pro\\src\\com\\zcw\\";
	        String resultPathPage = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot\\result\\";
	        */
	        /*String sourcePath = "E:/zcw/EclipseWorkspace/pro/WebRoot/freemarker";
	        String resultPath = "E:/zcw/EclipseWorkspace/pro/src/com/zcw/"+packageName.replace(".", "/")+"/";
	        String resultPathPage = "E:/zcw/EclipseWorkspace/pro/WebRoot/result/"+packageName.replace(".", "/")+"/";
	        */
	        String sourcePath = "G:/Java/Myclis2014_code/pro/src/com/zcwsoft/common/generateModel";
	        //sourcePath = sourceFilePath;
	        String resultPath = firPath+proName+src+packagePath;//例如：E:/zcw/EclipseWorkspace/pro/src/
	        String resultPathPage = firPath+proName+pagePath;//例如：E:/zcw/EclipseWorkspace/pro/WebRoot/WEB-INF/jsp/enforce/
	        if(tableMap.get("OUTPUTPATH")!=null){
	            resultPath=tableMap.get("OUTPUTPATH").toString()+"/code/"+proName+src+ packagePath+packageName.replace(".", "/")+"/";
	            resultPathPage=tableMap.get("OUTPUTPATH").toString()+"/code/"+proName+pagePath+packageName.replace(".", "/")+"/";
	        }else{
	            resultPath =resultPath+ packageName.replace(".", "/")+"/";
	            resultPathPage = resultPathPage+packageName.replace(".", "/")+"/";
	        }
	     //  查询list
	      //  String path = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot";
	        
	        Map<String, Object> weaponMap = new HashMap<String, Object>();  
	        weaponMap.put("'$'", "$");    
	        weaponMap.put("titleName", tName);    
	        weaponMap.put("mvcPath", lowerField+"Controller");    
	        weaponMap.put("gridListQuery", Freemarker.generateList(fieldList)); 
	        if(genType.contains("6"))
	        {
	        	sitterConfig("listQueryModel.model",lowerField+"List.jsp",sourcePath,resultPathPage,weaponMap);
	        }
	       // 表单+ｇｒｉｄ编辑
	        Map<String, Object> formMap = new HashMap<String, Object>();  
	        formMap.put("'$'", "$");    
	        formMap.put("flag", flag);    
	        formMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
	        formMap.put("titleName", tName);    
	        formMap.put("mvcPath", lowerField+"Controller");    
	        formMap.put("formLabel", Freemarker.generateForm(fieldList)); 
	        formMap.put("gridEdit", Freemarker.generateListEdit(fieldListGrid1));//有从表。Detail页面增加grid1，主要看flag字段
	        formMap.put("gridEdit2", Freemarker.generateListEdit(fieldListGrid2));//有从表2。Detail页面增加grid2,主要看flag2字段
	        if(genType.contains("5"))
	        {
	        	sitterConfig("formDetailModel.model",lowerField+"Detail.jsp",sourcePath,resultPathPage,formMap);
	        }
	       //controller
	        Map<String, Object> controllerMap = new HashMap<String, Object>();  
	        controllerMap.put("flag", flag);    
	        controllerMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid  
	        controllerMap.put("field", field);    
	        controllerMap.put("mvcPath", lowerField);    
	        controllerMap.put("pagePath", resultPagePath);    
	        controllerMap.put("lowerField", lowerField);    
	        controllerMap.put("javaFullPath", javaFullPath); 
	        controllerMap.put("nowDate", nowDate); 
	        if(genType.contains("2"))
	        {
	        	sitterConfig("controller.model",field+"Controller.java",sourcePath,resultPath+"controller/",controllerMap);
	        }
	        //service
	        Map<String, Object> serviceMap = new HashMap<String, Object>();  
	        serviceMap.put("flag", flag);    
	        serviceMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
	        serviceMap.put("field", field);    
	        serviceMap.put("lowerField", lowerField);    
	        serviceMap.put("javaFullPath", javaFullPath); 
	        serviceMap.put("nowDate", nowDate); 
	        if(genType.contains("3"))
	        {
	        	sitterConfig("serviceModel.model",field+"Service.java",sourcePath,resultPath+"service/",serviceMap);
	        }
	        
	        //bean
	        Map<String, Object> beanMap = new HashMap<String, Object>();  
	        beanMap.put("flag", flag);    
	        beanMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
	        beanMap.put("field", field);    
	        beanMap.put("content", getVariableFields(tableName,field));    
	        beanMap.put("tableName", tableName);    
	        beanMap.put("javaFullPath", javaFullPath);    
	        beanMap.put("nowDate", nowDate); 		
	        if(genType.contains("1"))
	        {		//sourcePath,firPath+proName+src+beanPath+"/"			
	        	sitterConfig("beanModel.model",field+"Bean.java",sourcePath,resultPath+"bean/",beanMap);
	        }
	        //sqlxml
	        Map<String, Object> sqlXmlMap = new HashMap<String, Object>();  
	        sqlXmlMap.put("flag", flag);    
	        sqlXmlMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
	        sqlXmlMap.put("tfield", fi); //获取   
	        sqlXmlMap.put("tableName", tableName);    
	        sqlXmlMap.put("tfield1", fi1); //获取   
	        sqlXmlMap.put("tableName1", tb1);    
	        sqlXmlMap.put("tfield2", fi2); //获取   
	        sqlXmlMap.put("tableName2", tb2);  
	        if(genType.contains("4"))
	        {  
	        	sitterConfig("sqlXmlModel.model",field+"Sql.xml",sourcePath,resultPath+"service/impl/",sqlXmlMap);
	        }
	        
	        //serviceImpl  这个人必须放最后面
	        Map<String, Object> serviceImplMap = new HashMap<String, Object>();  
	        serviceImplMap.put("flag", flag);    
	        serviceImplMap.put("field", field);            
	        serviceImplMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
	        serviceImplMap.put("tableName", tableName);    
	        serviceImplMap.put("serviceImplGetId", serviceImplGetId);    
	        serviceImplMap.put("lowerField", lowerField);    
	        serviceImplMap.put("javaFullPath", javaFullPath); 
	        serviceImplMap.put("nowDate", nowDate); 
	        if(genType.contains("3"))
	        {
	        	sitterConfig("serviceImplModel.model",field+"ServiceImpl.java",sourcePath,resultPath+"service/impl/",serviceImplMap);
	        }
	        return "1";
	    }
	    

      public String serviceImplGetId = "";//主键字段，，用于serviceImpl getId
	  
	  	//获取字段，用于xmlSql字段
	    public String getField(String tableName,String keyName)throws Exception{
	    	keyName=keyName.toUpperCase();
			List<Map> listField = this.getTableFieldByTableName(tableName.toUpperCase());
			List<Map> prmList = this.getTableFieldByTableName2(tableName.toUpperCase());//获取主键
			String zhujian ="";
			for (int i = 0; i < prmList.size(); i++) {
				if(prmList.get(i).get("PRY")!=null){
					zhujian = (String)prmList.get(i).get("PRY");
					serviceImplGetId = removeUnderLine(zhujian.toLowerCase());
					serviceImplGetId = serviceImplGetId.substring(0,1).toUpperCase()+serviceImplGetId.substring(1,serviceImplGetId.length());
				}
			}
			String blank="	        t.";
			String fields = "";
			for (int i = 0; i < listField.size(); i++) {
				Map<String,Object> m = listField.get(i);
				if(i!=listField.size()-1){
 					if(!"".equals(zhujian)&&zhujian.equals(m.get(keyName).toString().toUpperCase())){
						fields+=blank+m.get(keyName)+" as PK,\n";
					}else{
						fields+=blank+m.get(keyName)+",\n";
					}
				}else{
					if(!"".equals(zhujian)&&zhujian.equals(m.get(keyName).toString().toUpperCase())){
						fields+=blank+m.get(keyName)+" as PK,\n";
					}else{
					fields+=blank+m.get(keyName)+"\n";
					}
				}
			}
	    	return fields.toLowerCase();
	    }
	    
	   public Map<String,Object> dt = new HashMap();//数据库类型作为key  java类型作为value
        
	  //获取变量，用于实体Bean    主要调用，，，拼接完整
	    public String getVariableFields(String tableName,String iField)throws Exception{

			List<Map> lm = this.getTableFieldByTableName2(tableName.toUpperCase());
	    	String blank="   ";
	        String blank2="         ";
	        String variable = "";
	        String primary= "";//存构造方法内容
	        String toString = "";
	        boolean isPry = false;
	        dt.put("VARCHAR2", "String");
	        dt.put("NUMBER", "Integer");
	        dt.put("DATE", "Date");
	       
	        variable += "/*"+lm.get(0).get("BIAOZHUSHI")+"*/\n";
	        variable += "@Entity\n"+
	                "@Table(name = \""+tableName+"\", schema = \"#OWNER#\")\n"+
	                "public class "+iField+"Bean implements java.io.Serializable {\n\n"+
	                "private static final long serialVersionUID = 3259867707009629625L;\n\n";
	       
	       variable += "\n"+this.getVariableField(lm)+"\n";//定义变量 例如： private String id;//zhushi
	       
	       variable += getPrimaryVar(lm,iField);//获取主键get Set 和构造函数
	        
	        for (int i = 0; i < lm.size(); i++) {
	            Map<String,Object> m = lm.get(i);
	            String tFieldSor=m.get("FIELD").toString();//表字段
	            String tField = removeUnderLine(tFieldSor.toLowerCase());
	            String field=m.get("FIELD").toString().replace("_", "").toLowerCase();//去掉 _ 的字段
	            //字段首字母大写
	            String firstUpField=field.substring(0,1).toUpperCase()+field.substring(1,field.length());
	            String dataType= m.get("FIELDTYPE").toString().toUpperCase();//字段类型
	            String zhushi= (String)m.get("TNAME");//注释
	            String pry= (String)m.get("PRY");//主键  判断：不为空就是主键
	            String owner= (String)m.get("OWNER");//用户名\域名
	            String dLength= m.get("DLENFTH")+"";//长度
	            String defaultValue= (String)m.get("DEFAULTVALUE");//默认值
	            String nullable= (String)m.get("NULLABLE");//是否空 N:不能空 Y:可空  
	            
	            if(pry!=null&&!"".equals(pry)){//如果是主键字段 不操作，因为已经特殊处理
	                toString += field+"=\"+"+field+"+\"";
	                continue;
	            }
	           
	            /*设置get方法*/
	            variable +=blank+ "/*"+zhushi+"\n   get()方法*/\n"; 
	            
	            /*判断注解类型*/
	            if("VARCHAR2".equals(dataType)){
	                    variable +=blank+ "@Column(name=\""+tFieldSor+"\",#NULLABLE# length="+dLength+")\n";
	            }else if("NUMBER".equals(dataType)){
	                    variable +=blank+ "@Column(name=\""+tFieldSor+"\",#NULLABLE# precision=8, scale=0)\n"; 
	            }else if("DATE".equals(dataType)){
	                variable +=blank+ "@Temporal(TemporalType.TIMESTAMP)\n"; 
	                variable +=blank+ "@Column(name=\""+tFieldSor+"\",#NULLABLE# length="+dLength+")\n"; 
	            }
	            /*判断是否可以空根据NULLABLE*/
	            if("N".equals(nullable)){//不能为空
	                variable= variable.replace("#NULLABLE#", " nullable=false,");//不能为空
	            }else{
	                variable= variable.replace("#NULLABLE#", "");//可以为空
	            }
	            
	            variable +=blank+ "public "+dt.get(dataType)+" get"+firstUpField+"() {\n"+
	                       blank2+"return this."+field+";\n"+
	                       blank+ "}\n"; 

	            /*设置set方法*/
	            variable +=blank+ "/*"+zhushi+"\n   set()方法*/\n"; 
	            variable +=blank+ "public void set"+firstUpField+"("+dt.get(dataType)+" "+field+") {\n"+
	                       blank2+"this."+field+"="+field+";\n"+
	                       blank+ "}\n"; 
	            
	            
	            variable = variable.replace("#OWNER#", owner);//此处只在第一次有效.
	            toString += field+"=\"+"+field+"+\"";
	        }
	        variable+="@Override\n " +
	                "public String toString(){"+
	                "return \""+iField+"Bean["+toString+"]\";\n }";
	        return variable;
	    	
	    	
	    }
	    
	  /*  public static void main(String[] args) {
	    	System.out.println(removeUnderLine("_wori_nima___hehe"));
		}*/
	    /**
	     * 功能：吧字段中下划线去掉 并下划线后一位大写
	     * @param tField
	     * @return
	     */
	    private String removeUnderLine(String tField) {
	    	if(tField.indexOf("_")==-1){return tField;}
	    	String result="";
	    	int stI=tField.indexOf("_");
	    	tField=tField.substring(0,stI)+tField.substring(stI+1,stI+2).toUpperCase()+tField.substring(stI+2,tField.length());
	    	tField = removeUnderLine(tField);
	    	return tField;
	    }

		//获取变量，用于实体Bean  获取字段，
	    public String getVariableField(List<Map> listField)throws Exception{
			//List<Map> listField = this.getTableFieldByTableName(tableName.toUpperCase());
			String blank="	";
			String variable = "";
			//Map<String,Object> dt = new HashMap();//字段作为key  类型作为value
			dt.put("VARCHAR2", "String");
			dt.put("NUMBER", "Integer");
			dt.put("DATE", "Date");
			//字段变量处理
			for (int i = 0; i < listField.size(); i++) {
				Map<String,Object> m = listField.get(i);
				Map<String,Object> rsm = new HashMap();//字段作为key  类型作为value
					String field=m.get("FIELD").toString().replace("_", "").toLowerCase();
					field = removeUnderLine(field);
					String dataType= m.get("FIELDTYPE").toString().toUpperCase();
					String zhushi=(String) m.get("TNAME");
					 rsm.put(field, dataType.toLowerCase());
					 variable +=blank+ "private "+dt.get(dataType)+" "+field+";//"+zhushi+"\n"; 
			}
			
	    	return variable;
	    }
	    //获取主键的get set ,,用于放在首位
	    public  String getPrimaryVar(List<Map> lm,String iField){
	        String blank="   ";
	        String blank2="         ";
	        String primary= "";//存构造方法内容
	        //Map<String,Object> dt = new HashMap<String,Object>();//字段作为key  类型作为value
	        dt.put("VARCHAR2", "String");
	        dt.put("NUMBER", "Integer");
	        dt.put("DATE", "Date");
	       
	        for (int i = 0; i < lm.size(); i++) {
	            Map<String,Object> m = lm.get(i);
	            String tFieldSor=m.get("FIELD").toString();//表字段
	            String tField = removeUnderLine(tFieldSor.toLowerCase());
	            String field=m.get("FIELD").toString().replace("_", "").toLowerCase();//去掉 _ 的字段
	            //字段首字母大写
	            String firstUpField=field.substring(0,1).toUpperCase()+field.substring(1,field.length());
	            String dataType= m.get("FIELDTYPE").toString().toUpperCase();//类型
	            String zhushi= (String)m.get("TNAME");//注释
	            String pry= (String)m.get("PRY");//主键
	            String dLength= m.get("DLENFTH")+"";//长度
	            
	            if(pry==null||"".equals(pry)){
	                continue;
	            }else{
	                

	                    primary +=blank+ "public "+iField+"Bean() {\n"+
	                              blank+ "}\n\n"; 
	                    primary +=blank+ "public "+iField+"Bean("+dt.get(dataType)+" "+field+") {\n"+
	                              blank2+"this."+field+"="+field+";\n"+
	                              blank+ "}\n\n"; 
	                
	                    /*设置get方法*/
	                    primary +=blank+ "/*"+zhushi+" get()方法*/\n"; 
	                    primary +=blank+ "@Id\n";
	                    primary +=blank+ "@Column(name=\""+tFieldSor+"\",unique = true,#NULLABLE# length="+dLength+")\n";
	                    primary +=blank+ "public "+dt.get(dataType)+" get"+firstUpField+"() {\n"+
	                            blank2+"return this."+field+";\n"+
	                            blank+ "}\n"; 

	                 /*设置set方法*/
	                    primary +=blank+ "/*"+zhushi+" set()方法*/\n"; 
	                    primary +=blank+ "public void set"+firstUpField+"("+dt.get(dataType)+" "+field+") {\n"+
	                            blank2+"this."+field+"="+field+";\n"+
	                            blank+ "}\n"; 
	                    return primary;
	            }
	        }   
	        return primary;
	    }
	    
	    /**
	     * freemarker,核心方法，生成文件
	     * @param lsm
	     */
	    public  void sitterConfig(String sourceName,String resultName,String sourcePath,String resultPath,Map m){
	        try{
	            //AddFolder(resultPath);
	            //创建文件夹
	            File file = new File(resultPath);
	            if(!file.exists()){
	                file.mkdirs();
	            }
	          //创建一个合适的Configration对象  
	            Configuration configuration = new Configuration();  
	            configuration.setDirectoryForTemplateLoading(new File(sourcePath));  
	            configuration.setObjectWrapper(new DefaultObjectWrapper());  
	            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
	            //获取或创建一个模版。  
	            Template template = configuration.getTemplate(sourceName);  
	             
	              
	            Writer writer  = new OutputStreamWriter(new FileOutputStream(resultPath+resultName),"UTF-8");  
	            template.process(m, writer);  
	            resultPage +=resultName+"文件！生成成功~~\n";
	            System.out.println(resultName+"文件！生成成功~~");  
	        } catch (IOException e) {  
	        	resultPage +=resultName+"文件！生成失败~~原因："+e.getMessage()+"\n";
	            e.printStackTrace();  
	        } catch (TemplateException e) {  
	        	resultPage +=resultName+"文件！生成失败~~原因："+e.getMessage()+"\n";
	            e.printStackTrace();  
	        } 
	    }

	
}
