package com.zcwsoft.common.newutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zcwsoft.common.newutils.loginutils.session.SessionUtils;

/**
 * 功能：系统通用工具类
 * @author Administrator
 *
 */
public class BaseSysUtils {
	
	
	public static void main(String[] args) {
		try{
		String id = getID();
		System.out.println(id);
		}catch(Exception e){
			e.printStackTrace();}
	}
	/**
	 * 获取一个以时间直至毫秒的时间字符串作为主键id
	 * @return
	 */
	public static String getID(){
		Random rand = new Random();
		//生成100－1000之间的随机数，包括1000
		String randNum = rand.nextInt(10000) + 10000 + "";
		String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		System.out.println(id+"V"+randNum);
		return id+"V"+randNum;
	}

	/**
	 * 获取一个UUID作为主键id
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString(); 
        uuid = uuid.replace("-", "");               
        System.out.println(uuid); 
		return uuid;
	}
	/**
	 * 获取登录用户名
	 * @return
	 */
	public static String getUserName(){
		return SessionUtils.getUserName();
	}
	/**
	 * 获取登录用户id
	 * @return
	 */
	public static String getUserId(){
		return SessionUtils.getUserId();
	}
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest() {
			return SessionUtils.getRequest();
	}
	/**
	 * 获取response
	 * @return
	 */
	public static HttpServletResponse getResponse() {
			return SessionUtils.getResponse();
	}
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession() {
	   return SessionUtils.getSession();
	}
	/**
	 * 功能：获取容器中的对象，根据注入名称
	 * @param beanId 	@/Service(beanId)
	 * @return 	容器对象
	 */
	public static Object getIocBean(String beanId){
		ServletContext sc=getSession().getServletContext();
		WebApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		//WebApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
		//ac2.getBean("beanId"); 
		return ac1.getBean(beanId);  
	}

	public static boolean isBlank(String str){
		if(str == null || "".equals(str) || "null".equals(str))
		{
			return true;
		}
		return false;
	}
	public static boolean isNotBlank(String str){
		if(str == null || "".equals(str) || "null".equals(str))
		{
			return false;
		}
		return true;
	}
}
