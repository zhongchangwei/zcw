package com.zcwsoft.business.enforce.base.controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.base.service.GenertaFieldService;
import com.zcwsoft.common.dao.HibernateBaseDao;
import com.zcwsoft.common.newutils.basebean.ReceParam;

@Controller
@RequestMapping("genertaFieldController")
public class GenertaFieldController {
	@Autowired
	private GenertaFieldService ser;

	public Logger log =  LoggerFactory.getLogger(GenertaFieldController.class);
	
	/**
	 * 作者：zcw
	 * 功能：加载列表
	 * 时间：2017-11-24
	 * @return 
	 */
	@RequestMapping("list")
	public ModelAndView list(ReceParam rcep){
		ModelAndView mv=new ModelAndView("/enforce/base/genertaFieldList");
		try {
			String data = ser.list(rcep);
			mv.addObject("data", data);
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
		ModelAndView mv = new ModelAndView("/enforce/base/genertaFieldDetail");
		String id = rcep.getId();
		String dataShow=null;
		try{
			if(rcep.isNotEmpty(id)){//跳转修改页面操作
				dataShow = ser.formShowData(rcep);//表单回显
				String grid1ShowData = ser.grid1ShowData(rcep);//grid回显
				String maxSort = ser.getMaxSortByTabName(rcep);//获取某个表名中字段排序的最大排序
				mv.addObject("maxSort", maxSort);//排序
				mv.addObject("tableName", rcep.getParam());//表名
				mv.addObject("grid1ShowData", grid1ShowData);//grid1回显
				mv.addObject("dataShow",rcep.getURLEncoder(dataShow));//表单回显
			}else{
				//mv.addObject("tableName", rcep.getParam().toUpperCase());//表名
				String addShowGrid1Data = ser.grid1AddShowData(rcep);//加载该表所有字段（新增时，输完表名触发）
				mv.addObject("addShowGrid1Data", addShowGrid1Data);//加载该表所有字段（新增时，输完表名触发）
				
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
				data = ser.Save(rcep);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 作者：zcw
	 * 功能：删除
	 * 时间：2017-11-24
	 * @return 
	 */
	@RequestMapping("remove.do")
	@ResponseBody
	public Object remove(ReceParam aps){
		String data="";
		try{
				data = ser.remove(aps);
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 作者：zcw
	 * 功能：生成页面标签
	 * 时间：2017年12月2日19:23:04
	 * @return 
	 */
	@RequestMapping("generateLabel.do")
	@ResponseBody
	public String generateLabel(ReceParam rcep){
		String data = "";
		try{
			data = ser.generateLabel(rcep);
		}catch(Exception e){
			data+=e.getMessage();
			e.printStackTrace();
		}
		return data;
	}
	/**
	 * 获取数据库表id 和 name
	 * @return
	 */
	@RequestMapping("loadPaths.do")
	@ResponseBody
	public String loadPaths(){
		int result = 0;
		File file = null;
		String path = null;
		JFileChooser fileChooser = new JFileChooser();
		FileSystemView fsv = FileSystemView.getFileSystemView();  //注意了，这里重要的一句
		System.out.println(fsv.getHomeDirectory());                //得到桌面路径
		fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
		fileChooser.setDialogTitle("请选择要上传的文件...");
		fileChooser.setApproveButtonText("确定");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		result = fileChooser.showOpenDialog(null);
		if (JFileChooser.APPROVE_OPTION == result) {
		    	   path=fileChooser.getSelectedFile().getPath();
		    	   System.out.println("path: "+path);
		   }
		return path;
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
