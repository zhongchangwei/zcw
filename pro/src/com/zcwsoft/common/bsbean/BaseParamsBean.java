package com.zcwsoft.common.bsbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.restlet.engine.adapter.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 功能：用于前台传参到后台时，mvc直接使用该对象接收
 * 时间：2017-11-24 21:25:45
 * @author zcw
 *
 */
public class BaseParamsBean implements Serializable {
	private static final long serialVersionUID = -7636957952794611114L;
	
	
	private String id;
	private String ids;
	private String type;
	private String day;
	private String month;
	private String year;
	private String tableName;
	
	
	private String param;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	
	private String forms;
	private String forms1;
	private String forms2;
	private String addRows;
	private String addRows1;
	private String addRows2;
	private String modifyRows;
	private String modifyRows1;
	private String modifyRows2;
	
	private Object object;
	private List list;
	private Map map;
	private List<Map> listMap;
	
	
	//private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private HttpServletRequest request;
	
	
	public BaseParamsBean() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDay() {
		return day;
	}


	public void setDay(String day) {
		this.day = day;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}


	public String getParam1() {
		return param1;
	}


	public void setParam1(String param1) {
		this.param1 = param1;
	}


	public String getParam2() {
		return param2;
	}


	public void setParam2(String param2) {
		this.param2 = param2;
	}


	public String getParam3() {
		return param3;
	}


	public void setParam3(String param3) {
		this.param3 = param3;
	}


	public String getParam4() {
		return param4;
	}


	public void setParam4(String param4) {
		this.param4 = param4;
	}


	public String getParam5() {
		return param5;
	}


	public void setParam5(String param5) {
		this.param5 = param5;
	}


	public String getParam6() {
		return param6;
	}


	public void setParam6(String param6) {
		this.param6 = param6;
	}


	public String getParam7() {
		return param7;
	}


	public void setParam7(String param7) {
		this.param7 = param7;
	}

	
	
	

	
	
	
/////////////////////////////////////////////////////////////////////////////	
	
	public HttpServletResponse getResponse() {
		if(this.response==null){
			HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse(); 
			   return response;
		}
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public HttpSession getSession() {
		if(this.session==null){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		    HttpSession session=request.getSession();
		    return session;
		}
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	public HttpServletRequest getRequest() {
		if(this.request==null){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			return request;
		}
		return this.request;
	}


	
	
	
///////////////////////////////////////////////////////////////////////////
	
	
	
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public Object getObject() {
		return object;
	}


	public void setObject(Object object) {
		this.object = object;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public Map getMap() {
		return map;
	}


	public void setMap(Map map) {
		this.map = map;
	}


	public List<Map> getListMap() {
		return listMap;
	}


	public void setListMap(List<Map> listMap) {
		this.listMap = listMap;
	}
	
	
	
	
	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////
	
	
	
	


	public String getForms() {
		return forms;
	}


	public void setForms(String forms) {
		this.forms = forms;
	}


	public String getForms1() {
		return forms1;
	}


	public void setForms1(String forms1) {
		this.forms1 = forms1;
	}


	public String getForms2() {
		return forms2;
	}


	public void setForms2(String forms2) {
		this.forms2 = forms2;
	}


	public String getAddRows() {
		return addRows;
	}


	public void setAddRows(String addRows) {
		this.addRows = addRows;
	}


	public String getAddRows1() {
		return addRows1;
	}


	public void setAddRows1(String addRows1) {
		this.addRows1 = addRows1;
	}


	public String getAddRows2() {
		return addRows2;
	}


	public void setAddRows2(String addRows2) {
		this.addRows2 = addRows2;
	}


	public String getModifyRows() {
		return modifyRows;
	}


	public void setModifyRows(String modifyRows) {
		this.modifyRows = modifyRows;
	}


	public String getModifyRows1() {
		return modifyRows1;
	}


	public void setModifyRows1(String modifyRows1) {
		this.modifyRows1 = modifyRows1;
	}


	public String getModifyRows2() {
		return modifyRows2;
	}


	public void setModifyRows2(String modifyRows2) {
		this.modifyRows2 = modifyRows2;
	}


	
	
}
