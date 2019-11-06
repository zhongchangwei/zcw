<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>案件接收</title>
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
  		<legend class="font_bold">用户信息</legend>
	<input id="pk" class="mini-hidden"/>
	<table>
	<tr>
		<td class="name"><label><span style="color:red;">*</span>菜单名称：</label></td>
		<td><input id="name_" name="NAME_"  vtype="maxLength:10"  required="true"   class="mini-textbox"/></td>
		<td class="name"><label><span style="color:red;">*</span>代码属性：</label></td>
		<td><input id="property" name="PROPERTY" onvalidation="onIDCardsValidation"  required="true"  maxlength="20"  class="mini-textbox"/></td>
		<td class="name"><label><span style="color:red;">*</span>类型：</label></td>
		<td><input id="type" name="TYPE"   vtype="maxLength:20"  required="true"  maxlength="25"   class="mini-textbox"/></td>
		
	</tr>
	<tr>
		<td class="name"><label><span style="color:red;">*</span>排序：</label></td>
		<td><input id="sort" name="SORT"   vtype="maxLength:20"  required="true"  maxlength="25"   class="mini-textbox"/></td>
		
		<td class="name"><label><span style="color:red;">*</span>父类：</label></td>
		<td><input id="parentid" name="PARENTID"   vtype="maxLength:32"  required="true"  maxlength="32"   class="mini-textbox"/></td>
		
		<td  class="name"><label><span style="color:red;">*</span>Action路径：</label></td>
		<td><input id="url_" name="URL_"   vtype="maxLength:100"  required="true"  maxlength="100" width="350px"  class="mini-textbox"/></td>
		
		
	
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
	
	
   
	//获取单位信息
	function unitInfo(){
		 var row=grid.getSelected()
			mini.open({
				url:'<%=path%>/enforce/common/unitInfoGaint.do',
	    		width:900,height:500,
	    		title:'被申告单位信息',
	    		ondestroy:function(action) {
	    			 if (action == "ok") {
	    				 var iframe = this.getIFrameEl();
	                    var data = iframe.contentWindow.GetData();
	                    data = mini.clone(data); //必须
	                    var frdata = mini.decode(data);
	                	mini.get('organization1').setText(frdata.ORGANIZATION);
	                	mini.get('organizationame').setValue(frdata.ORGANIZATION);
	                	mini.get('organization1').setValue(frdata.ORGANIZATION);
	                	form2.setData(frdata); 
	                	form2.validate();
	                 
	    			} 
	    		}
	    	});
	    }
    
    //修改时回显数据
    var formData='${dataShow}';
    if(formData!=''){
    	var data = mini.decode(decodeURIComponent(formData));
    	//加载表单数据
    	form.setData(data);
    }
    
   
    
    
 	// 保存提交调用方法
	function onSaveSubmit() {
		var formdata = form.getData();//获取表单数据
		//form.validate();//验证
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
					url: '${Path}codeMenuController/commitSave.do',
					dataType: 'json',
					data: {
							//'addeRows':encodeURI(mini.encode(addRows)),
							//'modifyRows':encodeURI(mini.encode(modifyRows)),
							'forms':encodeURI(mini.encode(formdata))
						  },
					success: function(data) {
						alert(data);
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
	
 	
 	
	// 附件信息删除
	function deleteAtt() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除选中记录？", "确定？", 
			function (action) {
				if (action == "ok") {
					var ids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined"||rows[i].PK==''||rows[i].PK==null) {
							grid.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
						}
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						$.ajax({
							url: "<%=path%>/enforce/receive/receiveManageAttDelete.do?ids=" + id,
							dataType: 'json',
							success: function (flag) {
								if (flag != "0") {
									mini.alert("删除成功！", "提示");
									grid.reload();
									//grid.removeRows(rows, false); //从列表上去掉已删的行
								} else {
									mini.alert("删除失败！", "提示");
								}
								mini.hideMessageBox(msgid);
							},
							error: function () {
								mini.hideMessageBox(msgid);
								mini.alert("提交数据时发生错误，请与管理员联系！", "提示");
							}
						});
					}
				} else {
					return;
				}
			});
		} else {
			mini.alert("请选中一条或多条记录","提示");
		}
	}
    
    
	
  
		
	
    
</script>
  </body>
</html>