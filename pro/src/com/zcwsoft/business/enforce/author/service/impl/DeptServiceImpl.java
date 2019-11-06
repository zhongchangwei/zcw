package com.zcwsoft.business.enforce.author.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.author.service.DeptService;
import com.zcwsoft.business.enforce.common.dao.impl.CommonDaoImpl;
import com.zcwsoft.common.enforce.bean.TCodeInfo;
import com.zcwsoft.common.enforce.bean.TSDept;
import com.zcwsoft.common.newutils.CommonManage;
import com.zcwsoft.common.newutils.MapUtils;
import com.zcwsoft.common.newutils.basebean.ReceParam;
import com.zcwsoft.common.util.BaseScanSqlCommand;
import com.zcwsoft.common.util.Json;

@Service("deptService")
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private CommonDaoImpl dao;
	
	
	
	/**
	 * 功能：列表加载
	 * 时间：2017年8月27日20:24:54
	 * 作者：zcw
	 * @return
	 */
	@Override
	public String list() throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "deptSql.xml", "list");
		return CommonManage.getDesAttribute(sql);
	}
	
	
	/**
	 * 作者：zcw
	 * 功能：新增  修改（回显）弹出页面
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String showData(ReceParam rcep) throws Exception {
		String sql=new BaseScanSqlCommand().getSqlText(this, "deptSql.xml", "showFormData");
		sql=sql+" and u.id='"+rcep.getId()+"'";
		List<Map> listMapBySql = dao.getListMapBySql(sql);
		return Json.Encode(listMapBySql.get(0));
	}

	/**
	 * 作者：zcw
	 * 功能：保存
	 * 时间：2017-11-24
	 * @return 
	 */
	@Override
	public String save(ReceParam rcep) throws Exception {
		
		Map msm = new HashMap();
		TSDept bean = new TSDept();
		String flag = "";
		Integer num =0;
		String id ="";
		//操作Form表单
		if(StringUtils.isEmpty(rcep.getForms())){
			//如果是空就不操作，此处防止没有表单数据却传了表单字段回来
		}else if(rcep.isUpdate()){//判断是否为修改
			//修改单个
			Map obj = rcep.getMapByJsonObj(rcep.getForms());
			id = obj.get("PK")+"";
			Map putUpdate = MapUtils.putUpdate(obj);
			num+= dao.updateBean(bean, putUpdate);
		}else if(rcep.isSave()){//判断是否为新增
			//新增单个
			TSDept beanAdd = rcep.getBeanAdd(bean, rcep.getForms());
			id = beanAdd.getId();
			num+= dao.saveBean(beanAdd);
		}
		//获取新增时的主键
		
		/**操作grid新增*/
		if(StringUtils.isNotEmpty(rcep.getAddRows())){
			//批量新增
			List<TSDept> listBeanAdd = rcep.getListBeanAdd(bean, rcep.getAddRows());
			num+= dao.saveBeans(listBeanAdd);
		}
		/**操作grid修改*/
		if(StringUtils.isNotEmpty(rcep.getModifyRows())){
			//批量修改
			List<Map> obj = rcep.getListMapByJsonArray(rcep.getModifyRows());
			List<Map> putUpdate = MapUtils.putListUpdate(obj);
			num+= dao.updateBeans(bean, putUpdate);
		}
		flag="操作成功："+num+"条";
		msm.put("flag", flag);
		msm.put("id", id);
		return Json.Encode(msm);
		
		
		
	}


	@Override
	public String delete(ReceParam aps) throws Exception {
		return null;
	}

}
