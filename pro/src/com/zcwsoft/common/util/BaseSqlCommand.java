package com.zcwsoft.common.util;
/**
 * 
 * @author kingschan
 * <br>
 * <b style="color:green;">得到xml文件中的sql文本 </b>
 * <p><b style="color:red;">取代com.kingzheng.common.SqlCommand</b></p>
 */
public interface BaseSqlCommand {
	/**
	 * 得到sql文本
	 * @param obj  与对象的相对路径
	 * @param key  XML文件键值
	 * @param fileName 文件名字
	 * @return
	 */
	String getSqlText(Object obj,String fileName,String key)throws Exception;
	/**
	 * 得到sql文本的重载方法
	 * @param filePath xmlsql文件的路径
	 * @param fileName 文件名
	 * @param key      键名
	 * @return
	 * @throws Exception
	 */
	String getSqlText(String filePath,String fileName,String key)throws Exception;
}
