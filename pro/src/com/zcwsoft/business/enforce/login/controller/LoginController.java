package com.zcwsoft.business.enforce.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.login.service.LoginService;


@Controller
@RequestMapping("/enforce/author/login")
public class LoginController {
	
	private Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService serv;
	
	
	@RequestMapping("/login.do")
	public ModelAndView login(HttpSession session){
		ModelAndView view =new ModelAndView("enforce/login/loginList");
		try{
			String loginTreeData = serv.loginTreeData(session);
			view.addObject("loginTreeData", loginTreeData);
			view.addObject("loginUserName", "Admin");
		}
		catch(Exception e){
			log.equals(e);
			e.printStackTrace();
		}
		return view;
	} 

}
