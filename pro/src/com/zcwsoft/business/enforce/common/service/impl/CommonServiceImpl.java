package com.zcwsoft.business.enforce.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.business.enforce.common.service.CommonService;
import com.zcwsoft.common.newutils.CommonManage;
import com.zcwsoft.common.util.BaseScanSqlCommand;
import com.zcwsoft.common.util.Json;
@Service("commonService")
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonDao dao;

	

	/**
	 * 功能：部门列表树形加载
	 * 时间：2017年8月27日20:24:54
	 * 作者：zcw
	 * @return
	 */
	@Override
	public String treeList() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "treeList");
		return CommonManage.getDesAttribute(sql);
	}
	/**
	 * 功能：菜单列表树形加载
	 * 时间：2017年8月27日20:24:54
	 * 作者：zcw
	 * @return
	 */
	@Override
	public String treeListToCodeMenu() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "treeListToCodeMenu");
		return CommonManage.getDesAttribute(sql);
	}
	
	/**
	 * 作者：zcw
	 * 功能：获取部门id 和 name
	 * 时间：2017-12-9 18:37:19
	 * @return [{id:?,name:?}]
	 */
	@Override
	public String getCodeMenu() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "getCodeMenu");
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}

	/**
	 * 作者：zcw
	 * 功能：获取部门id 和 name
	 * 时间：2017-11-24 21:10:27
	 * @return [{id:?,name:?}]
	 */
	@Override
	public String getDept() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "getDept");
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}
	/**
	 * 作者：zcw
	 * 功能：获取区域id 和 name
	 * 时间：2017-11-24 21:10:27
	 * @return [{id:?,name:?}]
	 */
	@Override
	public String getCity() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "getCity");
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}
	/**
	 * 作者：zcw
	 * 功能：获取当前数据库用户的所有表
	 * 时间：2017-12-16
	 * @return [{id:?,name:?}]
	 */
	@Override
	public String getUserTable() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "commonSql.xml", "getUserTable");
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql);
	}
}
