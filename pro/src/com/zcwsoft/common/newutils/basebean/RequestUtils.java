package com.zcwsoft.common.newutils.basebean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	HttpServletRequest request;

	public RequestUtils(HttpServletRequest req){
		this.request = req;
	}
	
	public HttpServletRequest getRequest(){
		return this.request;
	}
	
	/**
     * 
     * 把字符串转换成Encode编码（%E5%91%B5%E5%91%B5）
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String convertEncodeParam(String param){
        try{
            param = URLEncoder.encode(param, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }  
        return param;
    }
    /**
     * 
     * 把字符串转换成Decode编码（%E5%91%B5%E5%91%B5）
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String convertDecodeParam(String encodeParam){
        try{
            encodeParam = URLDecoder.decode(encodeParam, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }  
        return encodeParam;
    }
    /**
     * 获取request域中参数，并Decode解码
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String getRequestDecodeParam(String requestParamKey){
        HttpServletRequest request=getRequest();
        String parameter = null;
        try{
            parameter = request.getParameter(requestParamKey);
            parameter = URLDecoder.decode(parameter, "utf-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }  
        return parameter;
    }
    /**
     * 获取request域中参数，并转换UTF-8
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String getRequestUTF8Param(String requestParamKey){
        HttpServletRequest request=getRequest();
        String parameter = null;
        try{
            parameter = request.getParameter(requestParamKey);
            parameter = new String(parameter.getBytes("UTF-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }  
        return parameter;
    }
    /**
     * 获取request域中参数，并转换GBK
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String getRequestGBKParam(String requestParamKey){
        HttpServletRequest request=getRequest();
        String parameter = null;
        try{
            parameter = request.getParameter(requestParamKey);
            parameter = new String(parameter.getBytes("GBK"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }  
        return parameter;
    }
    /**
     * 获取request域中参数，不转码，
     * 〈功能详细描述〉
     * @param requestParamKey
     * @return
     */
    public String getRequestParam(String requestParamKey){
        HttpServletRequest request=getRequest();
        String parameter = request.getParameter(requestParamKey);
        return parameter;
    }

/*import org.springframework.web.context.support.WebApplicationContextUtils;  
ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContext sc)  
ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletContext sc)  
ac1.getBean("beanId");  
ac2.getBean("beanId"); 
https://www.cnblogs.com/keyi/p/6253235.html
http://blog.csdn.net/lipei1220/article/details/50977393
*/	
}
