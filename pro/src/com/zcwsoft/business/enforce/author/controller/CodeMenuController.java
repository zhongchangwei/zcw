package com.zcwsoft.business.enforce.author.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.author.service.CodeMenuService;
import com.zcwsoft.common.newutils.BaseSysUtils;
import com.zcwsoft.common.newutils.basebean.ReceParam;

@Controller
@RequestMapping("codeMenuController")
public class CodeMenuController {
	@Autowired
	private CodeMenuService ser;
	
	
	@RequestMapping("list")
	public ModelAndView list(ReceParam rcep){
		ModelAndView mv=new ModelAndView("/enforce/author/codeMenu/codeMenuList");
		try {
			com.zcwsoft.common.newutils.loginutils.user.UserUtils.getStringValueBySql();
			String data = ser.list();
			mv.addObject("data", data);
			mv.addObject("pid",rcep.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 作者：zcw
	 * 功能：新增  修改（回显）弹出页面
	 * 时间：2017-11-24
	 * @return 
	 */
	@RequestMapping("openDetail.do")
	public ModelAndView userOpenDetail(ReceParam rcep){
		ModelAndView mv = new ModelAndView("/enforce/author/codeMenu/codeMenuDetail");
		String id = rcep.getId();
		String dataShow=null;
		try{
			if(BaseSysUtils.isNotBlank(id)){
				dataShow = ser.showData(rcep);
				mv.addObject("pid",rcep.getPid());
				mv.addObject("dataShow",rcep.getURLEncoder(dataShow));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 作者：zcw
	 * 功能：保存
	 * 时间：2017-11-24
	 * @return 
	 */
	@RequestMapping("commitSave.do")
	@ResponseBody
	public Object commitSave(ReceParam rcep){
		String data="a";
		try{
				data = ser.save(rcep);
				System.out.println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}

}
