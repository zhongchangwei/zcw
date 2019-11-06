package com.zcwsoft.common.po;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zcwsoft.common.NewUtils.MapUtils;
import com.zcwsoft.common.util.CommonManage;

public class ReceParam extends SuperParams {

	// //////////////////////////////////////////////
	private final String pk = "PK";//页面加载时主键KEY

	
	
	
	public String getURLEncoder(String data){
		try {
			return URLEncoder.encode(data,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * * 获取Form表单主键值
	 * @return
	 */
	public String getFormsPk(){
		JSONObject m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.has(pk)){
			return m.getString(pk);
		}
		return null;
	}
	/**
	 * 判断Form表单是否有主键
	 * @return
	 */
	public boolean isupdate(){
		JSONObject m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.has(pk)&&StringUtils.isNotEmpty(m.getString(pk))){
			return true;
		}
		return false;
	}
	/**
	 * 判断Form表单是否有PK
	 * @return
	 */
	public boolean isSave(){
		JSONObject m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.has(pk)&&StringUtils.isEmpty(m.getString(pk))){
			return true;
		}
		return false;
	}
	/**
	 * * 判断 JSONObject 是否有主键
	 * @param json JSONObject数据
	 * @return
	 */
	public boolean isupdate(String json){
		JSONObject m = this.getMapByJsonObj(json);
		if(!m.isEmpty()&&m.has(pk)){
			return true;
		}
		return false;
	}
	/**
	 * *获取List<Map>根据JSONArray
	 * @param jsonObj
	 * @return
	 */
	public JSONObject getMapByJsonObj(String jsonObj){
		try {
			if (jsonObj == null)
				return null;
			return CommonManage.getJSONObjectByJson(jsonObj);
		} catch (IOException e) {
			System.out.println("异常位置:"+this.getClass().getName()+"\n参数:"+jsonObj);
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * *获取List<Map>根据页面穿回来的JSONArray
	 * @param jsonArray
	 * @return
	 */
	public JSONArray getListMapByJsonArray(String jsonArray){
		try {
			if (jsonArray == null)
				return null;
			return CommonManage.getJSONArrayByJson(jsonArray);
		} catch (IOException e) {
			System.out.println("异常位置:"+this.getClass().getName()+"\n参数:"+jsonArray);
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * 功能：吧json数据赋值到实体Bean，可追加（追加意思是，json没有的数据，可在parms数组中加入）
	 * @param t			实体类，字段对应json的key	  
	 * @param jsonObj 	json数据 格式：{key:value}
	 * @param parms 	格式：key,value,key,value
	 * @return
	 */
	public <T> T  getBeanAdd(T t,String jsonObj,String...parms){
		JSONObject mapByJsonObj = this.getMapByJsonObj(jsonObj);
		Map m = MapUtils.putAdd(mapByJsonObj, parms);
		return (T)MapUtils.getBeanByMap(t,m,pk);
	}
	/**
	 * 功能批量赋值到实体Bean ,
	 * @param t 			同 getBeanAdd（）
	 * @param jsonArray		List<json> 
	 * @param parms 		同 getBeanAdd（）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T>  getListBeanAdd(T t,String jsonArray,String...parms){
		List<Map> listMapByJsonArray = this.getListMapByJsonArray(jsonArray);
		List<Map> putListAdd = MapUtils.putListAdd(listMapByJsonArray, parms);
		return (List<T>)MapUtils.getListBeanByMap(t,putListAdd,pk);

	}
	/**
	 * 功能批量赋值到实体Bean ,
	 * @param t 			同 getBeanAdd（）
	 * @param jsonArray		List<json> 
	 * @param parms 		同 getBeanAdd（）
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public <T> T  getBeanUpdate(T t,String jsonObcj,String...parms){
		
		t = MapUtils.getBeanByMap(t,obj,"update");
		updateObj(t);
		
	}*/
	@Override
	public String toString() {
		return "ReceParam [getId()=" + getId() + ", getIds()=" + getIds()
				+ ", getType()=" + getType() + ", getDay()=" + getDay()
				+ ", getMonth()=" + getMonth() + ", getYear()=" + getYear()
				+ ", getParam()=" + getParam() + ", getParam1()=" + getParam1()
				+ ", getParam2()=" + getParam2() + ", getParam3()="
				+ getParam3() + ", getParam4()=" + getParam4()
				+ ", getParam5()=" + getParam5() + ", getParam6()="
				+ getParam6() + ", getParam7()=" + getParam7()
				+ ", getRequest()=" + getRequest() + ", getResponse()="
				+ getResponse() + ", getSession()=" + getSession()
				+ ", getForms()=" + getForms() + ", getForms1()=" + getForms1()
				+ ", getForms2()=" + getForms2() + ", getAddRows()="
				+ getAddRows() + ", getAddRows1()=" + getAddRows1()
				+ ", getAddRows2()=" + getAddRows2() + ", getModifyRows()="
				+ getModifyRows() + ", getModifyRows1()=" + getModifyRows1()
				+ ", getModifyRows2()=" + getModifyRows2() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
