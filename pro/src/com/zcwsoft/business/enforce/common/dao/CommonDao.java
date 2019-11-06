package com.zcwsoft.business.enforce.common.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public interface CommonDao {
	
	
	/*public <T> T getBean(T t,String sql)throws Exception;
	
	public <T> List<T> getBeanListNoReplSql(T t,String sql)throws Exception;
	
	public <T> List<T> getBeanListYesReplSql(T t,String sql)throws Exception;*/
	
	public List<?> getListBySql(String sql, Object...objects)throws Exception;
	
	public List<Map> getListMapBySql(String sql, Object...obj)throws Exception;
	
	public Object getSingleValueBySql(String sql, Object...object)throws Exception;

	public String getSequenceNum(String code)throws Exception;
	
	public<T> Integer saveBean(T t)throws Exception;
	
	public<T> Integer saveBeans(List<T> t)throws Exception;
	
	public<T> Integer updateBean(T t,Map obj)throws Exception;
	
	public<T> Integer updateBeans(T t,List<Map> obj)throws Exception;
	
	public Object queryById(Class<?> po , Object id)throws Exception;
	
	public Object getSingleValueByHql(String hql,Object...args)throws Exception;
	
	public int execute(String sql,Object... args)throws Exception;
	
	 Connection getConnection()throws SQLException;  

}
