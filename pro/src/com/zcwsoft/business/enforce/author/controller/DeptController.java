package com.zcwsoft.business.enforce.author.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.author.service.DeptService;
import com.zcwsoft.common.newutils.basebean.ReceParam;

@Controller
@RequestMapping("deptController")
public class DeptController {
	@Autowired
	private DeptService ser;

	

	/**
	 * 加载列表
	 * @param rcep
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(ReceParam rcep){
		ModelAndView mv=new ModelAndView("/enforce/author/dept/deptList");
		try {
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
		ModelAndView mv = new ModelAndView("/enforce/author/dept/deptDetail");
		String id = rcep.getId();
		String dataShow=null;
		try{
			if(StringUtils.isNotEmpty(id)){
				dataShow = ser.showData(rcep);
				mv.addObject("dataShow",rcep.getURLEncoder(dataShow));
			}
			mv.addObject("pid",rcep.getPid());
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
