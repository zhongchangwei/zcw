<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<script src="<%=path%>/res/miniui/scripts/boot.js" type="text/javascript"></script>
	<link href="<%=path%>/res/miniui/scripts/miniui/themes/bootstrap/skin.css" rel="stylesheet" type="text/css"/>
</head> 
<body topmargin="0" bottommargin="0" rightmargin="0" leftmargin="0" style="height: 100%" >
	<div style="padding-left:5px;padding-top:30px;padding-bottom:5px;padding-right:5px;height:150px;">
	<fieldset style="border:solid 1px #aaa;padding:3px;height:150px">
		<legend >选择文件</legend>
		<div style="padding:10px;">
		
			<form id="form1" name="uploadFile" action="<%=path%>/enforce/base/uploadProcess.do" method="post" enctype ="multipart/form-data">
		
			<table border="0" cellpadding="1" cellspacing="2" style="width:100%;table-layout:fixed;">
			<tr>
				<td style="width:40px">文件：</td>
				<td style="width:90%"><span id="fileSave"><input type="file" onchange="checking()" name="file" id="importinput" value="浏览..." style="width:100%"/></span></td>
			</tr>
			</table>
			
			</form>
			<div style="text-align:center;padding:20px;"> 
				<a id="savaPer" class="mini-button mini-button-warning sacFilter" onclick="onSave()">上传</a>
				<a id="cancelPer" class="mini-button mini-button-info sacFilter" onclick="onCancel()">返回</a>
			</div>
		</div>
	</fieldset>
	</div>
</body>
<SCRIPT type="text/javascript">
	//默认加载函数
	mini.parse();
	var sysMessage='${sysMessage}';
	
	var w = top["${param.win}"];
	function onSave(){
		if($("#importinput").val()==""){
			mini.alert("请选上传的文件","提示");
			return false;
		}else {
			var msgid = mini.loading("操作中，请稍后......");
			$("#form1").submit();
		}
	}
	
	function checking(){
		var file = $('#importinput')[0];
            if (file) {
                var fileSize = 0;
                if (file.size > 1024 * 1024) fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
                else fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
               // console.log(file.name, fileSize, file.type);
              //限制文件的大小，最大30MB
               if((Math.round(file.size * 100 / (1024 * 1024)) / 100)>=30){
              		clearFile();
              		mini.alert("文件过大无法上传","提示");
               		return;
               }
              }
            }   
            
            function clearFile() {
            	var myfile = document.getElementById('importinput');
            	myfile.outerHTML="<input type=\'file\' id=\'importinput\' onchange=\'checking()\' name=\'file\' value=\'浏览...\' style=\'width:100%\'>";
            	}
            
            function onCancel(e) {
            	CloseWindow("cancel");            
            }


			//关闭窗口
			function CloseWindow(action) {
				if (action == "close" && form.isChanged()) {
					if (confirm("数据被修改了，是否先保存？")) {
						return false;
						}
					}
				if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
				else window.close();
				}
			
			if(sysMessage==0 && sysMessage!=''){
				mini.alert("上传失败","提示");
			} else if(sysMessage==1){
				mini.confirm("上传成功", "确定关闭页面?", function (action) {
					if (action == "ok") {
						return window.CloseOwnerWindow(action);
					}else{
						return;
					}
				});
			}else if(sysMessage==2){
				mini.alert("系统错误请联系管理员","提示");
			}else if(sysMessage==3){
				mini.alert("不支持此文件格式上传，请重新选择","提示");
			}
			</SCRIPT>
		</html>