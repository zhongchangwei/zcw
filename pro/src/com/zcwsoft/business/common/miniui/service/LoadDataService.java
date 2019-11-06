package com.zcwsoft.business.common.miniui.service;
/**
 * 
*  <pre>    
* 类名称：LoadDataService 
* 类描述：   
* 创建人：cyc
* 创建时间：2016年4月27日16:37:41
* 修改人：Administrator   
* 修改时间：2016年4月27日16:37:46
* 修改备注：   
* @version V1.0
* </pre>
 */
public interface LoadDataService {

	String load(String data, String replace, Integer pageIndex, Integer pageSize, String sortField, String sortOrder, String sortFields) throws Exception;

	String loadArray(String data) throws Exception;

}
