<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   
   <%@include file="/common/common.jsp" %>
   <style type="text/css">
    	table .name{
    		width:100px;
    		text-align: right;
    	}
    	
    </style>
  <head>
  
    
    <title>登录页面</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">

  </head>
  
  <body class="body1" style="overflow: auto;">
 	<div class="mini-panel" showHeader="false" style="border: red solid 1px;height: 95%;px;width: 100%;">
	
	<div id="form1">
	
	<fieldset>
  		<legend class="font_bold">登录</legend>
	<table>
	<tr>
	   <td class="name"><label><span style="color:red;">*</span>用户名：</label></td>
	   <td><input id="userName" name="userName"  vtype="maxLength:20"  required="true"   class="mini-textbox"/></td>
	
	   <td class="name"><label><span style="color:red;">*</span>密码：</label></td>
	   <td><input id="password" name="password"  vtype="maxLength:20"  required="true"   class="mini-textbox"/></td>
	
	</tr>
  		</table>
  	</fieldset>
	</div>
	
	<div id="hide2" style="margin:0 auto;text-align: center;margin-top:10px;margin-bottom:20px;">
		<a id="save" class="mini-button mini-button-success" onclick="onSaveSubmit">保存</a>
		<a  id="close" class="mini-button mini-button-danger" onclick="onClose">重置</a>
	</div>
	</div>
 
 <script type="text/javascript">
	mini.parse();
	var form = new mini.Form("#form1");
	
	
   
    
    
 	// 保存提交调用方法
	function onSaveSubmit() {
		var formdata = form.getData();//获取表单数据
		form.validate();//验证
		 if(!form.isValid()){
			mini.alert("数据校验不通过,请填写列表中标红色的格子！", "提示");
			return;
		} 
		if(true){
			var msgid = mini.loading("正在登录......","提示");
			$.ajax({
					type: 'POST',
					url: '${Path}userController/commitSave.do',
					dataType: 'json',
					data: {
							'forms':encodeURI(mini.encode(formdata))
						  },
					success: function(data) {
						if(data == "success"){
								location.href ="<%=path%>/enforce/index/indexList.do";
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
