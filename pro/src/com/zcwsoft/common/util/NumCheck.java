package com.zcwsoft.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumCheck {
	/** 
	     * 判断字符串是否数值  
	     * @param str 
	     * @return true:是数值 ；false：不是数值  
	     * @author:WD_SUHUAFU 
	     */  
	    public static boolean isNumber(String str)  
	    {  
	        Pattern pattern = Pattern.compile("(^([+-]|[1-9]){1}[0-9]+(\\.[0-9]*)?$)|(^[0]{1}+(\\.[0-9]*)?$)"); 
	    	//^[-+]?([0]{1}(\\.[0-9]+)?|[1-9]{1}\\d*(\\.[0-9]+)?)
	        //Pattern pattern = Pattern.compile("^[-+]?([1-9]{1}[0-9]+|[0]?)(\\.[0-9]+)?$");
	        Matcher match=pattern.matcher(str);  
	        return match.matches();  
	   }  
	    public static void main(String[] args)        
    	{  
	    	System.out.println(isNumber("99."));
    	}

}
