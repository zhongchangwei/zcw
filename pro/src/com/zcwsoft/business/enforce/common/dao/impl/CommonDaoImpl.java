package com.zcwsoft.business.enforce.common.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.dao.HibernateBaseDao;
import com.zcwsoft.common.newutils.BaseSqlUtils;
import com.zcwsoft.common.newutils.MapUtils;

@Repository("CommonDaoImpl")
public class CommonDaoImpl extends HibernateBaseDao implements CommonDao {

	
	
	/**
	 * 功能：传入一条未处理的sql语句，把结果集存入List<实体bean>中
	 * 时间：2017年11月14日22:09:53
	 * @param t		实体
	 * @param sql	未处理的sql     
	 * @return		List<实体>
	 * @throws Exception
	 *//*
	public <T>  List<T> getBeanListNoReplSql(T t,String sql)throws Exception{
		Map mapValue = MapUtils.beanConverMap(t);//实体Bean转Map
		String execuSql = BaseSqlUtils.replaceParam(sql, mapValue);//处理sql
		List<Map> listMapValue = this.getListMapBySql(execuSql);//执行sql获取值List<Map>
		if(listMapValue.size()==0||listMapValue.isEmpty()){
			return null;
		}
		List<T> lsBean = MapUtils.getListBeanByMap(t, listMapValue);//把值List<Map>转换成List<实体>
		return lsBean;
	}
	*//**
	 * 功能：传入一条未处理的sql语句，把结果集存入List<实体bean>中
	 * 时间：2017年11月14日22:09:53
	 * @param t		实体
	 * @param sql	未处理的sql     
	 * @return		List<实体>
	 * @throws Exception
	 *//*
	public <T>  List<T> getBeanListYesReplSql(T t,String sql)throws Exception{
		List<Map> listMapValue = this.getListMapBySql(sql);//执行sql获取值List<Map>
		if(listMapValue.size()==0||listMapValue.isEmpty()){
			return null;
		}
		List<T> lsBean = MapUtils.getListBeanByMap(t, listMapValue);//把值List<Map>转换成List<实体>
		return lsBean;
	}
	
	*//**
	 * 功能：传入一条未处理的sql语句，把结果集存入实体bean中
	 * 时间：2017年11月14日22:09:53
	 * @param t		实体
	 * @param sql	未处理的sql
	 * @return		List<实体>
	 * @throws Exception
	 *//*
	public <T> T getBean(T t,String sql)throws Exception{
		Map mapValue = MapUtils.beanConverMap(t);//实体Bean转Map
		String execuSql = BaseSqlUtils.replaceParam(sql, mapValue);//处理sql
		List<Map> listMapValue = this.getListMapBySql(execuSql);//执行sql获取值List<Map>
		if(listMapValue.size()==0||listMapValue.isEmpty()){
			return null;
		}
		T lsBean = MapUtils.getBeanByMap(t, listMapValue.get(0));//把值Map转换成实体
		return lsBean;
	}
	*/
	
	
	
	
	
	/**
	 * 功能：根据sql返回List
	 * @param sql
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public List<?> getListBySql(String sql, Object...objects)throws Exception{
		return queryForListBySql(sql, objects);
	}
	
	
	/**
	 * 功能：根据sql返回List<Map>
	 * @param sql
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public List<Map> getListMapBySql(String sql, Object...objects)throws Exception{
		return (List<Map>) queryForListMapBySql(sql, objects);
	}
	
	
	
	
	/**
	 * 功能：根据sql查询单一值
	 * @param sql
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public Object getSingleValueBySql(String sql, Object...object)throws Exception{
		return  uniqueQueryBySql(sql, object);
	}
	
	
	/**-----%%%%%-----
	 * 功能：根据code获取编号的存储过程
	 */
	public String getSequenceNum(String code)throws Exception{
		return getSequenceNumber(code);
	}

	/**-----%%%%%-----
	 * 功能：po保存
	 */
	public<T> Integer saveBean(T t)throws Exception{
		saveObj(t);
		return 1;
	}
	/**-----%%%%%-----
	 * 功能：批量po保存
	 */
	public<T> Integer saveBeans(List<T> t)throws Exception{
		int i=0;
		for (int j = 0; j < t.size(); j++) {
			saveObj(t.get(j));
			i++;
		}
		return i;
	}
	
	/**-----%%%%%-----
	 * 功能：po更新
	 */
	@SuppressWarnings("unchecked")
	public<T> Integer updateBean(T t,Map obj)throws Exception{
		Class cls=null;
		try {
			
			cls = t.getClass().forName(t.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		t = (T)queryById(cls,obj.get("PK"));//如果这里报错  那你就看看表主键是否与实体主键一直。。不一致 就返回null
		t = MapUtils.getBeanByMap(t,obj,"update");
		updateObj(t);
		return 1;
	}
	
	/**-----%%%%%-----
	 * 功能：批量po更新
	 * @return 
	 */
	public<T> Integer updateBeans(T t,List<Map> obj)throws Exception{
		int i=0;
		for (int j = 0; j < obj.size(); j++) {
			updateBean(t,obj.get(j));
			i++;
		}
		return i;
	}
	
	
	
	/**-----%%%%%-----
	 * 功能：根据id获取实体
	 */
	public Object queryById(Class<?> po , Object id){
		return get(po, id);
	}
	
	/**
	 * 功能：hql单一值查值
	 */
	public Object getSingleValueByHql(String hql,Object...args) throws Exception{
		return uniqueQueryByHql(hql, args);
	}
	/**
	 * 功能：根据sql执行
	 */
	public int execute(String sql,Object... args)throws Exception{
		return executeSQL(sql, args);
	}

	/**
	 * 功能：获取Connection链接对象
	 */
	@Override
	public Connection getConnection() throws SQLException {
		return SessionFactoryUtils.getDataSource(getSessionfactory()).getConnection();
    }
}
