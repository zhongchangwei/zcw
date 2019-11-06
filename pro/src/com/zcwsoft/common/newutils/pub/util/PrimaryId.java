package com.zcwsoft.common.newutils.pub.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.zcwsoft.common.newutils.BaseSqlUtils;
import com.zcwsoft.common.util.BaseScanSqlCommand;

public class PrimaryId {

	
	public String getId()
	{
		String format ="";
		try{
			 Map m = selectData("id");
			 Object id = m.get("id");//主键id
			 Object todaytime = m.get("todaytime");//时间
			 Object type = m.get("type");//类型标识
			 Object remark = m.get("remark");//备注声明
			 Object subnumber = m.get("subnumber");//20位截取多少位（后至前）
			 Object newnumber = m.get("newnumber");//序号，用于升序，查询后立马加1
			 int lastn =  Integer.parseInt(subnumber+"");
			 int n = (newnumber+"").length();
			 String mat = "yyyyMMddhhmmssSSS000";//定义好的20长度
			 String lam = "";
			 if(lastn < 4)
			 {
				 lastn = 3;
			 }else{
				 if(lastn > 11)
				 {
					 lastn = 12;
				 }
			 }
			 for (int i = 0; i < lastn; i++) {
				 lam +="0";//截取后使用0拼接
			}
			 format = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
			 format = format.substring(0,mat.length()-lastn);
			 format+= lam;
			 if(format.length()>=n)
			 {
				 String substring = format.substring(0,format.length()-n);
				 format = substring + newnumber;
			 }
			System.out.println(format+"||"+format.length());
			updateData(id+"",newnumber+"");
		 }catch(Exception e){
			  format = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+"999";
		 }	
		return format;
	}

	private void updateData(String string, String string2) {
	    try {
			String sql = new BaseScanSqlCommand().getSqlText(this, "sql.xml", "selectData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 根据类型获取最新主键自增数据
	 * @param string
	 * @return
	 */
	private Map selectData(String string) {
		try {
			String sql = new BaseScanSqlCommand().getSqlText(this, "sql.xml", "updateData");
			String sql2 = BaseSqlUtils.getSql(this, "sql.xml", "updateData");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
