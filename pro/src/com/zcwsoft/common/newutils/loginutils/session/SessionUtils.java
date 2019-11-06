package com.zcwsoft.common.newutils.loginutils.session;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtils extends HttpServlet{

	private HttpServletRequest	request = this.getRequest();
	private HttpServletResponse	resopnse = this.getResponse();
	private HttpSession	session = this.getSession();
	private  ServletContext	sContext = this.getServletContext();
	private  ServletConfig	sConfig = this.getServletConfig();
	
	
	
	
	/**
	 * 获取登录用户名
	 * @return
	 */
	public static String getUserName(){
		
		return "u_admin";
	}
	/**
	 * 获取登录用户id
	 * @return
	 */
	public static String getUserId(){
		return "u_admin";
	}
	/**
	 * 获取request
	 * @return
	 */
	public static HttpServletRequest getRequest() {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			return request;
	}
	/**
	 * 获取response
	 * @return
	 */
	public static HttpServletResponse getResponse() {
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
			return response;
	}
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession() {
	    HttpSession session=getRequest().getSession();
	    return session;
	}
}
