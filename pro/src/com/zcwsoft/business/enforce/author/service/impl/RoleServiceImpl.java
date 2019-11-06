package com.zcwsoft.business.enforce.author.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.author.service.RoleService;
import com.zcwsoft.business.enforce.common.dao.impl.CommonDaoImpl;
import com.zcwsoft.business.enforce.author.bean.RoleBean;
import com.zcwsoft.common.util.BaseScanSqlCommand;
import com.zcwsoft.common.newutils.MapUtils;
import com.zcwsoft.common.newutils.basebean.ReceParam;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	private CommonDaoImpl dao;

	
	
	/**
	 * 作者：?
	 * 功能：加载列表
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String treeList(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "roleSql.xml", "treeListToRole");
		return rcep.getDesAttribute(sql);
	}
	
	/**
	 * 作者：?
	 * 功能：加载列表
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String list(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "roleSql.xml", "list");
		return rcep.getDesAttribute(sql);
	}

	/**
	 * 作者：?
	 * 功能：新增  修改（回显）弹出页面表单回显
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String formShowData(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "roleSql.xml", "formShowData");
		sql=sql+" and t.Id='"+rcep.getId()+"'";
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return rcep.Encode(listMapBySql.get(0));
	}
	/**
	 * 作者：?
	 * 功能：新增  修改（回显）弹出页面grid1回显
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String grid1ShowData(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "roleSql.xml", "grid1ShowData");
		return rcep.getDesAttribute(sql);
	}
	
	/**
	 * 作者：?
	 * 功能：新增  修改（回显）弹出页面grid1回显
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String grid2ShowData(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "roleSql.xml", "grid2ShowData");
		return rcep.getDesAttribute(sql);
	}
	
	/**
	 * 作者：?
	 * 功能：保存
	 * 时间：2018-02-28
	 * @return 
	 */
	@Override
	public String save(ReceParam rcep) throws Exception {
		Map msm = new HashMap();//设置key value 传回页面
		RoleBean bean = new RoleBean();//实体bean
		
		String id = null;//一般用于回显 和 设置从表外键
		String flag = "";//标识是否成功
		Integer num =0;	//操作了多少行数据
		
//********************操作Form表单***************************************
		if(rcep.isEmpty(rcep.getForms())){
			//如果是空就不操作，此处防止没有表单数据却传了表单字段回来
		}else if(rcep.isUpdate()){//判断是否为修改
			//单个修改(表单From)
			Map obj = rcep.getMapByJsonObj(rcep.getForms());
			id = obj.get("PK")+"";
			String[] keyValue = {};//自定义追加、替换操作，如果没有就put进去，有则替换（Map特性） 例如：{"id","id"}
			Map putUpdate = MapUtils.putUpdate(obj,keyValue);
			num+= dao.updateBean(bean, putUpdate);
		}else if(rcep.isSave()){//判断是否为新增
			//单个新增(表单From)
			String[] keyValue = {};
			RoleBean beanAdd = rcep.getBeanAdd(bean, rcep.getForms(),keyValue);
			id = beanAdd.getId();
			num+= dao.saveBean(beanAdd);
		}
		
		flag="操作成功："+num+"条";
		msm.put("flag", flag);
		msm.put("id", id);
		return rcep.Encode(msm);
	}
	/**
	 * 作者：？
	 * 功能：删除
	 * 时间：2018-02-28
	 * @return
	 * @throws Exception
	 */
	@Override
	public String remove(ReceParam rcep) throws Exception{
		Map msg = new HashMap();//设置key value 传回页面
		String sql = "update t_s_role set state = '0' where Id in('"+rcep.getIds()+"')";
		int i = dao.execute(sql);
		msg.put("flag", "删除了："+String.valueOf(i)+"条。");
		return rcep.Encode(msg);
	}

	
}