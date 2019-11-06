package com.zcwsoft.common.util;

/**

 * @(#)DateUtil.java

 *

 *

 * @author kidd

 * @version 1.00 2007/8/8

 */
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/** 时间范围：年*/
	public static final int YEAR = 1;
	/** 时间范围：季度*/
	public static final int QUARTER = 2;
	/** 时间范围：月*/
	public static final int MONTH = 3;
	/**时间范围：旬*/
	public static final int TENDAYS = 4;
	/** 时间范围：周*/
	public static final int WEEK = 5;
	/**时间范围：日*/
	public static final int DAY = 6;
	public static final SimpleDateFormat SF=new SimpleDateFormat("yyyy-MM-dd");
	/* 基准时间 */
	private Date fiducialDate = null;
	private Calendar cal = null;
	public DateUtils(Date fiducialDate) {
		if (fiducialDate != null) {
			this.fiducialDate = fiducialDate;
		}
		else {
			this.fiducialDate = new Date(System.currentTimeMillis());
		}
		this.cal = Calendar.getInstance();
		this.cal.setTime(this.fiducialDate);
		this.cal.set(Calendar.HOUR_OF_DAY, 0);
		this.cal.set(Calendar.MINUTE, 0);
		this.cal.set(Calendar.SECOND, 0);
		this.cal.set(Calendar.MILLISECOND, 0);
		this.fiducialDate = this.cal.getTime();
	}
	
	/**如果希望保留小时数，分钟数等，请使用该构造函数
	 * @param fiducialDate
	 * @param value
	 */
	public DateUtils(Date fiducialDate, Object value) {
		if (fiducialDate != null) {
			this.fiducialDate = fiducialDate;
		}
		else {
			this.fiducialDate = new Date(System.currentTimeMillis());
		}
		this.cal = Calendar.getInstance();
		this.cal.setTime(this.fiducialDate);
	}
	/**
	 * 
	 * 获取DateHelper实例
	 * @param fiducialDate
	 *            基准时间
	 * @return Date
	 */
	public static DateUtils getInstance(Date fiducialDate) {
		return new DateUtils(fiducialDate);
	}
	/**
	 * 获取DateHelper实例, 使用当前时间作为基准时间
	 * @return Date
	 */
	public static DateUtils getInstance() {
		return new DateUtils(null);
	}
	/**
	 * 获取年的第一天
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getFirstDayOfYear(int offset) {
		cal.setTime(this.fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取年的最后一天
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getLastDayOfYear(int offset) {
		cal.setTime(this.fiducialDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + offset);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	/**
	 * 获取季度的第一天
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getFirstDayOfQuarter(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);
		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.APRIL);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.JULY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		return cal.getTime();
	}

	public Date getYesterday() {
		long time = this.fiducialDate.getTime() - 60 * 60 * 24 * 1000;
		return new Date(time);
	}

	public Date getTomorrow() {
		long time = this.fiducialDate.getTime() + 60 * 60 * 24 * 1000;
		return new Date(time);
	}

	/**
	 * 获取季度的最后一天
	 * @param offset
	 *            偏移量
	 * @return Date
	 */
	public Date getLastDayOfQuarter(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset * 3);
		int mon = cal.get(Calendar.MONTH);
		if (mon >= Calendar.JANUARY && mon <= Calendar.MARCH) {
			cal.set(Calendar.MONTH, Calendar.MARCH);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		if (mon >= Calendar.APRIL && mon <= Calendar.JUNE) {
			cal.set(Calendar.MONTH, Calendar.JUNE);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}
		if (mon >= Calendar.JULY && mon <= Calendar.SEPTEMBER) {
			cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 30);
		}

		if (mon >= Calendar.OCTOBER && mon <= Calendar.DECEMBER) {
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 31);
		}
		return cal.getTime();
	}

	/**
	 * 获取月的第一天 xxxx-xx-1 00:00:00
	 * @param offset 偏移量 
	 * @return Date
	 */
	public Date getFirstDayOfMonth(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}

	/**
	 * 获取月的最后一天 到xxxx-xx-31 23:59:59
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getLastDayOfMonth(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.MONTH, offset + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	/**
	 * 获取旬的第一天
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getFirstDayOfTendays(int offset) {
		cal.setTime(this.fiducialDate);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day >= 21) {
			day = 21;
		}
		else if (day >= 11) {
			day = 11;
		}
		else {
			day = 1;
		}
		if (offset > 0) {
			day = day + 10 * offset;
			int monOffset = day / 30;
			day = day % 30;
			cal.add(Calendar.MONTH, monOffset);
			cal.set(Calendar.DAY_OF_MONTH, day);
		}
		else {
			int monOffset = 10 * offset / 30;
			int dayOffset = 10 * offset % 30;
			if ((day + dayOffset) > 0) {
				day = day + dayOffset;
			}
			else {
				monOffset = monOffset - 1;
				day = day - dayOffset - 10;
			}
			cal.add(Calendar.MONTH, monOffset);
			cal.set(Calendar.DAY_OF_MONTH, day);
		}
		return cal.getTime();
	}

	/**
	 * 获取旬的最后一天
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getLastDayOfTendays(int offset) {
		Date date = getFirstDayOfTendays(offset + 1);
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取周的第一天(MONDAY)
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getFirstDayOfWeek(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 获取周的最后一天(SUNDAY)
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date getLastDayOfWeek(int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(Calendar.DAY_OF_MONTH, offset * 7);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DAY_OF_MONTH, 6);
		return cal.getTime();
	}

	/**
	 * 获取指定时间范围的第一天
	 * @param dateRangeType  时间范围类型
	 * @param offset 偏移量
	 * @return Date
	 */

	public Date getFirstDate(int dateRangeType, int offset) {
		return null;
	}

	/**
	 * 获取指定时间范围的最后一天
	 * @param dateRangeType 时间范围类型
	 * @param offset 偏移量
	 * @return Date
	 */

	public Date getLastDate(int dateRangeType, int offset) {
		return null;
	}

	/**
	 * 根据日历的规则，为基准时间添加指定日历字段的时间量
	 * @param field  日历字段, 使用Calendar类定义的日历字段常量
	 * @param offset 偏移量
	 * @return Date
	 */
	public Date add(int field, int offset) {
		cal.setTime(this.fiducialDate);
		cal.add(field, offset);
		return cal.getTime();
	}

	/**
	 *  
	 * 根据日历的规则，为基准时间添加指定日历字段的单个时间单元
	 * @param field 日历字段, 使用Calendar类定义的日历字段常量
	 * @param up  指定日历字段的值的滚动方向。true:向上滚动 / false:向下滚动
	 * @return Date
	 */

	public Date roll(int field, boolean up) {
		cal.setTime(this.fiducialDate);
		cal.roll(field, up);
		return cal.getTime();
	}

	/**
	 * 把字符串转换为日期
	 * @param dateStr 日期字符串
	 * @param format 日期格式
	 * @return Date
	 */
	public static Date strToDate(String dateStr, String format) {
		Date date = null;
		if (dateStr != null && (!dateStr.equals(""))) {
			DateFormat df = new SimpleDateFormat(format);
			try {
				date = df.parse(dateStr);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	/**
	 * 把字符串转换为日期，日期的格式为yyyy-MM-dd HH:ss
	 * @param dateStr  日期字符串
	 * @return Date
	 */

	public static Date strToDate(String dateStr) {
		Date date = null;
		if (dateStr != null && (!dateStr.equals(""))) {
			if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				dateStr = dateStr + " 00:00";
			}
			else if (dateStr.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}")) {
				dateStr = dateStr + ":00";
			}
			else {
				System.out.println(dateStr + " 格式不正确");
				return null;
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss");
			try {
				date = df.parse(dateStr);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	public static Date dateTimeStrToDate(String dateStr) {
		Date date = null;
		if (dateStr != null) {			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date = df.parse(dateStr);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	/***
	 * 指定格式字符串yyyy-MM-dd HH:mm转时间Date
	 * @param dateStr
	 * @return
	 */
	public static Date stringToDate(String dateStr) {
		Date date = null;
		if (dateStr != null) {			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				date = df.parse(dateStr);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 把日期转换为字符串
	 * @param date 日期实例
	 * @param format  日期格式
	 * @return Date
	 */

	public static String dateToStr(Date date, String format) {
		return (date == null) ? "" : new SimpleDateFormat(format).format(date);
	}

	public static String dateToStr(Date date) {
		return (date == null) ? "": new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static String dateToSampleStr(Date date) {
		return (date == null) ? "": new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 取得当前日期 年-月-日
	 * @return Date
	 */
	public static String getCurrentDate() {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 取得当前日期 年-月
	 * @return Date
	 */
	public static String getCurrentDateYM() {
		DateFormat f = new SimpleDateFormat("yyyyMM");
		return f.format(Calendar.getInstance().getTime());
	}
	
	public static void main1(String[] args) {
		DateUtils dateHelper = DateUtils.getInstance();
		/* Year */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfYear(" + i + ") = "+ dateHelper.getFirstDayOfYear(i));
			System.out.println("LastDayOfYear(" + i + ") = "+ dateHelper.getLastDayOfYear(i));
		}
		/* Quarter */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfQuarter(" + i + ") = "+ dateHelper.getFirstDayOfQuarter(i));
			System.out.println("LastDayOfQuarter(" + i + ") = "+ dateHelper.getLastDayOfQuarter(i));
		}

		/* Month */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfMonth(" + i + ") = "+ dateHelper.getFirstDayOfMonth(i));
			System.out.println("LastDayOfMonth(" + i + ") = "+ dateHelper.getLastDayOfMonth(i));
		}
		/* Week */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfWeek(" + i + ") = "+ dateHelper.getFirstDayOfWeek(i));
			System.out.println("LastDayOfWeek(" + i + ") = "+ dateHelper.getLastDayOfWeek(i));
		}
		/* Tendays */
		for (int i = -5; i <= 5; i++) {
			System.out.println("FirstDayOfTendays(" + i + ") = "+ dateHelper.getFirstDayOfTendays(i));
			System.out.println("LastDayOfTendays(" + i + ") = "+ dateHelper.getLastDayOfTendays(i));
		}
	}

	/**
	 * 取当前日期的字符串形式,"XXXX年XX月XX日"
	 * @return java.lang.String
	 */
	public static String getPrintDate() {
		String printDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		printDate += calendar.get(Calendar.YEAR) + "年";
		printDate += (calendar.get(Calendar.MONTH) + 1) + "月";
		printDate += calendar.get(Calendar.DATE) + "日";
		return printDate;
	}
	/**
	 * 将指定的日期字符串转化为日期对象
	 * @param dateStr 日期字符串
	 * @return java.util.Date
	 */
	public static Date getDate(String dateStr, String format) {
		if (dateStr == null) {
			return new Date();
		}
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(dateStr);
			return date;
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 从指定Timestamp中得到相应的日期的字符串形式 日期"XXXX-XX-XX"
	 * @param dateTime
	 * @return 、String
	 */
	public static String getDateFromDateTime(Timestamp dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dateTime).toString();
	}

	/**
	 * 得到当前时间 return java.sql.Timestamp
	 * @return Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		long curTime = System.currentTimeMillis();
		return new Timestamp(curTime);
	}
	
	/**获取两个时间的差值，返回小时*/
	public static double getDateOfHours(Date start,Date end) {
		return DoubleUtlis.div(Double.valueOf((end.getTime()-start.getTime())), Double.valueOf(1000*60*60),1);
	}
	/**获取两个时间的差值，返回小时*/
	public static int getDateOfIntHours(Date start,Date end) {
		return (int)(double)DoubleUtlis.div(Double.valueOf((end.getTime()-start.getTime())), Double.valueOf(1000*60*60),1);
	}
	
	/**获取两个时间的差值，返回天*/
	public static double getDateOfDay(Date start,Date end) {
		return DoubleUtlis.div(Double.valueOf((end.getTime()-start.getTime())), Double.valueOf(1000*60*60*24),1);
	}
	
	/**获取两个时间的差值，返回天*/
	public static int getDateOfIntDay(Date start,Date end) {
		return (int)(double)DoubleUtlis.div(Double.valueOf((end.getTime()-start.getTime())), Double.valueOf(1000*60*60*24),1);
	}
	
	/**获取两个时间的差值，返回天*/
	public static int getDateOfIntMinute(Date start,Date end) {
		return (int)(double)DoubleUtlis.div(Double.valueOf((end.getTime()-start.getTime())), Double.valueOf(1000*60),1);
	}

	
	/**
	 * 将结束日期的年月日、开始时间的时分 赋值 与 返回 的时间
	 * 如：开始时间为2013-12-31 08:00
	 *       结束时间为2014-01-02 17:30
	 *       返回值为    2014-01-02 08:00
	 * @param start
	 * @param end
	 * @return
	 * @author 葛传艺
	 * @version v1.0 2013年12月14日10:18:52
	 */
	public static Date AssignYMDHm(Date start,Date end) {
		Calendar startCal=Calendar.getInstance();
		startCal.setTimeInMillis(start.getTime());
		Calendar endCal=Calendar.getInstance();
		endCal.setTimeInMillis(end.getTime());
		Calendar c=Calendar.getInstance();
		c.set(YEAR, endCal.get(YEAR));
		c.set(MONTH, endCal.get(MONTH));
		c.set(DAY, endCal.get(DAY));
		c.set(Calendar.HOUR_OF_DAY, startCal.get(Calendar.HOUR_OF_DAY));
		c.set(Calendar.MINUTE, startCal.get(Calendar.MINUTE));
		return c.getTime();
	}
	
	/***
	 * 判断两个时间是否在一个时间段内
	 * @param startScope 开始时间段
	 * @param endScope  结束时间段
	 * @param start  开始时间
	 * @param end   结束时间
	 * @return 
	 * @author 葛传艺
	 * @version v1.0 2013年12月14日10:18:52
	 */
	public static boolean isTimePeriod(Date startScope,Date endScope,Date start,Date end) {
		return start.getTime()<=endScope.getTime()&&start.getTime()>=startScope.getTime()&&
					end.getTime()<=endScope.getTime()&&end.getTime()>=startScope.getTime();
	}
	
	/**
	 * 将时间的时分秒格式化为0
	 * @param d
	 * @return
	 *  @author 葛传艺
	 * @version v1.0 2013年12月14日19:24:57
	 */
	public static Date getFormatYMD(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 将时间的秒、毫秒格式化为0
	 * @param d
	 * @return
	 *  @author 葛传艺
	 * @version v1.0 2013年12月14日19:24:57
	 */
	public static Date getFormatYMDHm(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/***
	 * 根据年月日获取时间类型
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month - 1, day);
		Date dt = c.getTime();
		return dt;
	}	

	public static Date getOnlyDate(Date dt) {			
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(getYear(dt),getMonth(dt)-1,getDay(dt));		
		return c.getTime();
	}
	

	public static int getYear(Date dt) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(dt.getTime());	
		return c.get(Calendar.YEAR);
	}
	public static int getMonth(Date dt) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(dt.getTime());	
		return c.get(Calendar.MONTH) + 1;
	}
	

	public static int getDay(Date dt) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(dt.getTime());	
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(Date dt){
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(dt.getTime());	
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getMinute(Date dt){
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTimeInMillis(dt.getTime());	
		return c.get(Calendar.MINUTE);
	}
	
	/***
	 * 拼装完整时间
	 * 取date的年月日，取time的时分秒
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getCompleteDate(Date date, Date time){
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(getYear(date),getMonth(date)-1,getDay(date),getHour(time),getMinute(time));	
		return c.getTime();
	}
	/***
	 * 拼装完整时间
	 * 取year的年，取Date的月日时分
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getCompleteDateExtend(Date year, Date date){
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(getYear(year),getMonth(date)-1,getDay(date),getHour(date),getMinute(date));	
		return c.getTime();
	}
	/***
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(getDate(year, month, 1));
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR,23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Date addTime(Date date,int field,int offset){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, offset);
		return c.getTime();
		
	}
	
	
	/***
	 * 该方法用于计算当天的正常上班时间段baseStartDate~baseEndDate于其他时间段(1,请假;2,加班;3,出差...)的交集时间数
	 * @param baseStartDate 上班时间
	 * @param baseEndDate 下班时间
	 * @param startDate 待核算开始时间
	 * @param endDate 待核算结束时
	 * @return
	 */
	public static double getIntersectionHoursOfDay(Date baseStartDate,Date baseEndDate,Date startDate,Date endDate){
		double hours = 0;
		if(baseStartDate.before(endDate) && baseEndDate.after(startDate)){
			if(baseStartDate.before(startDate)){
				//|__________|
				//    |________|
				if(baseEndDate.before(endDate)){
					hours += DateUtils.getDateOfHours(startDate, baseEndDate);
				}
				//|_______________|
				//    |________|
				else{
					hours += DateUtils.getDateOfHours(startDate, endDate);
				}								
			}
			else{
				//   |__________|
				//|________|
				if(baseEndDate.before(endDate)){
					hours += DateUtils.getDateOfHours(baseStartDate, baseEndDate);
				}
				//   |__________|
				//|_________________|
				else{
					hours += DateUtils.getDateOfHours(baseStartDate, endDate);
				}								
			}
		}
		return hours;
	}
	
	/**
	 * 说明：分钟转小时
	 * 余数标准：x>30,0.5小时；x<=30,0小时；
	 * @param minute
	 * @return
	 * @author 冯伟湛
	 * @version 0.1 2014-3-27 下午07:02:51
	 */
	public static double minuteToHour(int minute){
		double hour = minute/60;
		int remainder = minute%60;
		hour+=remainder>=30?0.5:0;
		return hour;
	}
	
	/**
	 * 说明：分钟转小时
	 * 余数标准：x>30,1小时；x<=30,0小时；
	 * @param minute
	 * @return
	 * @author 冯伟湛
	 * @version 0.1 2014-3-27 下午07:02:51
	 */
	public static double minuteToHour2(int minute){
		double hour = minute/60;
		int remainder = minute%60;
		hour+=remainder>30?1:0;
		return hour;
	}
	
	public static String minuteToHourToString(int hours){
		return String.valueOf(hours);
	}
	
	/***
	 * 该方法用于计算当天的正常上班时间段baseStartDate~baseEndDate于其他时间段(1,请假;2,加班;3,出差...)的交集时间数
	 * @param baseStartDate 上班时间
	 * @param baseEndDate 下班时间
	 * @param startDate 待核算开始时间
	 * @param endDate 待核算结束时
	 * @return
	 */
	public static int getIntersectionMinuteOfDay(Date baseStartDate,Date baseEndDate,Date startDate,Date endDate){
		int minute = 0;
		if(baseStartDate.before(endDate) && baseEndDate.after(startDate)){
			if(baseStartDate.before(startDate)){
				//|__________|
				//    |________|
				if(baseEndDate.before(endDate)){
					minute += DateUtils.getDateOfIntMinute(startDate, baseEndDate);
				}
				//|_______________|
				//    |________|
				else{
					minute += DateUtils.getDateOfIntMinute(startDate, endDate);
				}								
			}
			else{
				//   |__________|
				//|________|
				if(baseEndDate.before(endDate)){
					minute += DateUtils.getDateOfIntMinute(baseStartDate, baseEndDate);
				}
				//   |__________|
				//|_________________|
				else{
					minute += DateUtils.getDateOfIntMinute(baseStartDate, endDate);
				}								
			}
		}
		return minute;
	}
	
	public Date toDate(){
		return this.cal.getTime();
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		
		Date d1=new Date();
		
		Date d=DateUtils.getFormatYMDHm(new Date());
		System.out.println(sf.format(d));
		System.out.println(sf.format(d1));
		
		System.out.println("aaaaaaa:"+getDate(2014, 0, 1));
		
	}

}
