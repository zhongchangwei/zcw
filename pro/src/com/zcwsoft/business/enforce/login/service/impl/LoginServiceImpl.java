package com.zcwsoft.business.enforce.login.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.business.enforce.login.service.LoginService;
import com.zcwsoft.common.newutils.CommonManage;
import com.zcwsoft.common.util.BaseScanSqlCommand;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CommonDao comdao;

	/**
	 * 功能：获取首页菜单数据
	 * 作者：zcw
	 * 开发时间：2017-11-10 20:46:48
	 */
	@Override
	public String loginTreeData(HttpSession session) throws Exception {
		String sql = new BaseScanSqlCommand().getSqlText(this, "sql.xml", "loginTree");
		return CommonManage.getDesAttribute(sql);
	}
	
	
	
}
