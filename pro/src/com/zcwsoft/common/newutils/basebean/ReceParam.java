package com.zcwsoft.common.newutils.basebean;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zcwsoft.common.newutils.MapUtils;
import com.zcwsoft.common.util.DES;
import com.zcwsoft.common.util.MD5;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class ReceParam extends BaseParamsBean {

	// //////////////////////////////////////////////
	private final String pk = "PK";//页面加载时主键KEY
	
	/**
	 * 加密字符串，防止空格。换行，双引号数据传送抛异常
	 * @param data
	 * @return
	 */
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
		Map m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.containsKey(pk)){
			return (String)m.get(pk);
		}
		return null;
	}
	/**
	 * 判断Form表单是否有主键
	 * @return
	 */
	public boolean isUpdate(){
		Map m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.containsKey(pk)&&this.isNotEmpty(m.get(pk)+"")){
			return true;
		}
		return false;
	}
	/**
	 * 判断Form表单是否有PK
	 * @return
	 */
	public boolean isSave(){
		Map m = this.getMapByJsonObj(super.getForms());
		if(!m.isEmpty()&&m.containsKey(pk)&&this.isEmpty(m.get(pk)+"")){
			return true;
		}
		return false;
	}
	/**
	 * * 判断 JSONObject 是否有主键
	 * @param json JSONObject数据
	 * @return
	 */
	public boolean isUpdate(String json){
		Map m = this.getMapByJsonObj(json);
		if(!m.isEmpty()&&m.containsKey(pk)){
			return true;
		}
		return false;
	}
	/**
	 * *获取List<Map>根据JSONArray
	 * @param jsonObj
	 * @return
	 */
	public Map getMapByJsonObj(String jsonObj){
		try {
			if (jsonObj == null){
				return null;
			}
			Map rs = this.getJSONObjectByJson(jsonObj);
			return rs;
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
	public List<Map> getListMapByJsonArray(String jsonArray){
		try {
			if (jsonArray == null)
				return null;
			return this.getJSONArrayByJson(jsonArray);
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
	public <T> T  getBeanAdd(T t,String jsonObj,String...parms)throws Exception{
		Map mapByJsonObj = this.getMapByJsonObj(jsonObj);
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
	public <T> List<T>  getListBeanAdd(T t,String jsonArray,String...parms) throws Exception{
		List<Map> listMapByJsonArray = this.getListMapByJsonArray(jsonArray);
		List<Map> putListAdd = MapUtils.putListAdd(listMapByJsonArray, parms);
		return (List<T>)MapUtils.getListBeanByMap(t,putListAdd,pk);

	}
	
	/**
	 * 返回加密的字符串
	 */
	public  String getDesAttribute(Object value) throws Exception {
		String sql = DES.encrypt(value.toString());
		JSONObject o = new JSONObject();
		o.put("sql", sql);
		String temp = DES.encrypt(o.toString());
		String md5Str = MD5.getMD5(temp);//校验串
		return temp+md5Str;
	}
	public  String Encode(Object obj) {
		if(obj == null || obj.toString().equals("null")) return null;
		if(obj != null && obj.getClass() == String.class){
			return obj.toString();
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
		return serializer.deepSerialize(obj);
	}
	
	/****************************获取Utils工具对象******************************************/
	/**
	 * 功能：获取操作request对象工具类
	 * @return
	 */
	public RequestUtils getRequestUtils(){
		return new RequestUtils(super.getRequest());
	}
	/**
	 * 功能：获取容器中的对象，根据注入名称
	 * @param beanId 	@/Service(beanId)
	 * @return 	容器对象
	 */
	public Object getIocBean(String beanId){
		ServletContext sc=super.getSession().getServletContext();
		WebApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		//WebApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(sc);
		//ac2.getBean("beanId"); 
		return ac1.getBean(beanId);  
	}

	/**
	* 编码的json转JSONArray
	* 作者：zcw
	* 开发时间：2017-11-17 20:27:29
	* @return
	*/
	public List<Map> getJSONArrayByJson(String json) throws IOException{
		String jsonStr = URLDecoder.decode(json,"utf-8");
		List<Map> jArray = JSONArray.fromObject(jsonStr);
		if(jArray==null){
			return null;
		}
		List<Map> m = jArray;
		//List<Map<String,Object>> rsm = new ArrayList<Map<String,Object>>();
		List<Map> rsm = new ArrayList<Map>();
		for (int i = 0; i < m.size(); i++) {
			rsm.add(this.getM(m.get(i)));
		}
		return rsm;
	}
	
	/**
	 * 功能：编码的json转JSONObject
	 * 作者：zcw
	 * 开发时间：2017年11月17日20:27:44
	 */
	public Map getJSONObjectByJson(String json)throws IOException{
		String jsonStr = jodd.servlet.URLDecoder.decode(json, "utf-8");
		Map obejct = JSONObject.fromObject(jsonStr);
		if(obejct==null){
			return null;
		}
		Map<String, Object> rsm = getM(obejct);
		return rsm;
	}
	/**
	 * 字符串null替换null
	 * @param m
	 * @return
	 */
	private Map<String,Object> getM(Map<String,Object> m){
		Map<String,Object> rsm = new HashMap<String,Object>();
		for (String key:m.keySet()) {
			if(m.get(key)==null||"null".equals(m.get(key))||"".equals(m.get(key))||"\"null\"".equals(m.get(key))){
				rsm.put(key, "");
			}else{
				rsm.put(key, m.get(key));
			}
		}
		return rsm;
	}
	/**
	 * 得到sql文本
	 * @param obj  与对象的相对路径
	 * @param key  XML文件键值
	 * @param fileName 文件名字
	 * @return
	 */
	public String getSqlText(Object obj, String fileName, String key)throws Exception {		
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(obj.getClass().getResource("").getPath().concat(fileName)));
		Element rootElm = document.getRootElement();
		return rootElm.element(key).getTextTrim();
	}
	
	public boolean isEmpty(String str) {
		try{
			if("".equals(str)||str==null||"null".equals(str)){
				return true;
			}
		}catch(Exception e){}
		return false;
	}
	
	public boolean isNotEmpty(String str){	
		try{
			if("".equals(str)||str==null||"null".equals(str)){
				return false;
			}
		}catch(Exception e){}
		return true;
	}
	public boolean equals(String st,String eq){
		if(eq.equals(st)){
		return true;
		}
		return false;
	}
	/**********************************************************************/
	
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
	/**
	 * 数组合并
	 * @param a
	 * @param b
	 * @return
	 */
	public String[] mergeArray(String[] a,String[] b){
		String[] result = new String[a.length+b.length];
		for (int i = 0; i < result.length; i++) {
			if(a.length>i){
			result[i] = a[i];
			}else{
				result[i] = b[i];
			}
		}
		return result;
		
	}
	

}
