package com.zcwsoft.common.pub.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/** 
 * @author 关德辉
 * @version 1.0
 * @datetime 2012-2-05 下午03:23:18 
 * 类说明 
 */
public class JackJson {
	
	
	public static String getBasetJsonData(Object obj){
		StringWriter writer = new StringWriter();
		if(obj != null){
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));  
			try {
				mapper.writeValue(writer, obj);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.toString();
	
    } 
	
	/*
	 * author: 关德辉
	 * Date: 2013-01-29
	 * describe: 将含单条记录的json字符串转换成bean
	 */
	public static Object readJson2Entity(String json, Class objClass) {
		//若throw会造成500错误
		Object obj=null;
		try {
			obj = Class.forName(objClass.getName()).newInstance();
		} catch (InstantiationException e2) {
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m;
		try {
			m = mapper.readValue(json, Map.class);
			List<Method> setMethods = new ArrayList<Method>();
			List<String> Fields = new ArrayList<String>();
			
			
			java.lang.reflect.Method[] ms = objClass.getMethods();
			for (Method method : ms) {
				// 过滤所有set方法
				String methodName = method.getName();
				if (methodName.startsWith("set")) {
					// 根据set方法获取属性名称
					StringBuffer fieldName = new StringBuffer(methodName.substring(3));
					Object value = m.get(fieldName.toString());
					if (value == null) {
						char ch = fieldName.charAt(0);
						// 转换第一个字符的大小写
						ch = (char) (ch >= 'A' && ch <= 'Z' ? ch + 32 : ch - 32);
						fieldName.setCharAt(0, ch);
						value = m.get(fieldName.toString());
					}
					if (value != null) {
						try {
							// 保存对应关系
							setMethods.add(method);
							Fields.add(fieldName.toString());
							if(method.toString().indexOf("(java.lang.String)") != -1 ){
								method.invoke(obj, new Object[] { value.toString() });
							}else{
								if(!StringUtil.checkObj(value.toString())){continue;}
								if(method.toString().indexOf("java.lang.Integer") != -1 ){									
									method.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
								}else if(method.toString().indexOf("(java.lang.Long)") != -1 ){
									method.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
								}else if(method.toString().indexOf("(java.lang.Double)") != -1 ){
									method.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
								}else if(method.toString().indexOf("(java.util.Date)") != -1 ){
									method.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd mm:ss") });
								}
							}
							
						} catch (Exception e) {
							//System.out.println(fieldName);
							e.printStackTrace();
						}
					}
				}
			}
		} catch (JsonParseException e1) {
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return obj;
	}
	
	
	/*
	 * author: 关德辉
	 * Date: 2013-01-29
	 * describe: 将含多条记录的json字符串转换成bean数组
	 */
	public static List<Object> readJson2EntityList(String json, Class objClass){
		List<Object> tempList = new ArrayList<Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		List<Method> setMethods = null;
		List<String> Fields = null;
		try {
			//把json格式的字符串转换成数组
			List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
			for (int i = 0; i < list.size(); i++) {
				//若throw会造成500错误
				Object obj=null;
				try {
					obj = Class.forName(objClass.getName()).newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				//PackageInput obj = new PackageInput();
				Map<String, Object> m = list.get(i);
				if (setMethods == null) {
					// 保存对应关系
					setMethods = new ArrayList<Method>();
					Fields = new ArrayList<String>();
					java.lang.reflect.Method[] ms = objClass.getMethods();
					for (Method method : ms) {
						// 过滤所有set方法
						String methodName = method.getName();
						if (methodName.startsWith("set")) {
							// 根据set方法获取属性名称
							StringBuffer fieldName = new StringBuffer(methodName.substring(3));
							Object value = m.get(fieldName.toString());
							if (value == null) {
								char ch = fieldName.charAt(0);
								// 转换第一个字符的大小写
								ch = (char) (ch >= 'A' && ch <= 'Z' ? ch + 32 : ch - 32);
								fieldName.setCharAt(0, ch);
								value = m.get(fieldName.toString());
							}
							if (value != null) {
								try {
									// 保存对应关系
									setMethods.add(method);
									Fields.add(fieldName.toString());
									if(method.toString().indexOf("(java.lang.String)") != -1 ){
										method.invoke(obj, new Object[] { value.toString() });
									}else{
										if(!StringUtil.checkObj(value.toString())){continue;}
										if(method.toString().indexOf("java.lang.Integer") != -1 ){									
											method.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Long)") != -1 ){
											method.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Double)") != -1 ){
											method.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
										}else if(method.toString().indexOf("(java.util.Date)") != -1 ){
											//时间转换为年月日格式、可另外做一个方法判断时间字符串是“年月日”、还是“年月日 时分秒”，再分别进行格式化，需要与下面缓存对应
											method.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd") });
										}
									}
								} catch (Exception e) {
									//System.out.println(fieldName);
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					// 直接从缓存取
					for (int j = 0; j < setMethods.size(); j++) {
						Method mt = setMethods.get(j);
						String fieldName = Fields.get(j);
						Object value = m.get(fieldName);
						if (value != null) {
							try {
								//System.out.println(fieldName);
								if(mt.toString().indexOf("(java.lang.String)") != -1 ){
									mt.invoke(obj, new Object[] { value.toString() });
								}else{
									if(!StringUtil.checkObj(value.toString())){continue;}
									if(mt.toString().indexOf("java.lang.Integer") != -1 ){									
										mt.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Long)") != -1 ){
										mt.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Double)") != -1 ){
										mt.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
									}else if(mt.toString().indexOf("(java.util.Date)") != -1 ){
										//时间转换为年月日格式、可另外做一个方法判断时间字符串是“年月日”、还是“年月日 时分秒”，再分别进行格式化
										mt.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd") });
									}
								}
							} catch (Exception e) {
								//System.out.println(fieldName);
								e.printStackTrace();
							}
						}
					}
				}
	            
	            tempList.add(obj);
	        }
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempList;
	}
	
	
	/*
	 * author: 关德辉
	 * Date: 2013-01-29
	 * describe: 将含多条记录的json字符串转换成bean数组
	 */
	public static List<Object> readJson2EntityList2(String json, String objClassName,Method[] ms){
		List<Object> tempList = new ArrayList<Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		List<Method> setMethods = null;
		List<String> Fields = null;
		try {
			//把json格式的字符串转换成数组
			List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
			for (int i = 0; i < list.size(); i++) {
				//若throw会造成500错误
				Object obj=null;
				try {
					obj = Class.forName(objClassName).newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				//PackageInput obj = new PackageInput();
				Map<String, Object> m = list.get(i);
				if (setMethods == null) {
					// 保存对应关系
					setMethods = new ArrayList<Method>();
					Fields = new ArrayList<String>();
					for (Method method : ms) {
						// 过滤所有set方法
						String methodName = method.getName();
						if (methodName.startsWith("set")) {
							// 根据set方法获取属性名称
							StringBuffer fieldName = new StringBuffer(methodName.substring(3));
							Object value = m.get(fieldName.toString());
							if (value == null) {
								char ch = fieldName.charAt(0);
								// 转换第一个字符的大小写
								ch = (char) (ch >= 'A' && ch <= 'Z' ? ch + 32 : ch - 32);
								fieldName.setCharAt(0, ch);
								value = m.get(fieldName.toString());
							}
							if (value != null) {
								try {
									// 保存对应关系
									setMethods.add(method);
									Fields.add(fieldName.toString());
									if(method.toString().indexOf("(java.lang.String)") != -1 ){
										method.invoke(obj, new Object[] { value.toString() });
									}else{
										if(!StringUtil.checkObj(value.toString())){continue;}
										if(method.toString().indexOf("java.lang.Integer") != -1 ){									
											method.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Long)") != -1 ){
											method.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Double)") != -1 ){
											method.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
										}else if(method.toString().indexOf("(java.util.Date)") != -1 ){
											method.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd mm:ss") });
										}
									}
								} catch (Exception e) {
									//System.out.println(fieldName);
									e.printStackTrace();
								}
							}
						}
					}
				} else {
					// 直接从缓存取
					for (int j = 0; j < setMethods.size(); j++) {
						Method mt = setMethods.get(j);
						String fieldName = Fields.get(j);
						Object value = m.get(fieldName);
						if (value != null) {
							try {
								//System.out.println(fieldName);
								if(mt.toString().indexOf("(java.lang.String)") != -1 ){
									mt.invoke(obj, new Object[] { value.toString() });
								}else{
									if(!StringUtil.checkObj(value.toString())){continue;}
									if(mt.toString().indexOf("java.lang.Integer") != -1 ){									
										mt.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Long)") != -1 ){
										mt.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Double)") != -1 ){
										mt.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
									}else if(mt.toString().indexOf("(java.util.Date)") != -1 ){
										mt.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd mm:ss") });
									}
								}
							} catch (Exception e) {
								//System.out.println(fieldName);
								e.printStackTrace();
							}
						}
					}
				}
	            
	            tempList.add(obj);
	        }
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempList;
	}
	
	//json格式的字符串转换成List
	public static void readJson2List() {
		ObjectMapper mapper = new ObjectMapper();
	    String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
	                "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
	    try {
	        List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
	        System.out.println(list.size());
	        for (int i = 0; i < list.size(); i++) {
	            Map<String, Object> map = list.get(i);
	            Set<String> set = map.keySet();
	            for (Iterator<String> it = set.iterator();it.hasNext();) {
	                String key = it.next();
	                System.out.println(key + ":" + map.get(key));
	            }
	        }
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	
	//json格式的字符串转换成Map
	public static void readJson2Map() {
		ObjectMapper mapper = new ObjectMapper();
	    String json = "{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}";
	    try {
	        Map<String, Object> map = mapper.readValue(json, Map.class);
	        Set<String> set = map.keySet();
            for (Iterator<String> it = set.iterator();it.hasNext();) {
                String key = it.next();
                System.out.println(key + ":" + map.get(key));
            }
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		/*Date d1 = new Date();
		Map map = new HashMap();
		map.put("a", "tes1");
		map.put("b", "12");
		List list = new ArrayList();
		list.add(map);
		Date d2 = new Date();
		System.out.println("装载对象：" + StringUtil.getTimeInMillis(d1, d2));
		
		Date d3 = new Date();
		String str = getBasetJsonData(list);
		Date d4 = new Date();
	    System.out.println("转换json：" + StringUtil.getTimeInMillis(d3, d4) + str);
		
		readJson2List();*/
		
		/* readJson2EntityList测试
		String json = "[{\"totalCount\":\"2\",\"TotalPage\":\"1\",\"iRowId\":\"1\",\"IID\":\"1\",\"DDATE\":\"2013-01-28 00:00\",\"SSIGNUSER\":\"何海涛\",\"ISIGNUSERID\":\"4\",\"SCREATOR\":\"关德辉\",\"ICREATUSERID\":\"2\",\"DCREATEDATE\":\"2013-01-28 00:00\",\"SEDITOR\":\"关德辉\",\"IEDITUSERID\":\"2\",\"DEDITDATE\":\"2013-01-28 00:00\",\"SREMARK\":\"123\"},{\"totalCount\":\"2\",\"TotalPage\":\"1\",\"iRowId\":\"2\",\"IID\":\"2\",\"DDATE\":\"2013-01-29 10:33\",\"SSIGNUSER\":\"2\",\"ISIGNUSERID\":\"2\",\"SCREATOR\":\"2\",\"ICREATUSERID\":\"2\",\"DCREATEDATE\":\"2013-01-29 10:33\",\"SEDITOR\":\"2\",\"IEDITUSERID\":\"2\",\"DEDITDATE\":\"2013-01-29 10:33\",\"SREMARK\":\"2\"}]";
		List<Object> list = readJson2EntityList(json, PackageInput.class);
		for(int i=0; i<list.size(); i++){
			PackageInput pkgInput = (PackageInput) list.get(i);
			System.out.println(pkgInput.getIID());
		}
		*/
		/*
		String json = "{\"totalCount\":\"2\",\"TotalPage\":\"1\",\"iRowId\":\"1\",\"IID\":\"1\",\"DDATE\":\"2013-01-28 00:00\",\"SSIGNUSER\":\"何海涛\",\"ISIGNUSERID\":\"4\",\"SCREATOR\":\"关德辉\",\"ICREATUSERID\":\"2\",\"DCREATEDATE\":\"2013-01-28 00:00\",\"SEDITOR\":\"关德辉\",\"IEDITUSERID\":\"2\",\"DEDITDATE\":\"2013-01-28 00:00\",\"SREMARK\":\"123\"}";
		PackageInput pkgInput = (PackageInput)readJson2Entity(json, PackageInput.class);
		System.out.println(pkgInput.getIID());
		*/
		System.out.println(Double.parseDouble(""));
	}
	
	/*
	 * author: 李志鹏
	 * Date: 2015-10-19
	 * describe: 将含多条记录的json字符串转换成bean数组
	 * 原来的在mini UI的批量保存在如果同时存在新增和修改的情况下，修改的数据的主键会被重置成空，多新增一条修改的信息
	 */
	public static List<Object> readJson2EntityList3(String json, Class objClass){
		List<Object> tempList = new ArrayList<Object>();
		ObjectMapper mapper = new ObjectMapper();
		
		List<Method> setMethods = null;
		List<String> Fields = null;
		try {
			//把json格式的字符串转换成数组
			List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
			for (int i = 0; i < list.size(); i++) {
				//若throw会造成500错误
				Object obj=null;
				try {
					obj = Class.forName(objClass.getName()).newInstance();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				//PackageInput obj = new PackageInput();
				Map<String, Object> m = list.get(i);
				if (setMethods == null) {
					// 保存对应关系
					setMethods = new ArrayList<Method>();
					Fields = new ArrayList<String>();
					java.lang.reflect.Method[] ms = objClass.getMethods();
					for (Method method : ms) {
						// 过滤所有set方法
						String methodName = method.getName();
						if (methodName.startsWith("set")) {
							// 根据set方法获取属性名称
							StringBuffer fieldName = new StringBuffer(methodName.substring(3));
							Object value = m.get(fieldName.toString());
							if (value == null) {
								char ch = fieldName.charAt(0);
								// 转换第一个字符的大小写
								ch = (char) (ch >= 'A' && ch <= 'Z' ? ch + 32 : ch - 32);
								fieldName.setCharAt(0, ch);
								value = m.get(fieldName.toString());
							}
							if (value != null) {
								try {
									// 保存对应关系
									setMethods.add(method);
									Fields.add(fieldName.toString());
									if(method.toString().indexOf("(java.lang.String)") != -1 ){
										method.invoke(obj, new Object[] { value.toString() });
									}else{
										if(!StringUtil.checkObj(value.toString())){continue;}
										if(method.toString().indexOf("java.lang.Integer") != -1 ){									
											method.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Long)") != -1 ){
											method.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
										}else if(method.toString().indexOf("(java.lang.Double)") != -1 ){
											method.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
										}else if(method.toString().indexOf("(java.util.Date)") != -1 ){
											//时间转换为年月日格式、可另外做一个方法判断时间字符串是“年月日”、还是“年月日 时分秒”，再分别进行格式化，需要与下面缓存对应
											method.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd") });
										}
									}
								} catch (Exception e) {
									//System.out.println(fieldName);
									e.printStackTrace();
								}
							}
						}
					}
					//修改部分,modify by lizhipeng 
					setMethods = null;
				} else {
					// 直接从缓存取
					for (int j = 0; j < setMethods.size(); j++) {
						Method mt = setMethods.get(j);
						String fieldName = Fields.get(j);
						Object value = m.get(fieldName);
						if (value != null) {
							try {
								//System.out.println(fieldName);
								if(mt.toString().indexOf("(java.lang.String)") != -1 ){
									mt.invoke(obj, new Object[] { value.toString() });
								}else{
									if(!StringUtil.checkObj(value.toString())){continue;}
									if(mt.toString().indexOf("java.lang.Integer") != -1 ){									
										mt.invoke(obj, new Object[] { Integer.parseInt(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Long)") != -1 ){
										mt.invoke(obj, new Object[] { Long.parseLong(value.toString()) });
									}else if(mt.toString().indexOf("(java.lang.Double)") != -1 ){
										mt.invoke(obj, new Object[] { Double.parseDouble(value.toString()) });
									}else if(mt.toString().indexOf("(java.util.Date)") != -1 ){
										//时间转换为年月日格式、可另外做一个方法判断时间字符串是“年月日”、还是“年月日 时分秒”，再分别进行格式化
										mt.invoke(obj, new Object[] { DateUtil.parseToDate(value.toString(), "yyyy-MM-dd") });
									}
								}
							} catch (Exception e) {
								//System.out.println(fieldName);
								e.printStackTrace();
							}
						}
					}
				}
	            
	            tempList.add(obj);
	        }
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempList;
	}
}