<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>部门详细</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
   <%@include file="/common/common.jsp" %>
   
   <style type="text/css">
    	table .name{
    		width:100px;
    		text-align: right;
    	}
    	
    </style>
  </head>
<body class="body1" style="overflow: auto;">
	<div class="mini-panel" showHeader="false" style="border: red solid 1px;height: 95%;px;width: 100%;">
	
	<div id="form1">
	<input id="pkId" name="PK"  class="mini-textbox" visible="false"/>
	
	<fieldset>
  		<legend class="font_bold">部门信息</legend>
	<input id="pk" class="mini-hidden"/>
	<table>
	<tr>
	   <td class="name"><label><span style="color:red;">*</span>部门名称：</label></td>
	   <td><input id="dept_name" name="DEPT_NAME"  vtype="maxLength:20"  required="true"   class="mini-textbox"/></td>
	
	   <td class="name"><label><span style="color:red;">*</span>上级部门：</label></td>
	   <td><input id="pid" name="PID"  valueField="PK" textField="NAME"    url="${Path}common/getDeptKV.do" allowInput="false"    required="true"   class="mini-combobox" showNullItem="true"/></td>

	   <td class="name"><label><span style="color:red;">*</span>备注：</label></td>
	   <td><input id="remark" name="REMARK"  vtype="maxLength:20"  required="true"   class="mini-textbox"/></td>
	
	</tr>
  		</table>
  	</fieldset>
	</div>
	
	<div id="hide2" style="margin:0 auto;text-align: center;margin-top:10px;margin-bottom:20px;">
		<a id="save" class="mini-button mini-button-success" onclick="onSaveSubmit">保存</a>
		<a  id="close" class="mini-button mini-button-danger" onclick="onClose">关闭</a>
	</div>
	</div>
	
	<iframe id="downloadfile" style="display: none"></iframe>
<script type="text/javascript">
	mini.parse();
	var form = new mini.Form("#form1");
	
	
   
    
    //修改时回显数据
    var formData='${dataShow}';
    if(formData!=''){
    	//mini.get("pid").setValue(${pid});
    	var data = mini.decode(decodeURIComponent(formData));
    	//加载表单数据
    	form.setData(data);
    	alert(${pid});
    	mini.get("pid").setValue(${pid});
    }
    
    
 	// 保存提交调用方法
	function onSaveSubmit() {
		var formdata = form.getData();//获取表单数据
		form.validate();//验证
		 if(!form.isValid()){
			mini.alert("数据校验不通过,请填写列表中标红色的格子！", "提示");
			return;
		} 
		if(true){
			//var addRows = grid2.getChanges('added', false);//获得新增行
			//var modifyRows = grid2.getChanges('modified', false);//获得修改行	
			var msgid = mini.loading("操作中，请稍后......","提示");
			$.ajax({
					type: 'POST',
					url: '${Path}userController/commitSave.do',
					dataType: 'json',
					data: {
							//'addeRows':encodeURI(mini.encode(addRows)),
							//'modifyRows':encodeURI(mini.encode(modifyRows)),
							'forms':encodeURI(mini.encode(formdata))
						  },
					success: function(data) {
						if(data!=""){
							mini.alert("保存成功！"+data.flag, "提示",function(){
								location.href ="<%=path%>/deptController/openDetail.do?id="+data.id;
								});
						}
						mini.hideMessageBox(msgid);
					},
					error:function() {
						mini.hideMessageBox(msgid);
						mini.alert("提交数据时发生错误，请与管理员联系！", "提示");
					}
			});
		} else {
			mini.alert("数据校验不通过,请填写红色格子区域", "提示");
		}
	}
	
 	
 	

    
	
  
		
	
    
</script>
  </body>
</html>