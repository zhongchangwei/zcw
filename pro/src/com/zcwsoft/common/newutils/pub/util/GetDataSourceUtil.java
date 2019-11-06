package com.zcwsoft.common.newutils.pub.util;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.BaseSysUtils;

public class GetDataSourceUtil {

	public static CommonDao dao = (CommonDao) BaseSysUtils.getIocBean("CommonDaoImpl");
	
	public CommonDao getDao(){
		if(dao == null){
			dao = (CommonDao) BaseSysUtils.getIocBean("CommonDaoImpl");
		}
		return dao;
	}
}
//update GENDERSERIALNOAUTO set newnumber = '" + n + "' where id = '" + id + "'"
//select * from GENDERSERIALNOAUTO where TYPE='"+value+"'")