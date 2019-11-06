package com.zcwsoft.business.common.miniui.dao.impl;

import org.springframework.stereotype.Repository;

import com.zcwsoft.business.common.miniui.dao.LoadDataDao;
import com.zcwsoft.common.dao.HibernateBaseDao;
import com.zcwsoft.common.util.Json;
/**
 * 
*  <pre>    
* 类名称：BaseDaoImpl 
* 类描述：   
* 创建人：cyc
* 创建时间：2016年3月4日09:01:12
* 修改人：Administrator   
* 修改时间：2016年3月4日09:01:16
* 修改备注：   
* @version V1.0
* </pre>
 */
@Repository("LoadDataDaoImpl")
public class LoadDataDaoImpl extends HibernateBaseDao implements LoadDataDao {

	/**
	 * {"total": 1111,"data": [{ },{ },{ }]}
	 * 作者：cyc
	 * 开发时间：2016年4月28日14:43:13
	 * @return
	 * @throws Exception 
	*/
	@Override
	public String load(String data, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields) throws Exception {
		return pagingMiniUI(data, pageIndex, pageSize, sortField, sortOrder, sortFields);
	}

	/**
	* 加载数据,返回标准的MiniUi格式的JSON
	* [{ },{ },{ }]
	* 作者：cyc
	* 开发时间：2016年4月28日14:43:24
	* @return
	*/
	@Override
	public String loadArray(String sql) throws Exception {
		return Json.Encode(queryForListMapBySql(sql));
	}

}
