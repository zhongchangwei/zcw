package com.zcwsoft.common.newutils.redisutils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.zcwsoft.common.newutils.BaseSysUtils;


public class JSONUtils
{
	
	private static String blank_ = "!_#$%%$#_!";//空格
	private static String null_ = "!_#$%_%$#_!";//空值
	private static Map BlankMap = new HashMap();//空值
	
	static{
		BlankMap.put("!_#$%%$#_!", " ");
		BlankMap.put("!_#$%_%$#_!", "");
	}
    /**
     * 
     * @author zhongchangwei JSON工具类
     * @param 
     * 
     */
    
	
	/**
	 * 替换掉/"null
	 * @param formatString
	 * @return
	 */
	public static String replaceNull(String formatString){
		if(!BaseSysUtils.isBlank(formatString))
        {
        	//System.out.println("\\"+"\"");
			formatString = formatString.replace("\\"+"\"", "");
        }
		return formatString;
	}
	
	
	
	 /***
     * 处理Map,异常数据纠正	JSONObject自带bug:值null时变“null”
     * @param 
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map toMapGet(Map lsm)
    {
    	Map rsm=null;
 		rsm = new HashMap();
		for (Object key : lsm.keySet()) 
		{
			if(lsm.get(key) instanceof List)
			{
				rsm.put(key, toListMap2((List<Map>)lsm.get(key)));
			}else if(lsm.get(key) instanceof Map){
				rsm.put(key, toMapGet((Map)lsm.get(key)));
			}else{
				Object o = null;
				if(lsm.get(key) != null)
				{
					o = (lsm.get(key)+"").replace("\"null\"", "").replace("\"\"", "");
 					/*if((o+"").indexOf("!_#$%%$#_!")!=-1){//redis无法存储空格,加密存储
						o=(o+"").replace("!_#$%%$#_!", " ");
					}*/
				}
				rsm.put(key, o);
			}
		}
    	return rsm;
    }
    /***
     * 插入调用
     * 处理Map,异常数据纠正	JSONObject自带bug:值null时变“null”
     * @param 
     * @return
     */
    @SuppressWarnings("unchecked")
	public static Map toMapSet(Map lsm)
    {
    	Map rsm=null;
 		rsm = new HashMap();
		for (Object key : lsm.keySet()) 
		{
			if(lsm.get(key) instanceof List)
			{
				rsm.put(key, toListMap2((List<Map>)lsm.get(key)));
			}else if(lsm.get(key) instanceof Map){
				rsm.put(key, toMapSet((Map)lsm.get(key)));
			}else{
				Object o = null;
				if(lsm.get(key)!= null)
				{
					o = (lsm.get(key)+"").replace("\"null\"", "").replace("\"\"", "");
					/*if("null".equals(o)){//redis无法存储null
						o="!_#$%_%$#_!";
					}else if(o==null){
						o="!_#$%_%$#_!";
					}else if("".equals(o+"")){
						o="!_#$%_%$#_!";
					}else if((o+"").indexOf(" ")!=-1){//redis无法存储空格,加密存储
						o=(o+"").replace(" ", "!_#$%%$#_!");
					}*/
					
					/*if("!_#$%_%$#_!".indexOf(" ") !=-1){//redis无法存储null
						o=(o+"").replace("!_#$%_%$#_!", "");
					}else if("!_#$%%$#_!".indexOf(" ")!=-1){//redis无法存储空格,加密存储
						o=(o+"").replace(" ", "!_#$%%$#_!");
					}*/
				}
				rsm.put(key, o);
			}
		}
    	return rsm;
    }
    
   
    
    /***
     * 处理List<Map> ,异常数据纠正
     * @param 
     * @return
     */
    @SuppressWarnings("unchecked")
	public static List<Map> toListMap2(List<Map> lsm)
    {
    	List<Map> list = new ArrayList<Map>();
    	Map rsm=null;
    	for (int i = 0; i < lsm.size(); i++) 
    	{
    		Map maps = lsm.get(i);
    		rsm = new HashMap();
			for (Object key : maps.keySet()) 
			{
				if(maps.get(key) instanceof List)
				{
					rsm.put(key, toListMap2((List<Map>)maps.get(key)));
				}else if(key instanceof Map){
					rsm.put(key, toMapSet((Map)key));
				}else{
					Object o = null;
					if(maps.get(key) != null)
					{
						o = (maps.get(key)+"").replace("\"null\"", "").replace("\"\"", "");
						/*if("null".equals(o)){//redis无法存储null
							o="!_#$%_%$#_!";
						}else if(o==null){
							o="!_#$%_%$#_!";
						}else if("".equals(o+"")){
							o="!_#$%_%$#_!";
						}else if((o+"").indexOf(" ")!=-1){//redis无法存储空格,加密存储
							o=(o+"").replace(" ", "!_#$%%$#_!");
						}*/
						 if((o+"").length()==21 && (o+"").indexOf("-") !=-1 && (o+"").indexOf(" ")!=-1 && (o+"").indexOf(".")!=-1 && (o+"").indexOf(":")!=-1 ){
			    			o =(o+"").substring(0,(o+"").length()-2);
						 }/*else if((o+"").indexOf(" ")!=-1){//redis无法存储空格,加密存储
							o=(o+"").replace(" ", "!_#$%%$#_!");
						}*/
					}
					rsm.put(key, o);
				}
			}
			list.add(rsm);
		}
    	return list;
    }
	
    /***
     * redis缓存使用类型
     * 处理Map,异常数据纠正	JSONObject自带bug:值null时变“null”
     * @param 
     * @return
     */
    /*@SuppressWarnings("unchecked")
	public static Map<String,String> toMap_redis(Map lsm)
    {
    	Map<String,String> rsm=null;
		rsm = new HashMap<String,String>();
		for (Object key : lsm.keySet()) 
		{
			String value = null;
			Object obj = lsm.get(key);
			if(obj instanceof Date){
				value = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(obj);
			}else{
				value = lsm.get(key)+"";
			}
			rsm.put((String)key, value);
			
		}
    	return rsm;
    }*/
    
    /***
     * redis缓存转换专用
     * 处理List<Map> ,异常数据纠正
     * @param 
     * @return
     */
  /*  @SuppressWarnings("unchecked")
	public static List<Map<String,String>> toListMap_redis(List<Map> lsm)
    {
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
    	Map<String,String> rsm=null;
    	for (int i = 0; i < lsm.size(); i++) 
    	{
    		Map maps = lsm.get(i);
    		rsm = new HashMap();
			for (Object key : maps.keySet()) 
			{
				String value = null;
				Object obj = maps.get(key);
				if(obj instanceof Date){
					value = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(obj);
				}else{
					value = maps.get(key)+"";
				}
				rsm.put((String)key, value);
			}
			list.add(rsm);
		}
    	return list;
    }
    */
	
    /***
     * redis转换专用
     * 将List对象序列化为JSON文本
     */
   /* public static String toStringByJSONArray_redis(List<Map> list)
    {
        JSONArray jsonArray = JSONArray.fromObject(list);
        String tostring = jsonArray.toString();
        tostring = replaceNull(tostring);
        return tostring;
    }*/
    
    
    /***
     *  redis转换专用
     * 将对象序列化为JSON文本
     * @param object
     * @return
     *//*
    public static String toStringByJSONObject_redis(Map object)
    {
        JSONObject jsonArray = JSONObject.fromObject(object);
        String tostring = jsonArray.toString();
        tostring = replaceNull(tostring);
        return tostring;
    }*/
    
    /***
     * 将List对象序列化为JSON文本
     */
    public static String toStringByJSONArray(List<Map> list)
    {
        JSONArray jsonArray = JSONArray.fromObject(list);
        String tostring = jsonArray.toString();
        tostring = replaceNull(tostring);
        return tostring;
    }
    
    
    /***
     * 将对象序列化为JSON文本
     * @param object
     * @return
     */
    public static String toStringByJSONObject(Map object)
    {
    	object = convertMapDataSet(object);
        JSONObject jsonArray = JSONObject.fromObject(object);
        String tostring = jsonArray.toString();
        tostring = replaceNull(tostring);
        return tostring;
    }

    @SuppressWarnings("unchecked")
	public static Map convertMapDataSet(Map m){
    	Map hashMap = new HashMap();
    	for(Object key:m.keySet()){
    		Object obj = m.get(key);
    		if(key instanceof Date ){
    			String format = new SimpleDateFormat("yyyy-MM-dd").format((Date)obj);
    			hashMap.put(key,format);
    		}else if(key instanceof Timestamp){
    			String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)obj);
    			hashMap.put(key,format);
    		}else if((obj+"").length()==21 && (obj+"").indexOf("-") !=-1 && (obj+"").indexOf(" ")!=-1 && (obj+"").indexOf(".")!=-1 && (obj+"").indexOf(":")!=-1 ){
    			hashMap.put(key,(obj+"").substring(0,(obj+"").length()-2));
    		}else{
    			/*if("null".equals(obj)){//redis无法存储null
					obj="!_#$%_%$#_!";
				}else if(obj==null){
					obj="!_#$%_%$#_!";
				}else if("".equals(obj+"")){
					obj="!_#$%_%$#_!";
				}else if((obj+"").indexOf(" ")!=-1){//redis无法存储空格,加密存储
					obj=(obj+"").replaceAll(" ", "!_#$%%$#_!");
				}*/
    			/*if((obj+"").indexOf(" ")!=-1){//redis无法存储空格,加密存储
    				obj=(obj+"").replace(" ", "!_#$%%$#_!");
				}*/
    			hashMap.put(key, obj);
    		}
    	}
    	return hashMap;
    }
    /**
     * json 转 Map 查询调用
     * @param json(JSONObject)
     * @return Map
     */
    public static Map jsonToMapGet(String json)
    {
    	Map fromObject = JSONObject.fromObject(json);
    	fromObject = toMapGet(fromObject);
    	return fromObject;
    }
    
    /**
     * json 转 Map 插入调用
     * @param json(JSONObject)
     * @return Map
     */
    public static Map jsonToMapSet(String json)
    {
    	Map fromObject = JSONObject.fromObject(json);
    	fromObject = toMapSet(fromObject);
    	return fromObject;
    }
    /**
     * json 转 List<Map>
     * @param json(JSONArray)
     * @return List<Map>
     */
    public static List<Map> jsonToListMap(String json)
    {
    	List<Map> fromArray = JSONArray.fromObject(json);
    	fromArray = toListMap2(fromArray);
    	return fromArray;
    }
    
    /**
     *  redis转换专用
     * json 转 Map
     * @param json(JSONObject)
     * @return Map
     *//*
    public static Map jsonToMap_redis(String json)
    {
    	Map fromObject = JSONObject.fromObject(json);
    	Map<String,String> m = toMap_redis(fromObject);
    	return m;
    }*/
    /**
     *  redis转换专用
     * json 转 List<Map>
     * @param json(JSONArray)
     * @return List<Map>
     */
   /* public static List<Map<String,String>> jsonToListMap_redis(String json)
    {
    	List<Map> fromArray = JSONArray.fromObject(json);
    	List<Map<String,String>> lsm = toListMap_redis(fromArray);
    	return lsm;
    }*/
    
//************************end zcw*******************************************    

   

    /***
     * 将对象转换为Collection对象
     * @param object
     * @return
     */
    public static Collection toCollection(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toCollection(jsonArray);
    }

  
    
    

    /***
     * 将JSON对象数组转换为传入类型的List
     * @param 
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass)
    {
        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将对象转换为传入类型的List
     * @param 
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(Object object, Class<T> objectClass)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将JSON对象转换为传入类型的对象
     * @param 
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T toBean(JSONObject jsonObject, Class<T> beanClass)
    {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将将对象转换为传入类型的对象
     * @param 
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T toBean(Object object, Class<T> beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * @param 泛型T 代表主实体类型
     * @param 泛型D 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName 从实体类在主实体类中的属性名称
     * @param detailClass 从实体类型
     * @return
     */
    public static <T, D> T toBean(String jsonString, Class<T> mainClass,
            String detailName, Class<D> detailClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray = (JSONArray) jsonObject.get(detailName);

        T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
        List<D> detailList = JSONUtils.toList(jsonArray, detailClass);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName, detailList);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * @param 泛型T 代表主实体类型
     * @param 泛型D1 代表从实体类型
     * @param 泛型D2 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName1 从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2 从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @return
     */
    public static <T, D1, D2> T toBean(String jsonString, Class<T> mainClass,
            String detailName1, Class<D1> detailClass1, String detailName2,
            Class<D2> detailClass2)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);

        T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JSONUtils.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JSONUtils.toList(jsonArray2, detailClass2);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }
    
    /***
     * 将JSON文本反序列化为主从关系的实体
     * @param 泛型T 代表主实体类型
     * @param 泛型D1 代表从实体类型
     * @param 泛型D2 代表从实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailName1 从实体类在主实体类中的属性
     * @param detailClass1 从实体类型
     * @param detailName2 从实体类在主实体类中的属性
     * @param detailClass2 从实体类型
     * @param detailName3 从实体类在主实体类中的属性
     * @param detailClass3 从实体类型
     * @return
     */
    public static <T, D1, D2, D3> T toBean(String jsonString,
            Class<T> mainClass, String detailName1, Class<D1> detailClass1,
            String detailName2, Class<D2> detailClass2, String detailName3,
            Class<D3> detailClass3)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        JSONArray jsonArray1 = (JSONArray) jsonObject.get(detailName1);
        JSONArray jsonArray2 = (JSONArray) jsonObject.get(detailName2);
        JSONArray jsonArray3 = (JSONArray) jsonObject.get(detailName3);

        T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
        List<D1> detailList1 = JSONUtils.toList(jsonArray1, detailClass1);
        List<D2> detailList2 = JSONUtils.toList(jsonArray2, detailClass2);
        List<D3> detailList3 = JSONUtils.toList(jsonArray3, detailClass3);

        try
        {
            BeanUtils.setProperty(mainEntity, detailName1, detailList1);
            BeanUtils.setProperty(mainEntity, detailName2, detailList2);
            BeanUtils.setProperty(mainEntity, detailName3, detailList3);
        }
        catch (Exception ex)
        {
            throw new RuntimeException("主从关系JSON反序列化实体失败！");
        }

        return mainEntity;
    }

    /***
     * 将JSON文本反序列化为主从关系的实体
     * @param 主实体类型
     * @param jsonString JSON文本
     * @param mainClass 主实体类型
     * @param detailClass 存放了多个从实体在主实体中属性名称和类型
     * @return
     */
    public static <T> T toBean(String jsonString, Class<T> mainClass,
            HashMap<String, Class> detailClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        T mainEntity = JSONUtils.toBean(jsonObject, mainClass);
        for (Object key : detailClass.keySet())
        {
            try
            {
                Class value = (Class) detailClass.get(key);
                BeanUtils.setProperty(mainEntity, key.toString(), value);
            }
            catch (Exception ex)
            {
                throw new RuntimeException("主从关系JSON反序列化实体失败！");
            }
        }
        return mainEntity;
    }
}