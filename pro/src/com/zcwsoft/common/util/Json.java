package com.zcwsoft.common.util;
import java.sql.Timestamp;
import java.util.Date;


import flexjson.*;
import flexjson.transformer.*;
public class Json {
	public static String Encode(Object obj) {
		if(obj == null || obj.toString().equals("null")) return null;
		if(obj != null && obj.getClass() == String.class){
			return obj.toString();
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
		return serializer.deepSerialize(obj);
	}
	public static Object Decode(String json) {
		if (StringUtil.isNullOrEmpty(json)) return "";
		Object obj=null;
		JSONDeserializer deserializer = new JSONDeserializer();
		try {
			deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));	
			obj= deserializer.deserialize(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			
		
		if(obj != null && obj.getClass() == String.class){
			return Decode(obj.toString());
		}		
		return obj;
	}
	public static Object Decode2(String json) {
		if (StringUtil.isNullOrEmpty(json)) return "";
		JSONDeserializer deserializer = new JSONDeserializer();
		//deserializer.use(String.class, new DateTransformer("yyyy-MM-dd HH:mm:ss"));		
		Object obj = deserializer.deserialize(json);
		if(obj != null && obj.getClass() == String.class){
			return Decode(obj.toString());
		}		
		return obj;
	}
	
}
