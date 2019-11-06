package com.zcwsoft.common.util;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 得到xml文件中的sql文本 
 *取代com.kingzheng.common.SqlCommand
 * @author kingschan
 *	sql语句扫描
 */
public class BaseScanSqlCommand implements BaseSqlCommand{

	@Override
	public String getSqlText(Object obj, String fileName, String key)throws Exception {		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(obj.getClass().getResource("").getPath().concat(fileName)));
		Element rootElm = document.getRootElement();
		return rootElm.element(key).getTextTrim();
	}

	@Override
	public String getSqlText(String filePath, String fileName, String key)throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filePath.concat(fileName)));
		Element rootElm = document.getRootElement();
		return rootElm.element(key).getTextTrim();
		
	}

}
