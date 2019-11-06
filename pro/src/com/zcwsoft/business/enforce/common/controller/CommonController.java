package com.zcwsoft.business.enforce.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.common.service.CommonService;
import com.zcwsoft.common.newutils.basebean.ReceParam;


@Controller
@RequestMapping("common")
public class CommonController {
	
	private Logger log = Logger.getLogger(CommonController.class);
	
	@Autowired
	private CommonService ser;

	
	/**
	 * 加载树
	 * @param rcep
	 * @return
	 */
	@RequestMapping("treeList")
	public ModelAndView treeList(ReceParam rcep){
		ModelAndView mv=new ModelAndView("/enforce/common/commonTree");
		try {
			mv.addObject("type", rcep.getType());
			if(rcep.equals(rcep.getType(), "codeMenu")){
				String treeListToCodeMenu = ser.treeListToCodeMenu();
				mv.addObject("treeData", treeListToCodeMenu);
				mv.addObject("title", "菜单管理");
			}else{
				String deptTreeData = ser.treeList();
				mv.addObject("treeData", deptTreeData);
				mv.addObject("title", "部门管理");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 作者：zcw
	 * 功能：获取部门id 和 name
	 * 时间：2017-11-24 21:10:27
	 * @return [{id:?,name:?}]
	 */
	@RequestMapping("getCodeMenu.do")
	@ResponseBody
	public String getCodeMenu(){
		String data=null;
		try {
			data = ser.getCodeMenu();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 作者：zcw
	 * 功能：获取部门id 和 name
	 * 时间：2017-11-24 21:10:27
	 * @return [{id:?,name:?}]
	 */
	@RequestMapping("getDeptKV.do")
	@ResponseBody
	public String getDeptKV(){
		String data=null;
		try {
			data = ser.getDept();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 获取区域id 和 name
	 * @return
	 */
	@RequestMapping("getCityKV.do")
	@ResponseBody
	public String getCityKV(){
		String data=null;
		try {
			data = ser.getCity();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 弹出上传页面
	 * @return
	 */
	@RequestMapping("uploadPage.do")
	public ModelAndView uploadPage(){
		ModelAndView  mv=new ModelAndView("/enforce/common/upload");
		 return mv;
	}
	/**
	 * 获取数据库表id 和 name
	 * @return
	 */
	@RequestMapping("getUserTable.do")
	@ResponseBody
	public String getUserTable(){
		String data=null;
		try {
			data = ser.getUserTable();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return data;
	}
}
