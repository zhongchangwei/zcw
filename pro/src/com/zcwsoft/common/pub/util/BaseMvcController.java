/**   
*    
* 项目名称：gjj   
* 模块：公用
* 类名称：BaseController   
* 类描述：   
* @version 1.0   
*    
*/
package com.zcwsoft.common.pub.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class BaseMvcController {

	   public HttpServletRequest  getRequest() throws Exception{
		   HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		   return request;
	   }
	   
	   
	   public HttpServletResponse  getResponse() throws Exception{
		   HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
		   return response;
	   }
	   
	   public HttpSession  getSession() throws Exception{
		   HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		   HttpSession session=request.getSession();
		   return session;
	   } 
	   

}
