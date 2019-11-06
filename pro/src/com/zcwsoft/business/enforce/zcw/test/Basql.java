package com.zcwsoft.business.enforce.zcw.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Basql {
	private static String BEGIN_F_1 = "<[%";
	private static String END_F_1 = "%]>";
	private static String BEGIN_F_2 = "<[";
	private static String END_F_2 = "]>";
	private static String ADD_SYMBOL = "#_#";
	private static String ADD_SYMBOL_2 = "#__#";
	private static String MARK_F = ":";
	private static final String BRANK_F = " ";
	private static int num = 0;
	private static Map<String,String> typeMap = new HashMap<String,String>();
	

	public static String replaceParam(String sql,Map mapValue){
		sql = converDate(sql,mapValue,BEGIN_F_1,END_F_1,"N");//先处理原生态的
		sql = converDate(sql,mapValue,BEGIN_F_2,END_F_2,null);//继续处理
		return sql;
	}
	
	
	/**
	 * 功能：处理sql 把替换符中的参数标识替换成有效值，并返回一条有效的可执行的sql
	 * @param sql       sql语句
	 * @param mapValue  条件对应字段与参数Map
	 * @param BEGIN_F	替换符 开始
	 * @param END_F		替换符 结束
	 * @param type		类型，转换替换参数 or 不处理替换
	 * @return			处理后可执行的sql
	 */
	public static String converDate(String sql,Map<String, Object> mapValue
									,String BEGIN_F,String END_F,String type){
		try{
			
			sql = sql.replace("：",MARK_F);
			//存放未处理过的条件：<[and param = :param]>
			Map<String,String> mapKey = null;
			//存放已处理后的条件
			Map<String,String> result = new HashMap<String,String>();
			//存放参数的Key
			List<String> lK = new ArrayList<String>();
			int ini = 0;
			for(String key:mapValue.keySet()){
				//把mapValue的Key存储，后面用做于取值Key\
				lK.add(key);
			}
			while(sql.indexOf(BEGIN_F) !=-1&&sql.indexOf(END_F) !=-1){
				if(ini == 0){
					mapKey = new HashMap<String,String>();
				}
				int strSub = sql.indexOf(BEGIN_F);
				int endSub = sql.indexOf(END_F)+END_F.length();
				String value = sql.substring(strSub,endSub);
				//把无效sql条件截取出来替换成#_#?#_#标识符，用于mapValue的Key存放，如果传进来参数为空，下面则截掉该条件
			/*sql = sql.substring(0,strSub) + ADD_SYMBOL+ini+ADD_SYMBOL+
					sql.substring(endSub,sql.length());*/
			if("N".equals(type)){//如果是<[%%]>
				sql = sql.substring(0,strSub) + ADD_SYMBOL_2+num+ADD_SYMBOL_2+
						sql.substring(endSub,sql.length());
				typeMap.put(ADD_SYMBOL_2+num+ADD_SYMBOL_2, value);
				num++;
			}else{//如果是<[]>
				
				sql = sql.substring(0,strSub) + ADD_SYMBOL+ini+ADD_SYMBOL+
						sql.substring(endSub,sql.length());
				mapKey.put(ADD_SYMBOL+ini+ADD_SYMBOL, value);
			}
			ini++;
			}
			if(mapKey==null || "N".equals(type)){return sql;}
			int fs = 0;
			for (int i = 0; i < mapKey.size(); i++) {
				String  flag="",flag2="",paramName_2="",paramName_s_2="";
				flag = ADD_SYMBOL + i + ADD_SYMBOL;
				if(num>fs){
					 flag2 = ADD_SYMBOL_2 + fs + ADD_SYMBOL_2;
					 paramName_s_2 = typeMap.get(flag2);
					 paramName_2 = paramName_s_2.substring(paramName_s_2.indexOf(MARK_F)+1
								,paramName_s_2.indexOf(END_F)-1).trim().toUpperCase();
				}
				//获取未处理的条件： <[and param = :param]>
				String paramName_s = mapKey.get(flag);
				//获取条件中的参数替换标识 ：param
				String paramName = paramName_s.substring(paramName_s.indexOf(MARK_F)+1
						,paramName_s.indexOf(END_F)).trim().toUpperCase();

			
				for (int j = 0; j < lK.size(); j++) {
					//如果传入的参数Key对应下标
					if(lK.get(j).toUpperCase().equals(paramName_2.toUpperCase())
							|| lK.get(j).toLowerCase().equals(paramName_2.toLowerCase())){
						//处理条件，替换参数使其生成有效可执行sql条件
						String value = paramName_s_2.toUpperCase().replace(BEGIN_F_1, "")
									   .replace(END_F_1, "").replace(MARK_F+lK.get(j).toUpperCase()
											   ,mapValue.get(lK.get(j))+""
											   );
						//把有限sql条件加入result中
						sql = sql.replace(flag, flag2);
						result.put(flag2, " "+value+" ");
						fs++;
						//j=lK.size();
						break;
					}
					//如果传入的参数Key对应下标
					else if(lK.get(j).toUpperCase().equals(paramName.toUpperCase())
							|| lK.get(j).toLowerCase().equals(paramName.toLowerCase())){
						//处理条件，替换参数使其生成有效可执行sql条件
						String value = paramName_s.toUpperCase().replace(BEGIN_F, "")
									   .replace(END_F, "").replace(MARK_F+lK.get(j).toUpperCase()
											   //如果type等于N则标识不用转换类型
											   , ("N".equals(type)?mapValue.get(lK.get(j)):
													   getparam(mapValue.get(lK.get(j))))+""
													   );
						//把有限sql条件加入result中
						result.put(flag, " "+value+" ");
						//j=lK.size();
						break;
					}
				}
			}
			for (int k = 0; k < result.size(); k++) {
				String key = ADD_SYMBOL+ k +ADD_SYMBOL;
				sql = sql.replace(key, result.get(key));
			}
		}catch(Exception e){e.printStackTrace();}
		
		
		
		return sql;
	}

	/**
	 * 功能：处理参数类型，转换成数据库对应的参数
	 * @param obj  	传入参数，
	 * @return	   	返回数据库类型
	 */
	private static Object getparam(Object obj) {
		//String rs = "";
		if(obj instanceof Integer){
			obj = obj+"";
		}else if(obj instanceof String){
			obj =" '" +obj+"'";
		}else if(obj instanceof Date){
			obj = new SimpleDateFormat("yyyy-MM-dd").format((Date)obj);
			obj = "to_date('"+obj+"','yyyy-MM-dd')";
		}
		
		return obj;
	}

}
