package com.zcwsoft.common.newutils.redisutils.where;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;

public class WhereOperation {

	/** 大于 */
	public static final String FYMOBL_GREATER = ">";
	/** 小于*/
	public static final String FYMOBL_LESS = "<";
	/** 等于 */
	public static final String FYMOBL_EQUAL = "=";
	/** 不等于 */
	public static final String FYMOBL_NOTEQUAL = "!=";
	/** 组合查询拼接符号 */
	public static final String FYMOBL_GROUP = "&&";
	/** 日期查询符号 */
	public static final String FYMOBL_DATE = "$d$";
	/**	 模糊查询符号	 */
	public static final String FYMOBL_LIKE = "%*%";
	/**	 分隔符	 */
	public static final String FYMOBL_SPRIT = ",";
	
	
	/** 全局变量，是否日期时间戳*/
	public static boolean isDateTime =false;	
	
	/**
	 * 用于匹配日期	格式：yyyy-MM-dd
	 * <br> $d$2017-03-01	或	$d$2017-03-01~2017-04-01<br>
	 * @param Eachevalue
	 * @param whereValue
	 * @return
	 */
	public static boolean checkDateEq(String Eachevalue,String whereValue){
		//boolean flag  = true;
		if(whereValue.indexOf("/") != -1)
		{
			whereValue = whereValue.replace("/", "-");
		}
		if(Eachevalue.indexOf(" ") == -1 && whereValue.indexOf(" ") == -1 && whereValue.indexOf("-") == -1)
		{//如果成立，不是时间格式yyyy-MM-dd
			return false;
		}
		whereValue = whereValue.replace(FYMOBL_DATE, "");
		/*if(Eachevalue.contains("2017-03-14")){
			System.out.println(Eachevalue);
		}*/
		Date eacheValueDate = null;
		Date whereValueDateStr = null;
		Date whereValueDateEnd = null;
		eacheValueDate = checkDateType(Eachevalue);
		if(whereValue.indexOf("~") != -1){//证明是组合查询
			String[] split = whereValue.split("~");
			whereValueDateStr = checkDateType(split[0]);
			whereValueDateEnd = checkDateType(split[1]);
		}else{
			whereValueDateStr = checkDateType(whereValue);
		}
		 if(eacheValueDate != null && whereValueDateStr != null){
			 if(whereValueDateEnd!=null){//是组合查询
				 if(eacheValueDate.getTime() >= whereValueDateStr.getTime() 
					&& eacheValueDate.getTime() <= whereValueDateEnd.getTime()){
					 return true;
				 }else{
					 return false;
				 }
			 }else{//单查询
				 //System.out.println("记录：eacheValueDate="+eacheValueDate.getTime()+"||whereValueDateStr"+whereValueDateStr.getTime());
				 if(eacheValueDate.getTime() == whereValueDateStr.getTime() ){
					 return true;
				 }else{
					 return false;
				 }
			 }
			 
		 }else{
			 return false;
		 }
		
		
		
		//return flag;
	}
	/**
	 * 日期类型检查处理，传入String格式的日期返回Date时间格式
	 * <br>	如果出现2017-2-1  to：2017-02-01		2017-02-01 00:00:00 to 2017-02-01
	 * @param date
	 * @return
	 */
	public static Date checkDateType(String date){
		String rsDate = null;
		if(date.indexOf("T") != -1)
		{
			date = date.replace("T", " ");//防止时间戳
		}
		if(date.indexOf("-") != -1)
		{
			if(date.length()<11)
			{//可能是9位数，8位
				String[] s1 = date.split("-");
				if(s1.length==3){
					if(s1[1].length()!=2){
						s1[1] = "0"+s1[1];
					}
					if(s1[2].length()!=2){
						s1[2] = "0"+s1[2];
					}
				}
				rsDate = s1[0]+"-"+s1[1]+"-"+s1[2] + " 00:00:00";
			}else{
				if(date.indexOf(" ")!=-1){
					String[] s2 = date.split(" ");
					if(s2[0].indexOf("-") != -1){
						String[] s1 = s2[0].split("-");
						if(s1.length==3){
							if(s1[1].length()<2){
								s1[1] = "0"+s1[1];
							}
							if(s1[2].length()<2){
								s1[2] = "0"+s1[2];
							}
						}
						rsDate = s1[0]+"-"+s1[1]+"-"+s1[2] + " 00:00:00";
					}
				}
			}
		}
		try {
			return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(rsDate);
		} catch (Exception e) {
			System.out.println("标识符："+FYMOBL_DATE+"中时间格式有误："+date);
		}
		return null;
	}
	/**
	 * 匹配不等于某某某
	 * 不等于就获取.
	 * (逗号隔开，可多值检测，首端加上!=即可,类似数据库中的not in)	
	 * <br> !=a	或	!=a,b,c,d	或   !=1,2,3,4  <br>
	 * @param Eachevalue
	 * @param whereValue
	 * @return
	 */
	public static boolean checkNotEq(String Eachevalue,String whereValue){
		boolean f = false;
		 boolean flag  = true;
		 whereValue = whereValue.replace(FYMOBL_NOTEQUAL, "");
		 if(whereValue.indexOf("，") != -1)
		 {
			 whereValue = whereValue.replace("，", ",");
		 }
		 if(StringUtils.isNotBlank(whereValue))
		 {
			  String[] split = whereValue.split(",");
			  if(split.length ==0 && whereValue.equals(Eachevalue))
			  {
				  f  = true;
			  }
				  for (int k = 0; k < split.length; k++) 
				  {
					  if(Eachevalue.equals(split[k]))
					  {
						  f = true;
						  break;
					  }
				}
		    if(f)
		    {
		    	flag = false;
		    }
		}else{
			if(Eachevalue.equals(whereValue))
			  {
				  f = false;
			  }
		}
	    return flag;
	}
	
	/**
	 * 匹配等于某某某
	 * 等于就获取
	 * <br>(逗号隔开，可多值检测，类似数据库中的in)
	 * <br>	a,b,c,d	或 1,2,3,4	<br>
	 * @param Eachevalue
	 * @param whereValue
	 * @return
	 */
	public static boolean checkEq(String Eachevalue,String whereValue){
		 boolean f = false;
		 boolean flag  = true;
		 if(whereValue.indexOf("，") != -1)
		 {
			 whereValue = whereValue.replace("，", ",");
		 }
		 if(StringUtils.isNotBlank(whereValue))
		 {
			  String[] split = whereValue.split(",");
			  if(split.length ==0 && whereValue.equals(Eachevalue))
			  {
				  f  = true;
			  }
				  for (int k = 0; k < split.length; k++) 
				  {
					  if(Eachevalue.equals(split[k]))
					  {
						  f = true;
						  break;
					  }
				}
			 
		    if(!f)
		    {
		    	flag = false;
		    }
		}else{
			if(!Eachevalue.equals(whereValue))
			  {
				flag = false;
			  }
		}
	    return flag;
	}

	/**
	 * 检测缓存与条件是否匹配（大于、 大于等于 、小于、 小于等于）
	 * @param Eachevalue	缓存取值
	 * @param whereValue	数组类型:双数=条件，单数=值<br>
	 * 						值样例：<50&&>30  就获取31到49的值  || <=50&&>=30就获取30-50的值	|| <30 || <=30 || >30 || >=30
	 * @return
	 */
	public static boolean checkGreaterAndLess(String Eachevalue,String whereValue){
		boolean flag = false;
		boolean isGroup = false;//是否组合查询 true；是  false；否
		 isDateTime = true;//是否日期时间戳，带时分秒	带=true 不带=false
		Object  objMax = null;
		Object  objMin = null;
		Object  objMaxEq = null;
		Object  objMinEq = null;
		
		if(whereValue.indexOf(FYMOBL_GROUP)!=-1)
		{//组合查询
			isGroup = true;
			String[] sp = whereValue.split(FYMOBL_GROUP);
			if(sp[0].indexOf("-") != -1 && sp[0].length()<13){//时间
				isDateTime = false;
			}
			if(sp[0].indexOf(FYMOBL_GREATER) != -1){
				if(sp[0].indexOf(FYMOBL_GREATER+FYMOBL_EQUAL) != -1){
					String  maxEq = sp[0].split(FYMOBL_GREATER+FYMOBL_EQUAL)[1];
					objMaxEq = checkConvert(maxEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  max = sp[0].split(FYMOBL_GREATER)[1];
					objMax = checkConvert(max);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
			}else{
				if(sp[0].indexOf(FYMOBL_LESS+FYMOBL_EQUAL) != -1){
					String  minEq = sp[0].split(FYMOBL_LESS+FYMOBL_EQUAL)[1];
					objMinEq = checkConvert(minEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  min = sp[0].split(FYMOBL_LESS)[1];
					objMin = checkConvert(min);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
			}
			if(sp[1].indexOf(FYMOBL_LESS) != -1){
				if(sp[1].indexOf(FYMOBL_LESS+FYMOBL_EQUAL) != -1){
					String  minEq = sp[1].split(FYMOBL_LESS+FYMOBL_EQUAL)[1];
					objMinEq = checkConvert(minEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  min = sp[1].split(FYMOBL_LESS)[1];
					objMin = checkConvert(min);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
			}else{
				if(sp[1].indexOf(FYMOBL_GREATER+FYMOBL_EQUAL) != -1){
					String  maxEq = sp[1].split(FYMOBL_GREATER+FYMOBL_EQUAL)[1];
					objMaxEq = checkConvert(maxEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  max = sp[1].split(FYMOBL_GREATER)[1];
					objMax = checkConvert(max);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
			}
		}else{//普通查询
			if(whereValue.indexOf("-") != -1 && whereValue.length()<13){//时间
				isDateTime = false;
			}
			
			if(whereValue.indexOf(FYMOBL_GREATER) != -1){
				if(whereValue.indexOf(FYMOBL_GREATER+FYMOBL_EQUAL) != -1){
					String  maxEq = whereValue.split(FYMOBL_GREATER+FYMOBL_EQUAL)[1];
					objMaxEq = checkConvert(maxEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  max = whereValue.split(FYMOBL_GREATER)[1];
					objMax = checkConvert(max);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
				
			}else if(whereValue.indexOf(FYMOBL_LESS) != -1){
				if(whereValue.indexOf(FYMOBL_LESS+FYMOBL_EQUAL) != -1){
					String  minEq = whereValue.split(FYMOBL_LESS+FYMOBL_EQUAL)[1];
					objMinEq = checkConvert(minEq);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}else{
					String  min = whereValue.split(FYMOBL_LESS)[1];
					objMin = checkConvert(min);//进行转换，返回相应的类型int(date),只有这2种类型才能大于小于取值
				}
			}
		}
		/**
		 
		 */
		if(isGroup){
			if(objMaxEq!=null && objMinEq!=null)
			{
				if(objMaxEq instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) >= (Integer)objMaxEq && Integer.parseInt(Eachevalue) <= (Integer)objMinEq)
					{
						flag = true;
					}
				}else if(objMaxEq instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() >= ((Date)objMaxEq).getTime() && parse.getTime() <= ((Date)objMinEq).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMaxEq!=null && objMin!=null)
			{
				if(objMaxEq instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) >= (Integer)objMaxEq && Integer.parseInt(Eachevalue) < (Integer)objMin)
					{
						flag = true;
					}
				}else if(objMaxEq instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() >= ((Date)objMaxEq).getTime() && parse.getTime() < ((Date)objMin).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMax!=null && objMinEq!=null)
			{
				if(objMax instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) > (Integer)objMax && Integer.parseInt(Eachevalue) <= (Integer)objMinEq)
					{
						flag = true;
					}
				}else if(objMax instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() > ((Date)objMax).getTime() && parse.getTime() <= ((Date)objMinEq).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMax!=null && objMin!=null)
			{
				if(objMax instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) > (Integer)objMax && Integer.parseInt(Eachevalue) < (Integer)objMin)
					{
						flag = true;
					}
				}else if(objMax instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() > ((Date)objMax).getTime() && parse.getTime() < ((Date)objMin).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else{//都不成立直接返回：不匹配
				return false;
			}
		}else{
			if(objMaxEq!=null)
			{
				if(objMaxEq instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) >= (Integer)objMaxEq )
					{
						flag = true;
					}
				}else if(objMaxEq instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() >= ((Date)objMaxEq).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMinEq!=null)
			{
				if(objMinEq instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) <= (Integer)objMinEq )
					{
						flag = true;
					}
				}else if(objMinEq instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() <= ((Date)objMinEq).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMax!=null)
			{
				if(objMax instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) > (Integer)objMax )
					{
						flag = true;
					}
				}else if(objMax instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() > ((Date)objMax).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}else if(objMin!=null)
			{
				if(objMin instanceof Integer)
				{
					if(Integer.parseInt(Eachevalue) < (Integer)objMin )
					{
						flag = true;
					}
				}else if(objMin instanceof Date)
				{
					Date parse =(Date)checkConvert(Eachevalue);
					if(parse != null && parse.getTime() < ((Date)objMin).getTime())
					{
						flag = true;
					}
				}else{//2种数据都不能转换，证明输入参数有误
					return false;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 检测数据是否可以强转，
	 * @param str	入参，字符串数据格式，可以是int类型：20 11 135 或日期类型：则  2017-02-06   2018-3-16 14:25:25
	 * @return
	 */
	public static Object checkConvert(String str){
		if(isDateTime==false){
			return checkDateType(str);
		}
		Integer xy = null;
		Date date=null;
		if(str.length()<10){
			try{
				xy = Integer.parseInt(str);
				if(xy!=null){
					//System.out.println(str+"是数字类型");
					return xy;
				}
			}catch(Exception e){System.out.println(e);}
		}
		if(str.length()>10){//"yyyy-MM-dd hh:mm:ss"
			try{
				date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str.replace("T", " "));
			}catch(Exception e){}
			if(date!=null){
				//System.out.println(str+"是日期类型");
				return date;
			}
		}else{
			try{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			}catch(Exception e){System.out.println(e.getMessage());}
			if(date!=null){
				return date;
			}
		}
			System.out.println("数据转换失败，不是有效的int或date类型:"+str);
		return null;
	}
	

	
	
	//private String equally(String)
	
}
