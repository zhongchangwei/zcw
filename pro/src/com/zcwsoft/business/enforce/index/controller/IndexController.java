package com.zcwsoft.business.enforce.index.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.dbunit.ant.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.index.service.IndexService;
import com.zcwsoft.common.newutils.pub.util.TableIndexUtils;
import com.zcwsoft.common.newutils.redisutils.where.RedisMultiUtil;


@Controller
@RequestMapping("/enforce/index")
public class IndexController {
	
	private Logger log = Logger.getLogger(IndexController.class);
	
	@Autowired
	private IndexService serv;
	
	
	@RequestMapping("/indexList.do")
	public ModelAndView index(HttpSession session){
		ModelAndView view =new ModelAndView("enforce/index/indexList");
		try{
			//System.out.println(new TableIndexUtils("T_S_USER").getUniquePrimaryField());
			test();
			String indexTreeData = serv.indexTreeData(session);
			view.addObject("indexTreeData", indexTreeData);
			view.addObject("loginUserName", "Admin");
		}
		catch(Exception e){
			log.equals(e);
			e.printStackTrace();
		}
		return view;
	} 

	
	public void test()
	{
		TableIndexUtils ts = new TableIndexUtils("t_s_user");
		Map fieldToBeanMapping = ts.getFieldToBeanMapping();
		System.out.println(fieldToBeanMapping);
		/*
		Long s = new Date().getTime();
		System.out.println(new Date().getTime());
		RedisMultiUtil rs = new RedisMultiUtil();
		rs.setTableName("t_s_user");
//		rs.setEqualAnd("id", "20180707204505366V14687");
		rs.setEqualAnd("id", "20180707204505411V18677");
		rs.setEqualAnd("tname", "模块");
//		rs.setEqualOr("id", "20180707204505411V18677");
//		rs.setLikeOr("id", "20180707204505411V18677");
//		rs.setLikeOr("id", "20180707204505366V14687");
//		rs.setEqualOr("id", "b");
//		rs.setEqualOr("id", "a");
//		rs.setEqualOr("id", "d");
////		rs.setEqualAnd("NAME_", "用户管理");
////		rs.setLike("NAME_", "部门");
//		rs.setGreaterEqAnd("sort", "2");
//		rs.setBeginEndNotEq("sort", "1", "9");
//		rs.setNotEqual("id", "b");
		List<Map> execute = rs.execute();
		System.out.println(new Date().getTime()-s);
		System.out.println(execute.size());
		for (int i = 0; i < execute.size(); i++) {
			System.out.println(execute.get(i).get("ID")+"||"+execute.get(i).get("SORT")+"||"+execute.get(i));
		}*/
	}
}
