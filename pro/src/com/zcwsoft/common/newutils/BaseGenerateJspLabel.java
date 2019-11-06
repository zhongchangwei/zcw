package com.zcwsoft.common.newutils;

import java.util.List;
import java.util.Map;

public class BaseGenerateJspLabel {

	public static void main(String[] args) {
		
	}

	/**
	 * 详细表单生成
	 * @param lsm
	 */
	public static void generateForm(List<Map> lsm){
		String splic = "";
		for (int i = 0; i < lsm.size(); i++) {
			Map<String,String> m = lsm.get(i);
		 if(m.get("FIELD")!=null&&m.get("TNAME")!=null&&m.get("DATATYPE")!=null){//字段
			 splic=splic+"<table>\n";
	            if("text".equals(m.get("DATATYPE"))){//下拉选择
	                splic+="<td class='name'><label><span style='color:red;'>*</span>"+m.get("TNAME")+"：</label></td>'\n"+
	                        "<td><input id='"+m.get("FIELD").toLowerCase()+"' name='"+m.get("FIELD")+"'  vtype='maxLength:20'  required='true'   class='mini-textbox'/></td>";
	            }else if("optiondata".equals(m.get("DATATYPE"))){
	                splic+="<td class='name'><label><span style='color:red;'>*</span>"+m.get("TNAME")+"：</label></td>'\n"+
	                        "<td><input id='"+m.get("FIELD").toLowerCase()+"' name='"+m.get("FIELD")+"'  valueField='pk' textField='name' " +
	                        		"data=\""+"[{pk:'1',name:'男'},{pk:'0',name:'女'}]"+"\" allowInput='false' \n" +
	                                "required='true'   class='mini-combobox' showNullItem='false'/></td>";
	            }else if("optionurl".equals(m.get("DATATYPE"))){
	                splic+="<td class='name'><label><span style='color:red;'>*</span>"+m.get("TNAME")+"：</label></td>'\n"+
	                        "<td><input id='"+m.get("FIELD").toLowerCase()+"' name='"+m.get("FIELD")+"'  valueField='pk' textField='name' " +
	                        		"url='${Path}common/getCityKV.do' allowInput='false' \n" +
	                                "required='true'   class='mini-combobox' showNullItem='false'/></td>";
	            }else if("date".equals(m.get("DATATYPE"))){//下拉选择
	                splic+="<td class='name'><label><span style='color:red;'>*</span>"+m.get("TNAME")+"：</label></td>'\n"+
	                        "<td><input id='"+m.get("FIELD").toLowerCase()+"' name='"+m.get("FIELD")+"' allowinput='false' required='true' class='mini-datepicker' " +
	                        		"format='yyyy-MM-dd' showTime='true'  showClearButton='true'/></td>";
	            }
	            splic+="\n";
	        }
		 if(!"".equals(splic)){
			 splic=splic+"</table>";
		 }
		 splic+="\n";
		}
		System.out.println(splic);
	}
	
	
	
	/**
	 * List列表生成
	 * @param lsm
	 */
	public static void generateList(List<Map> lsm){
		String splic = "";
		for (int j = 0; j < lsm.size(); j++) {
			Map<String,String> m =lsm.get(j);
		    if(m.get("FIELD")!=null){//字段
		        splic="<div field='"+m.get("FIELD")+"' headerAlign='center' ";
		    }
		    if(m.get("WIDTH")!=null){//宽度
		        splic=splic+"width='"+m.get("WIDTH")+"' ";
		    }
		    if(m.get("DATETYPE")!=null&&"date".equals(m.get("DATETYPE"))){//时间 1=是时间字段
		        if(m.get("DATEFORMAT")!=null){//显示的时间格式
		            splic=splic+" allowSort='true'  ";
		            splic=splic+" dateFormat='"+m.get("DATEFORMAT")+"' ";
		        }
		    }
		    if(m.get("ISHIDDEN")!=null&&"0".equals(m.get("ISHIDDEN"))){//是否隐藏0隐藏
		        splic=splic+" visible='false' ";
		    }else{
		    	splic=splic+" visible='true' ";
		    }
		    if(m.get("TNAME")!=null){//标题名称
		        splic=splic+">"+m.get("TNAME")+"</div>";
		    }else{
		        splic=splic+">unKnow</div>";
		    }
		}
		System.out.println(splic);
	}
	
	
	
}
