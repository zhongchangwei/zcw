package com.zcwsoft.business.common.miniui.service.impl;

import java.net.URLDecoder;
import java.util.Iterator;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.common.miniui.dao.LoadDataDao;
import com.zcwsoft.business.common.miniui.service.LoadDataService;
import com.zcwsoft.common.newutils.CommonManage;
import com.zcwsoft.common.util.DES;
import com.zcwsoft.common.util.MD5;

@Service
public class LoadDataServiceImpl implements LoadDataService {

	@Qualifier("LoadDataDaoImpl")
	@Autowired
	private LoadDataDao loadDataDao;
	
	/**
	 * 解密
	 * 作者：cyc
	 * 开发时间：2016年4月28日14:31:44
	*/
	public String decode(String data) throws Exception {
		String returnStr = "";
		if (CommonManage.isNull(data) || data.length()<=32) {
			return null;
		}
		String md5 = data.substring(data.length()-32, data.length());
		String temp = data.substring(0,data.length()-32);
		if (!md5.equals(MD5.getMD5(temp))) {//Md5校验
			return null;
		}
		String str = DES.decrypt(temp);//第一重解密
		JSONObject o = JSONObject.fromObject(str);
		String sql = o.getString("sql");
		sql = DES.decrypt(sql);//第二重解密
		returnStr = sql;
		return returnStr;
	}
	

	@Override
	public String load(String data, String replace, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields) throws Exception {
		String sql = decode(data);
		//SQL替换，用于显示默认条件
		if (CommonManage.notNull(replace)) {
 			String jsonStr = URLDecoder.decode(replace,"utf-8");
			JSONObject obj = JSONObject.fromObject(jsonStr);
			@SuppressWarnings("rawtypes")
			Iterator it = obj.keys();  
			while (it.hasNext()) {  
				String keytemp = it.next().toString();
 				String value = obj.getString(keytemp);
				sql = sql.replace(keytemp, value);
				System.out.println(sql);
			}
		}
		return loadDataDao.load(sql, pageIndex, pageSize, sortField, sortOrder, sortFields);
	}

	
	@Override
	public String loadArray(String data) throws Exception {
		String sql = decode(data);
		return loadDataDao.loadArray(sql);
	}

}
