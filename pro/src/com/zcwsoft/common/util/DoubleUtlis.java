package com.zcwsoft.common.util;

import java.math.BigDecimal;

public class DoubleUtlis {
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 两个Double数相加
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double add(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两个Double数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double sub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 两个Double数相乘
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double mul(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		b1.setScale(1,   BigDecimal.ROUND_HALF_UP);
		BigDecimal b2 = new BigDecimal(v2.toString());
		b2.setScale(1,   BigDecimal.ROUND_HALF_UP);
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 两个Double数相除
	 * 
	 * @param v1
	 * @param v2
	 * @return Double
	 */
	public static Double div(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个Double数相除，并保留scale位小数
	 * 
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return Double
	 */
	public static Double div(Double v1, Double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if((v1==null||v1==0)||(v2==null||v2==0))
			return 0.0;
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * 两个Integer数相除，并保留scale位小数
	 * @param v1除数
	 * @param v2被除数
	 * @param scale保留位数
	 * @return Double
	 */
	public static Double div(Integer v1, Integer v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if((v1==null||v1==0)||(v2==null||v2==0))
			return 0.0;
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个Integer数相除，并保留scale位小数
	 * @param v1被除数
	 * @param v2除数
	 * @param scale保留位数并四舍五入
	 * @return int
	 */
	public static Integer divInteger(Integer v1, Integer v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if((v1==null||v1==0)||(v2==null||v2==0))
		return 0;
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return (int)b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 两个Integer数相除，小数点后大于0的，往前进一位，如8.1,则返回9
	 * @param v1被除数
	 * @param v2除数
	 * @param scale保留位数并四舍五入
	 * @return int
	 */
	public static Integer divInteger(Integer v1, Integer v2) {
		if(v1==null||v2==null)return 0;
		if(v1==0||v2==0)return 0;
		Double d=div(v1.doubleValue(), v2.doubleValue());
		Integer i=0;
		if(d!=null) {
			i=d.intValue();
			if((d-i)>0) {
				i=i+1;
			}
		}
		return i;
	}
	
	public static void main(String[] args) {
		System.out.println(divInteger(10,2));
	}
	
	/**
	 * 两个Integer数相除，并保留scale位小数
	 * @param v1被除数
	 * @param v2除数
	 * @param scale保留位数并四舍五入
	 * @return int
	 */
	public static Double divLong(Long v1, Integer v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		if((v1==null||v1==0)||(v2==null||v2==0))
		return 0.0;
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static Double formatDouble(Double d,int scale,int roundingMode){
		BigDecimal bg = new BigDecimal(d);
        return bg.setScale(scale, roundingMode).doubleValue();
	}

}
