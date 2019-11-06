package com.zcwsoft.common.pub.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 计算两个日期之间相差的月数和天数
 * @author iAction
 *
 */
public class DateUtils {
	
	static public DateFormat data_format_YMD = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 根据“作业实际完成时间”-“作业要求完成时间”，求出偏移时长
	 * @param s
	 * @param e
	 * @return
	 */
	public static long getDayByMinus(Date dt, Date de) {
		Calendar calendar = Calendar.getInstance();
		
		// 作业实际完成时间
		calendar.setTime(dt);
		long dtLong = calendar.getTimeInMillis();
		
		// 作业要求完成时间
		calendar.setTime(de);
		long deLong = calendar.getTimeInMillis();
		
		// 偏移时长
		long minus = (dtLong - deLong) * 1L / (1000L * 60 * 60 * 24);
		
		return minus;
	}
	
	/**
	 * 根据选择的时间段来计算相差的月数
	 * @param s
	 * @param e
	 * @return
	 */
	public int getMonth(Date s, Date e) {
		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		temp.add(Calendar.DATE, 1);

		int y = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
		int m = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

		if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
			return y * 12 + m + 1;
		} else if ((start.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
			return y * 12 + m;
		} else if ((start.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
			return y * 12 + m;
		} else {// 前破月后破月
			return (y * 12 + m - 1) < 0 ? 0 : (y * 12 + m - 1);
		}
	}

	public int getDay(Date s, Date e) {
		if (s.after(e)) {
			Date t = s;
			s = e;
			e = t;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(s);
		Calendar end = Calendar.getInstance();
		end.setTime(e);
		Calendar temp = Calendar.getInstance();
		temp.setTime(e);
		temp.add(Calendar.DATE, 1);

		if ((start.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {// 前后都不破月
			return 0;
		} else if ((start.get(Calendar.DATE) != 1)
				&& (temp.get(Calendar.DATE) == 1)) {// 前破月后不破月
			return getDayP(start);
		} else if ((start.get(Calendar.DATE) == 1)
				&& (temp.get(Calendar.DATE) != 1)) {// 前不破月后破月
			return end.get(Calendar.DATE);
		} else {// 前破月后破月
			if (start.get(Calendar.MONTH) == end.get(Calendar.MONTH)
					&& start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
				return end.get(Calendar.DATE) - start.get(Calendar.DATE) + 1;
			} else {
				return getDayP(start) + end.get(Calendar.DATE);
			}
		}
	}

	public int getDayP(Calendar s) {
		int d;
		if (s.get(Calendar.MONTH) == 1 && s.get(Calendar.YEAR) % 4 == 0
				&& s.get(Calendar.YEAR) % 100 != 0) {// 闰年2月
			d = 29;
		} else {
			Map<Integer, Integer> m = new HashMap<Integer, Integer>();
			m.clear();
			m.put(1, 31);
			m.put(3, 31);
			m.put(5, 31);
			m.put(7, 31);
			m.put(8, 31);
			m.put(10, 31);
			m.put(12, 31);
			m.put(4, 30);
			m.put(6, 30);
			m.put(9, 30);
			m.put(11, 30);
			m.put(2, 28);
			d = m.get(s.get(Calendar.MONTH) + 1);
		}
		return d - s.get(Calendar.DATE) + 1;
	}
}