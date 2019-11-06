package com.zcwsoft.business.enforce.zcw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.zcw.service.ExcelImpService;
/**
 * 功能：导入，导出
 * @author zcw
 *
 */
@Controller
@RequestMapping("/bigDateController")
public class ExcelImpController {
	
	@Autowired
	private ExcelImpService excelImpService;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("enforce/zcw/excelImpro");
		return mv;
	}
	@RequestMapping("excelImpro")
	@ResponseBody
	public ModelAndView excelImpro(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView("enforce/zcw/excelImpro");
		try{
			excelImpService.ExcelImpro(request,response);
		}catch(Exception e){
		}
		return mv;
	}
}
