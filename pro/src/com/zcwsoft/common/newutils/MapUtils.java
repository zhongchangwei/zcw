package com.zcwsoft.common.newutils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.zcwsoft.common.util.StringUtil;
import common.BaseUnit;

/**
 * Map工具类！！！！核心
 *
 * @author zcw
 */
public class MapUtils {

	/**
     * 从map集合中获取属性值
     * 
     * @param <E>
     * @param map
     *            map集合
     * @param key
     *            键对
     * @param defaultValue
     *            默认值
     * @return
     * @author zcw
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final static <E> E get(Map map, Object key, E defaultValue) {
        Object o = map.get(key);
        if (o == null)
            return defaultValue;
        return (E) o;
    }
    /**
     * 功能：加入字段值
     * @param map 页面传回来的JSONmap 
     * @param params 追加key(单数) value(双数)  即key,value,key,value  如果存在就替换（Map自带功能）
     * @return 追加后的map
     */
    @SuppressWarnings("unchecked")
	public static Map put(Map map,String...params){
    	if(params.length>1){//自定义追加
    		for (int i = 0; i < params.length; i=i+2) {
				map.put(params[i].toUpperCase(), params[i+1]);
			}
    	}
    	return map;
    }
    /**
     * 功能：批量追加（新增）
     * @param lsm
     * @param params
     * @return
     */
    public static List<Map> putListAdd(List<Map> lsm,String...params){
    	for (int i = 0; i < lsm.size(); i++) {
			putAdd(lsm.get(i),params);
		}
    	return lsm;
    }

    /**
     * 功能：批量追加（修改）
     * @param lsm
     * @param params
     * @return
     */
    public static List<Map> putListUpdate(List<Map> lsm,String...params){
    	for (int i = 0; i < lsm.size(); i++) {
			putUpdate(lsm.get(i),params);
		}
    	return lsm;
    }
    
    /**
     * 功能：加入字段值，此处默认追加 新增 信息
     * @param map 页面传回来的JSONObject
     * @param params 追加key(单数) value(双数)  即key,value,key,value  如果存在就替换（Map自带功能）
     * @return 追加后的map
     */
    @SuppressWarnings("unchecked")
	public static Map putAdd(Map map,String...params){
    	map.put("PK", BaseSysUtils.getID());//设置主键PK，后续会转换称id,也就是说数据库主键字段必须是ID
    	map.put("CREATE_DATE", getStrDateByNow());//创建时取值_当前时间
    	map.put("CREATE_USERID", BaseSysUtils.getUserId());//创建时取值_当前登录用户id
    	map.put("CREATE_USERNAME", BaseSysUtils.getUserName());//创建时取值_当前登录用户名
    	map.put("STATE", "1");//正常表示 1=正常 0=删除
    	
    	if(params.length>1){//自定义追加
    		for (int i = 0; i < params.length; i=i+2) {
    			map.put(params[i].toUpperCase(), params[i+1]);
    		}
    	}
    	return map;
    }
    /**
     * 功能：加入字段值，此处默认追加 修改 信息
     * @param map 页面传回来的JSONObject
     * @param params 追加key(单数) value(双数)  即key,value,key,value  如果存在就替换（Map自带功能）
     * @return 追加后的map
     */
    @SuppressWarnings("unchecked")
	public static Map putUpdate(Map map,String...params){
    	map.put("MODIFY_DATE", getStrDateByNow());//修改时取值_当前时间
    	map.put("MODIFY_USERID", BaseSysUtils.getUserId());//修改时取值_当前用户id
    	
    	if(params.length>1){//自定义追加
    		for (int i = 0; i < params.length; i=i+2) {
    			map.put(params[i].toUpperCase(), params[i+1]);
    		}
    	}
    	return map;
    }
    
    /**
     * 功能：获取当前时间并转换成string 例如：2017-11-29 21:27:32
     * @return
     */
    public static String getStrDateByNow(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    /**
     * Map集合对象转化成 JavaBean集合对象
     * 
     * @param javaBean JavaBean实例对象
     * @param mapList Map数据集对象
     * @return
     * @author zcw
     */
   // @SuppressWarnings({ "rawtypes" })
 /*   public static <T> List<T> getBeanByMap(T javaBean, List<Map> mapList) {
        if(mapList == null || mapList.isEmpty()){
            return null;
        }
        List<T> objectList = new ArrayList<T>();
        
        T object = null;
        for(Map map : mapList){
            if(map != null){
                object = getBeanByMapNotConverType(javaBean, map);
                objectList.add(object);
            }
        }
        
        return objectList;
        
    }*/
    
    /**
     * Map对象转化成 JavaBean对象
     * 
     * @param javaBean JavaBean实例对象
     * @param map Map对象
     * @return
     * @author zcw
     */
    @SuppressWarnings({ "rawtypes","unchecked", "hiding" })
    public static <T> T getBeanByMapNotConverType(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * JavaBean对象转化成Map对象
     * 
     * @param javaBean
     * @return
     * @author zcw
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map beanConverMap(Object javaBean) {
        Map map = new HashMap();
         
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                String propertyName = null; // javaBean属性名
                Object propertyValue = null; // javaBean属性值
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean, new Object[0]);
                        	map.put(propertyName, propertyValue);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }  
        
        return map;
    }
    
    /**List<bean实体>转List<Map>
     * @author zcw
     * 2017-11-8 21:31:50
     * @param <T>
     * @param <T>
     * @param bean
     * @param request
     */
    public static <T> List<Map<String, Object>> getListMapByListBean(List<T> ls){
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	
    	for (int i = 0; i < ls.size(); i++) {
    		list.add(beanConverMap(ls.get(i)));
		}
    	
    	return list;
    }
    
    
    /**List<Map>转List<bean实体> 并自动匹配参数赋值到实体，需可转换的参数
     * @author zcw
     * 2017-11-8 21:31:50
     * @param <T>
     * @param bean
     * @param request
     */
    public static <T> List<T> getListBeanByMap(T bean, List<Map> ls,String...type)throws Exception{
    	List<T> list = new ArrayList<T>();
    	for (int i = 0; i < ls.size(); i++) {
    		list.add(getBeanByMap(bean,ls.get(i),type));
		}
    	
    	return list;
    }
    
    
    
    /**
     * ***级别*** 核心方法，增 该操作核心转换
     * 自动匹配参数赋值到实体bean中
     * @author zcw
     * 2014-2-16 下午10:23:37
     * @param bean
     * @param type   pk 	》 map中的键为PK的赋值到实体ID中
     * 				 update 》不要创建新实例
     * @param request
     */
    public static <T> T getBeanByMap(T t, Map map,String...type)throws Exception{
    	Object bean = t;
    	Class clazz = null;
    	//如果页面穿回来的值有PK(修改操作)
    	if(type.length>0&&"UPDATE".equals(type[0].toUpperCase())){
    		clazz = t.getClass();
    	}else{
    		try {
    			bean = Class.forName(t.getClass().getName()).newInstance();
    			clazz = bean.getClass();
    		} catch (InstantiationException e1) {
    			e1.printStackTrace();
    		} catch (IllegalAccessException e1) {
    			e1.printStackTrace();
    		} catch (ClassNotFoundException e1) {
    			e1.printStackTrace();
    		}
    	}
        Method ms[] = clazz.getDeclaredMethods();    
        final String replace="_" ;
        String mname;
        String field;
        String fieldType;
        String value;
        //因为bean中的字段习惯去掉数据库字段 _ 拼凑的,而数据库取出的key是带 _ 的，所以要去掉
        map = replaceKeyByMap(map,replace);
        
        for(Method m : ms){
            mname = m.getName();
            if(!mname.startsWith("set")
                    || ArrayUtils.isEmpty(m.getParameterTypes())){
                continue;
            }
            try{
                field = mname.toLowerCase().charAt(3) + mname.substring(4, mname.length());
                //此处用于保存，修改时页面穿回来MAP中的key id为pk，，所以需要替换实体中的id变成pk才能跟map匹配
                if("ID".equals(field.toUpperCase())&&type.length>0&&("PK".equals(type[0].toUpperCase())||"UPDATE".equals(type[0].toUpperCase()))){
                	field="PK";
                	}
                String re = field.toUpperCase().replace(replace,"");
                value = map.get(re)+"";
                
                fieldType = m.getParameterTypes()[0].getName();
                //如果map中值是空
                if(StringUtils.isEmpty(value)||"null".equals(value)){
                	//如果是修改操作，且map中有该KEY，因为修改时候可能修改成空的值，进入if就不执行continue
                	if(type.length>0&&"UPDATE".equals(type[0].toUpperCase())&&map.containsKey(re)&&!"null".equals(value)){
                		//如果值为空，实体类型是时间类型，返回不赋值。因为null 或空 是无法插入时间类型的字段，会报错
                		//if(Date.class.getName().equals(fieldType)||Timestamp.class.getName().equals(fieldType)){
                			//时间类型则返回
                			//continue;
                		//}
                	}else{
                		continue;
                	}
                }
                //以下可以确认value为String类型
                if(String.class.getName().equals(fieldType)){
                    m.invoke(bean, (String)value);
                }else if(Integer.class.getName().equals(fieldType) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Integer.valueOf((String)value));
                }else if(int.class.getName().equals(fieldType) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Integer.valueOf((String)value));
                }else if(Short.class.getName().equals(fieldType) && NumberUtils.isDigits((String)value)){
                    m.invoke(bean, Short.valueOf((String)value));
                }else if(Float.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Float.valueOf((String)value));
                }else if(Double.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, Double.valueOf((String)value));
                }else if(BigDecimal.class.getName().equals(fieldType) && NumberUtils.isNumber((String)value)){
                    m.invoke(bean, BigDecimal.valueOf(Double.valueOf((String)value)));
                }else if(Date.class.getName().equals(fieldType)){
                    m.invoke(bean, strToDate(value));
                }else{
                    m.invoke(bean, value);
                }
            }catch(Exception e){
            	//e.printStackTrace();
                //continue;
                throw new Exception(e);
            }
        }
        return (T)bean;
    }
    /**
	 * 处理map中的Key，把下划线 _ 并去掉
	 * @param map
	 * @return
	 */
	public static Map<String,Object> replaceKeyByMap(Map<String,Object> map,String replace){
		Map<String,Object> m =new HashMap<String,Object>();
		for(String key:map.keySet()){
				m.put(key.replace(replace, ""), map.get(key));
		}
		return m;
	}
	
	/**
	 * 处理时间
	 * @param map
	 * @return
	 */
	public static Date strToDate(String strDate){
		try {
			
			if(strDate.length()>10){
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate.replace("T", " "));
			}else{
				return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取一个实体，根据实体路径
	 * @param className
	 * @return
	 */
	public static Object getInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return null;
	}
    
 }