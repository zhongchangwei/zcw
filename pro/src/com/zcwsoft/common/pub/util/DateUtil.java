package com.zcwsoft.common.pub.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author BWeiMing
 *  
 */
public class DateUtil {
    public static final int DB_TYPE_SYBASE=1;
    public static final int DB_TYPE_ORACLE=2;    
    public static final int DB_TYPE_MSSQL=3;
    
    public static final String yyMMdd="yy-MM-dd";
    public static final String yyyyMMdd="yyyy-MM-dd";
    public static final String HHmmss="HH:mm:ss";
    public static final String yyyyMMddHHmmss="yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm="yyyy-MM-dd HH:mm";
    public static final String yyMMddHHmmss="yy-MM-dd HH:mm:ss";
    
    //转换orale时间是需要使用的时间格式
    public static final String Oracl_yyMMdd="yy-mm-dd";
    public static final String Oracl_yyyyMMdd="yyyy-mm-dd";
    public static final String Oracl_HHmmss="hh24:mi:ss";
    public static final String Oracl_yyyyMMddHHmmss="yyyy-mm-dd hh24:mi:ss";
    public static final String Oracl_yyyyMMddHHmm="yyyy-mm-dd hh24:mi";
    public static final String Oracl_yyMMddHHmmss="yy-mm-dd hh24:mi:ss";
    
    /**
     * 根据数据库不同，转换时间格式
     * 主要用于数据的时间字段的比较，通常在查询时使用
     * @param sDate
     * @param sFormat
     * @param iDBType
     * 注意：sDate和sFormat的时间格式要匹配
     *   如果sDate的格式是"2009-09-09" ,则sFormat使用"yyyy-mm-dd"
     * @return
     */
    public static String to_DBDate(String sDate, String sFormat,int iDBType) {
       String sValue="";
       switch(iDBType){
         case DB_TYPE_ORACLE:
        	 sValue ="to_date('"+sDate+"','"+sFormat+"')";
        	 break;
         case DB_TYPE_SYBASE:
        	 sValue ="convert(datetime,'"+sDate+"')";
        	 break;
         case DB_TYPE_MSSQL:
        	 sValue ="cast('"+sDate+"' as datetime) ";
        	 break;
         default:
            sValue=sDate;
           break;
       }
       return sValue;
    }

    public static Date parseToDate(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        if(s==null||s.length()<8)
            return null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseToString(String s, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        Date date = null;
        String str=null;
        if(s==null||s.length()<8)
            return null;
        try {
            date = simpleDateFormat.parse(s);
            str=simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    public static String parseToString(Date date, String style) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(style);
        String str=null;
        if(date==null)
            return null;
        str=simpleDateFormat.format(date);
        return str;
    }
    
    public static String getNowTime(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMddHHmmss);
	    String str = formatter.format(now.getTime());
		return str;
	}
    
    public static String getNowTime2(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMddHHmm);
	    String str = formatter.format(now.getTime());
		return str;
	}
    public static String getShortNowTime(){
		Date nowDate = new Date();
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
	    SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMdd);
	    String str = formatter.format(now.getTime());
		return str;
	}
      
    public static String getMonthFirstDay() { 
        Calendar calendar = Calendar.getInstance();    
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));    
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String str = formatter.format((calendar.getTime())); 
        return str;
    }   
    
    // 获得当前日期与本周日相差的天数    
    private static int getMondayPlus(int day) {    
        Calendar cd = Calendar.getInstance();    
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......    
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK)-day;         //因为所需要的是从上周星期三到本周星期二所为第一天所以这里减3,即星期三作为开始日期    
        if (dayOfWeek == 1) {    
            return 0;    
        } else {    
            return 1 - dayOfWeek;    
        }    
    }   
    // 获得本周星期日的日期      
    public static String getCurrentWeekday2(int week,int day) {    //第一个参数据为控制哪一周，第二个参数用来控制哪一天作为开始日期
        int weeks = week ;    
        int mondayPlus = getMondayPlus(day);    
        GregorianCalendar currentDate = new GregorianCalendar();    
        currentDate.add(GregorianCalendar.DATE, mondayPlus+ 7 * weeks);    
        Date monday = currentDate.getTime();    	            
        DateFormat df = DateFormat.getDateInstance();    
     //   String preMonday = df.format(monday) + " 00:00";	
        String preMonday = df.format(monday) + "";	
        return preMonday;    
    }     
    
    public static long getsubdates(String endtime,String starttime){			
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		long days=0;
    	try { 
    	    Date d1 = df.parse(endtime); 
    	    Date d2 = df.parse(starttime); 
    	    long diff = d1.getTime() - d2.getTime(); 
    	    days = diff / (1000 * 60 * 60 * 24); 
    	} catch (Exception e) { } 
    	return days;
	}
    
    public static String getNextMonthDay(String ts, int i){
		Calendar now = Calendar.getInstance();
		Timestamp t = Timestamp.valueOf(ts);
		now.setTime(t);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    now.add(Calendar.MONTH, i); 
		String dt = formatter.format(now.getTime());
		return dt;
	}
    
    /**
     * 两个时间相差时间
     * @param startTime
     * @param endTime
     * endTime>startTime
     * @return 返回XX天XX小时XX分
     */
    public static String getTwoTimeDayHourMin(String startTime, String endTime){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starDate=null; //2004-01-02 11:30:24
        Date endDate=null;//2004-03-26 13:31:40

		try {
			starDate = df.parse(startTime);
			endDate=df.parse(endTime);
		} catch (ParseException e) {			
			e.printStackTrace();
		}       
        long l=endDate.getTime()-starDate.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        //long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        String resultStr="";
        if(day>0){
        	resultStr=resultStr+day+"天";
        }
        if(hour>0){
        	resultStr=resultStr+hour+"小时";
        }
        if(min>0){
        	resultStr=resultStr+min+"分";
        }  
		return resultStr;
    }
    /**
     * 两个时间相差时间
     * @param startTime
     * @param endTime
     * endTime>startTime
     * @return 返回XX天
     */
    public static String getTwoTimeDay(String startTime, String endTime){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starDate=null; //2004-01-02 11:30:24
        Date endDate=null;//2004-03-26 13:31:40

		try {
			starDate = df.parse(startTime);
			endDate=df.parse(endTime);
		} catch (ParseException e) {			
			e.printStackTrace();
		}       
        long l=endDate.getTime()-starDate.getTime();
        long day=l/(24*60*60*1000);
        String resultStr="0";
        if(day>0){
        	resultStr=resultStr+day+"天";
        }
		return resultStr;
    }
    
    
    /**
	 * 获取系统时间，返回类型为java.util.Date,格式为yyyyy-MM-dd HH:mm:ss的时间
	 * author:lzp
	 */
	public static Date getNowDate(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate= dateFormat.format(now);
		Date nowDate = null;
		try {
			nowDate = dateFormat.parse(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nowDate;
	}
}