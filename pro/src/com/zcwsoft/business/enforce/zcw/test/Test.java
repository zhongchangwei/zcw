package com.zcwsoft.business.enforce.zcw.test;

import java.util.ArrayList;

import com.zcwsoft.common.newutils.basebean.MenuTreeBean;

public class Test {

	
	public static void main(String[] args) {
		/*String st = new TSUser().getClass().getResource("").getPath();
		System.out.println(StringUtils.substring(st,st.indexOf("classes/")+8,st.length())+new TSUser().getClass().getName());
		
		*/
		System.out.println(new ArrayList<MenuTreeBean>());
	/*	Map<String,Object> mapValue = new HashMap<String,Object>();
		mapValue.put("id", "2017");
		mapValue.put("name", null);
		mapValue.put("age", "");
		mapValue.put("phone", new Date());
		
		JavaBean t = new JavaBean();
		t = MapUtils.mapConverBean(t, mapValue);
		
		String sql = "select * from dual where " +
				"<[and id = :id]> " +
				"<[and name = :name]> " +
				"<[%and age = :age%]> "+
				"<[%and phone = :phone%]> ";	
		sql = BaseSqlUtils.replaceParam(sql, mapValue);
		System.out.println(sql);
		System.out.println(t.toString());
		mapValue = new HashMap<String,Object>();
		mapValue = MapUtils.beanConverMap(t);
		System.out.println(mapValue);*/
		
	}
	
}
