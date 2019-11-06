package com.zcwsoft.common.pub.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean checkStr(String str) {
		boolean bool = true;
		if(str == null || "".equals(str.trim()))
			bool = false;
		return bool;
	}
	
	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if(obj == null || "".equals(obj.toString().trim()))
			bool = false;
		return bool;
	}
	
	public static String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}
	
	public static int toInt(String str) {
		return "".equals(str) ? -1 : Integer.parseInt(str);
	}
	
	public static String getISOToGBK(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("ISO8859_1"),"GBK");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}
	
	public static String getISOToUTF8(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("ISO8859_1"),"UTF-8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}
	
	public static String getNowDate(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String str = formatter.format(now.getTime());
	    //str=getNextDate(str,-1);
		return str;		
	}
	
	
	public static String getNowTime(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String str = formatter.format(now.getTime());
		return str;
	}
	
	public static long getTimeInMillis(String sDate, String eDate){
		Timestamp sd = Timestamp.valueOf(sDate);
		Timestamp ed = Timestamp.valueOf(eDate);
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sd); 
		long timethis = calendar.getTimeInMillis();  
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(ed); 
		long timeend = calendar2.getTimeInMillis(); 
		long thedaymillis = timeend-timethis; 
		return thedaymillis;
	}
	
	public static String formatDateTime(String dTime) {
		String dateTime = "";
		if(dTime != null && !"".equals(dTime) && !dTime.startsWith("1900-01-01")) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}
	
	public static String formatTime(String dTime) {
		String dateTime = "";
		if(dTime != null && !"".equals(dTime)) {
			Timestamp t = Timestamp.valueOf(dTime);
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			dateTime = formatter.format(t);
		}
		return dateTime;
	}
	
	public   static   Date parses(String   strDate,   String   pattern)   throws   ParseException   {   
		  return    new   SimpleDateFormat(pattern).parse(strDate);   
	} 
	//当前日期是第几周
	public static String getWeekOfYear(){
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String week = calendar.get(Calendar.WEEK_OF_YEAR)+"";
		return week; 
	}
	//当前时间减去一年
	public static String getNowTimeLittle(){        
		Calendar   c   =   Calendar.getInstance();
		c.add(c.YEAR,+1); 
		String time = ""+c.get(c.YEAR)+"-"+(c.get(c.MONTH)+1)+"-"+c.get(c.DATE)+" "+c.get(c.HOUR_OF_DAY)+":"+c.get(c.MINUTE)+":"+c.get(c.SECOND); 
      String returnstr = "";
		try{
      	Date d = StringUtil.parses(time,"yyyy-MM-dd HH:mm:ss");
      	//System.out.println(SimpleDateFormat(d,"yyyy-MM-dd HH:mm:ss"));
      	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          
      	returnstr = formatter.format(d);
      }catch(Exception e){
      	
      }
		return returnstr;

	}
//	当前时间减去一天
	public static String getNowTimeLittleDate(){        
		Calendar   c   =   Calendar.getInstance();
		c.add(c.DATE,+1); 
		String time = ""+c.get(c.YEAR)+"-"+(c.get(c.MONTH)+1)+"-"+c.get(c.DATE)+" "+c.get(c.HOUR_OF_DAY)+":"+c.get(c.MINUTE)+":"+c.get(c.SECOND); 
      String returnstr = "";
		try{
      	Date d = StringUtil.parses(time,"yyyy-MM-dd HH:mm:ss");
      	//System.out.println(SimpleDateFormat(d,"yyyy-MM-dd HH:mm:ss"));
      	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
          
      	returnstr = formatter.format(d);
      }catch(Exception e){
      	
      }
		return returnstr;

	}
	//根据参数获取随机值的整位数
	public static String getRandom(int num) {
		return (Math.random()+"").substring(2, num + 2);
	}
	
	public static String getTimeInMillis(Date sDate, Date eDate){
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sDate); 
		long timethis = calendar.getTimeInMillis();  
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(eDate); 
		long timeend = calendar2.getTimeInMillis(); 
		long thedaymillis = timeend-timethis; 
		return thedaymillis < 1000 ? thedaymillis + "毫秒!" : (thedaymillis/1000) + "秒钟!";
	}
	
	public static String showTrace() {
    	StackTraceElement[] ste = new Throwable().getStackTrace();
    	StringBuffer CallStack = new StringBuffer();
    	
    	for (int i = 1; i < ste.length; i++) {
    		CallStack.append(ste[i].toString()+ "\n");
    		if (i > 4 ) break;
		}
    	return CallStack.toString();        
    }
	
	public static String checkTableDefKey(String[] key, String[] value, String name) {
		String str = "";
		for(int i=0; i<key.length; i++) {
			if(name.equals(key[i])) {
				str = value[i];
				break;
			}
		}
		return str;
	}
	
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static String getStrToGbk(String str){
		String strName = "";
		try{
			if(str!=null){
				strName = new String(str.getBytes("UTF-8"),"GBK");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strName;
	}

	public static String getNextDate(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts + " 00:00:00.000");
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    now.add(Calendar.DAY_OF_MONTH, +(i)); 
		String dt = formatter.format(now.getTime());
		return dt;
	}
	
	public static String getNextTime(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    now.add(Calendar.MINUTE, +(i)); 
		String dt = formatter.format(now.getTime());
		return dt;
	}
	
	//取Unix时间戳
	public static long getUnixTime(String dateTime) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		    date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 08:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long l = (date1.getTime() - date2.getTime())/1000;
        return l;
	}
	
	public static String toFirstUpperCase(String str) {
		if(str == null || "".equals(str.trim()))
			return "";
		String firstChar = str.substring(0, 1).toUpperCase();
		String lastStr = str.substring(1);
		return firstChar + lastStr;
	}
	
	//1ADSS、2OPGW、3管道、4架空、5直埋、6未知
	public static String getEnumName(String enumIds) {
		String[] ids = enumIds.split(",");
		String names = "";
		if(ids != null && ids.length > 0) {
			for(int i=0; i<ids.length; i++) {
				String id = ids[i];
				if(id == null || "".equals(id)) continue;
				switch(Integer.parseInt(id)) {
					case 1:
						names += "ADSS,";
						break;
					case 2:
						names += "OPGW,";
						break;
					case 3:
						names += "管道,";
						break;
					case 4:
						names += "架空,";
						break;
					case 5:
						names += "直埋,";
						break;
					case 6:
						names += "未知,";
						break;
				}
			}
		}
		return names.length() > 0?names.substring(0, names.length()-1):"";
	}

	//判断一个字符串是不是数字
	public static boolean isNum(String str){
		boolean flg;
		try{Double.parseDouble(str);
			flg=true;
		}catch(Exception ex){
			flg=false;
		}
		return flg;
	}
	
	//去除字符串数组中的重复值
	//add by tanzhouwen
	public static String [] filterRepeat(String[]  stringArray) 
	{ 
		ArrayList   arrayList   =   new   ArrayList(); 
		for (String str : stringArray) {
			if(!arrayList.contains(str)) 
			{ 
				arrayList.add(str);
			}
		} 
		return  (String[]) arrayList.toArray(new String[]{});
	} 
	

	
	/**
	 * 得到ID的in字句
	 * 如e.id in (1,3,4)
	 * @param ids 如"1,2,3,"等
	 * @param alias 如"e.id"等
	 * @return
	 */
	public static  String getIn300Ids(String ids,String alias) {
	    String tempS[] = ids.split(",");
	    int len = tempS.length;
	    int which = 0;
	    boolean isAnd = alias.indexOf("not")>0;
	    StringBuffer idsStr = new StringBuffer();
        idsStr.append("(");
	    if (len>300) {
	        if (len % 300 > 0) {
	            which = len / 300 + 1;
	        } else {
	            which = len / 300;
	        }
	        for (int i = 0; i < which; i++) {
	            idsStr.append(alias + " in (");
                for (int j = 300*i; j < 300*i+300; j++) {
                    if (j<len) {
                        idsStr.append(tempS[j]+",");
                    } else {
                        break;
                    }
                }
	            idsStr = idsStr.replace(idsStr.lastIndexOf(","),idsStr.length(),"");
	            if (i<which-1) {
	            	if(isAnd){
	            		idsStr.append(") and ");
	            	}else{
	            		idsStr.append(") or ");
	            	}
	            } else {
		            idsStr.append(")");
	            }
            }
	    } else {
	        idsStr.append(alias + " in (");
	        if (ids.lastIndexOf(",")==ids.length()-1) {
	            idsStr.append(ids.substring(0,ids.length()-1));
	        } else {
	            idsStr.append(ids);
	        }
	        idsStr.append(" )");
	    }
        idsStr.append(")");
	    return idsStr.toString();
	}
	
	
	/**
	 * 格式字符串
	 * 格式：a,b,v====>'a','b','v'
	 * @param str
	 * @return
	 */
	public static String getFormatString(String str){
		String strArr[]=str.split(",");
		String retStr="";
		for (int i = 0; i < strArr.length; i++) {
			if(i>0){
				retStr=retStr+",";
			}
			retStr=retStr+"'"+strArr[i]+"'";
		}
		return retStr;
	}
	
	public static String strRound(double value, int decimalPlaces) {
		// 声明一个返
		String rval;

		// 如果小数位是0
		if (decimalPlaces == 0) {
			rval = String.valueOf(Math.round(value));
			return rval;
		}

		// 先将参数值转为String型,并找到小数点所在位置
		DecimalFormat dformat = new DecimalFormat("0.0000000"); 
		String str=dformat.format(value);
		int point = str.indexOf(".");
		// 分别得到小数点之前的字符与小数点之后的字符
		String beforePoint = str.substring(0, point);
		String afterPoint = str.substring(point + 1);

		// 如果小数位正好是要求的小数位数
		if (afterPoint.length() == decimalPlaces) {
			rval = String.valueOf(value);
		} else if (afterPoint.length() < decimalPlaces) {
			// 如果小数位数少于要求的小数位数,则在后面补零
			StringBuffer sb = new StringBuffer(afterPoint);
			for (int i = 0; i < decimalPlaces - afterPoint.length(); i++) {
				sb.append("0");
			}
			// 连接
			sb.insert(0, ".").insert(0, beforePoint);
			rval = sb.toString();
		} else {
			// 如果小数位数多于要求的小数位数,则要四舍五入

			// 不管怎样,先舍
			StringBuffer sb = null;
			sb = new StringBuffer(beforePoint);
			sb.append(".").append(afterPoint.substring(0, decimalPlaces));
			String val = sb.toString();
			// 得到要舍掉的那位数
			int temp = Integer.valueOf(afterPoint.charAt(decimalPlaces) + "");
			// 如果要舍掉位置的数<4
			if (temp < 4) {
				rval = val;
			} else {
				// 如果要舍掉的位置>4

				// 构造出要加的o.XX1,如期而至2.588,保留2位小数,就要在2.5的基础上加0.01
				sb = new StringBuffer("0.1");
				for (int i = 1; i < decimalPlaces; i++) {
					sb.insert(2, "0");
				}

				// 在已经舍掉的情况下加上该补足的0.XX1
				double dbl = Double.valueOf(val)
						+ Double.valueOf(sb.toString());
				val = String.valueOf(dbl);
				// 此时加完后可能变成1,如0.99,保留1位小数,四舍五入就变成1了,所以要再判断是否小数位够位数
				// 如果没有小数位,则补足小数位
				if (val.indexOf(".") == -1) {
					val += ".";
					for (int i = 0; i < decimalPlaces; i++) {
						val += "0";
					}

					rval = val;
				} else {
					// 如果有小数位,不管怎样都补足小数位
					val = val.substring(val.indexOf(".") + 1);
					sb = new StringBuffer(dbl + "");
					for (int i = 0; i < decimalPlaces - val.length(); i++) {
						sb.append("0");
					}
					rval = sb.toString();
				}
			}
		}
		
		//因为double型不精确,所以最后再截一次
		point = rval.indexOf(".");
		return rval.substring(0, point + decimalPlaces + 1);

	}

	
	public static void main(String[] args) {
		
		System.out.println(strRound(0.0001,2));
	}
}
