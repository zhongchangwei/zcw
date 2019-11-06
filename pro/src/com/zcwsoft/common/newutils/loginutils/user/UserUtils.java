package com.zcwsoft.common.newutils.loginutils.user;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.BaseSysUtils;
import com.zcwsoft.common.newutils.basebean.ReceParam;


public class UserUtils {

	public static CommonDao dao = (CommonDao) BaseSysUtils.getIocBean("CommonDaoImpl");
			
	public static ReceParam getReceparam(){
		return new ReceParam();
	}
	/**
	 * 密码转换成md5格式
	 * @param password
	 * @return 
	 */
	public static String varentMd5(String password) {
		if("".equals(password)||null==password){
			return null;
		}
		String md5 = com.zcwsoft.common.util.MD5.getMD5(password);
		return md5;
	}
	public static String getStringValueBySql(){
		String singleValueBySql="";
		try {
			singleValueBySql = (String)dao.getSingleValueBySql("select * from dual");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singleValueBySql;
	}
	
	
	
	public static void main(String[] args) {
		//dao = (CommonDao) BaseSysUtils.getIocBean("CommonDaoImpl");
		try {
			Object singleValueBySql = dao.getSingleValueBySql("select * from dual");
			System.out.println(singleValueBySql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(varentMd5("我日"));
	}
}
