package com.zcwsoft.common.newutils.pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.BaseSqlUtils;
import com.zcwsoft.common.newutils.BaseSysUtils;

public class TableIndexUtils {

	public  CommonDao dao = null;
	public  String tableName = null;
	public  List<Map> itmLsM = new ArrayList<Map>();
	public  List<Map> fieldLsM = new ArrayList<Map>();
	public final String subSy = "#_#";
	public final String indexValueKey = "VALUE";
	
	private void init(){
		dao = (CommonDao) BaseSysUtils.getIocBean("CommonDaoImpl");
	}

	public TableIndexUtils(String tableName)
	{
		this.init();
		this.execute(tableName);
	}
	public TableIndexUtils()
	{
	}
	
	//这个构造是启动服务时 request域对象没有创建,获取不到IOC容器中的对象，所以需要单独引进dao
	public TableIndexUtils(String tableName,CommonDao dao)
	{
		this.dao = dao;
		this.execute(tableName);
	}
	
	public void execute(String tableName)
	{
		if(BaseSysUtils.isNotBlank(tableName))
		{
			this.tableName = tableName.toUpperCase();
		}
		String sql = BaseSqlUtils.getSql(this, "sql.xml", "getTableIndexs");
		try {
			itmLsM = dao.getListMapBySql(sql,this.subSy,this.tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该表的索引数量
	 * @return
	 */
	public int size()
	{
		return itmLsM.size();
	}
	/**
	 * 该表是否有索引
	 * @return
	 */
	public boolean isHaveIndex()
	{
		if(size() > 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * 该表是否有唯一索引
	 * @return
	 */
	public boolean isHaveUniqueIndex()
	{
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String uniqueNess = (String)itmLsM.get(i).get("UNIQUENESS");
			if("UNIQUE".equals(uniqueNess))
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * 获取该表唯一主键字段
	 * @return true=有唯一主键 ，false=反之
	 */
	public boolean isHaveUniquePrimaryField()
	{
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String uniqueNess = (String)itmLsM.get(i).get("UNIQUENESS");
			String indexName = (String)itmLsM.get(i).get("INDEX_NAME");
			if("UNIQUE".equals(uniqueNess) && indexName.contains("PK_"))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取该表唯一的索引字段
	 * @return true=有唯一索引 ，false=反之
	 */
	public String getUniqueIndexField()
	{
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String uniqueNess = (String)itmLsM.get(i).get("UNIQUENESS");
			String columnName = (String)itmLsM.get(i).get("COLUMN_NAME");
			if("UNIQUE".equals(uniqueNess))
			{
				return columnName;
			}
		}
		return null;
	}
	

	/**
	 * 获取该表唯一主键字段
	 * @return 
	 */
	public String getUniquePrimaryField()
	{
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String uniqueNess = (String)itmLsM.get(i).get("UNIQUENESS");
			String indexName = (String)itmLsM.get(i).get("INDEX_NAME");
			String columnName = (String)itmLsM.get(i).get("COLUMN_NAME");
			if("UNIQUE".equals(uniqueNess) && indexName.contains("PK_"))
			{
				return columnName;
			}
		}
		return null;
	}
	/**
	 * 该表是否有唯一索引
	 * @return
	 */
	public List<Map> getListMap()
	{
		return itmLsM;
	}

	/**
	 * 获取所有索引对应的字段名称
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getAllIndexField() 
	{
		List ls = new ArrayList();
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String columnName = (String)itmLsM.get(i).get("COLUMN_NAME");
			ls.add(columnName);
		}
		return ls;
	}
	/**
	 * 获取所有索引名称和索引字段
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getAllIndexNameAndIndexField()
	{
		Map map = new HashMap();
		for (int i = 0; i < itmLsM.size(); i++) 
		{

			String indexName = (String)itmLsM.get(i).get("INDEX_NAME");
			String FieldName = (String)itmLsM.get(i).get("COLUMN_NAME");
			map.put(indexName, FieldName);
		}
		return map;
	}
	/**
	 * 根据索引字段获取索引名称
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getIndexNameByIndexField(String fieldName)
	{
		for (int i = 0; i < itmLsM.size(); i++) 
		{
			String indexName = (String)itmLsM.get(i).get("INDEX_NAME");
			String FieldName = (String)itmLsM.get(i).get("COLUMN_NAME");
			if(fieldName.equals(FieldName))
			{
				return indexName;
			}
		}
		return null;
	}
	/**
	 * 检测是否有索引字段
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean checkIndexField(Object[] obj)
	{
		Map allIndexNameAndIndexField = this.getAllIndexNameAndIndexField();
		for (int i = 0; i < obj.length; i+=2) 
		{
			String field = ((String)obj[i]).toUpperCase();
			if(allIndexNameAndIndexField.containsValue(field))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取表字段对应实体变量映射
	 * key=表字段名称  value=实体变量名称
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getFieldToBeanMapping()
	{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String sql = BaseSqlUtils.getSql(this, "sql.xml", "getFieldNameBytableName");
		try {
			this.fieldLsM = dao.getListMapBySql(sql,this.tableName);
			for (int i = 0; i < fieldLsM.size(); i++) 
			{
				String fieldName =(String) fieldLsM.get(i).get("COLUMN_NAME");
				hashMap.put(fieldName, removeUnderLine(fieldName.toLowerCase()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}

	/**
	 * 获取实体变量对应表字段映射
	 * key=实体变量名称  value=表字段名称
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getBeanToFieldMapping()
	{
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String sql = BaseSqlUtils.getSql(this, "sql.xml", "getFieldNameBytableName");
		try {
			this.fieldLsM = dao.getListMapBySql(sql,this.tableName);
			for (int i = 0; i < fieldLsM.size(); i++) 
			{
				String fieldName =(String) fieldLsM.get(i).get("COLUMN_NAME");
				hashMap.put(removeUnderLine(fieldName.toLowerCase()) , fieldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hashMap;
	}
	
	/**
     * 功能：吧字段中下划线去掉 并下划线后一位大写
     * 因表字段一般使用下划线区分单词命名，实体则去掉下划线后首字母大写
     * @param tField
     * @return
     */
    private String removeUnderLine(String tField) {
    	if(tField.indexOf("_")==-1)
    	{return tField;}
    	String result="";
    	int stI=tField.indexOf("_");
    	tField=tField.substring(0,stI)+tField.substring(stI+1,stI+2).toUpperCase()+tField.substring(stI+2,tField.length());
    	tField = removeUnderLine(tField);
    	return tField;
    }
}
