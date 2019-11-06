package com.zcwsoft.business.common.miniui.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcwsoft.business.common.miniui.service.LoadDataService;

/**
 * 
*  <pre>    
* 类名称：LoadDataController 
* 类描述：   MiniUI加载数据公用类
* 创建人：cyc
* 创建时间：2016年4月27日16:31:06
* 修改人：cyc
* 修改时间：2016年4月27日16:31:09
* 修改备注：   
* @version V1.0
* </pre>
 */
@Controller
@RequestMapping("/loadMiniUIData")
public class LoadDataController {

	private Logger log = Logger.getLogger(LoadDataController.class);
	
	@Autowired
	private LoadDataService loadDataService;
	
	/**
	 * 
	 * 需求功能概述:加载数据,返回标准的MiniUi格式的JSON,{"total": 1111,"data": [{ },{ },{ }]}
	 * 作者：cyc
	 * 开发时间：2016年4月28日14:28:00
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value="/load.do")
	@ResponseBody
	public String load(String data, String replace, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields) {
		String json = "";
		try {
			json = loadDataService.load(data, replace, pageIndex, pageSize, sortField, sortOrder, sortFields);
		} catch (Exception e) {
			log.equals(e);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 需求功能概述:加载数据,返回标准的MiniUi格式的JSON，[{ },{ },{ }]
	 * 作者：cyc
	 * 开发时间：2016年4月28日14:29:39
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping(value="/loadArray.do")
	@ResponseBody
	public String loadArray(String data) {
		String json = "";
		try {
			json = loadDataService.loadArray(data);
		} catch (Exception e) {
			log.equals(e);
			e.printStackTrace();
		}
		return json;
	}
	
}
