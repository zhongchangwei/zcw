<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>测试管理</title>
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
	<fieldset style="margin-top:5px;">
		<legend style="font-weight:bold">测试管理</legend>
		<table>
			 <tr>
                <td class="name"><label>上级部门：</label></td>
                <td><input id="pid" name="PID"  valueField="pk" textField="name"    url="<%=path%>/common/getCityKV.do" allowInput="false"    required="true"   class="mini-combobox" showNullItem="false"/></td>
                <td class="name"><label><span style="color:red;">*</span>部门名称：</label></td>
                <td><input id="dept_name" name="DEPT_NAME"  vtype="maxLength:30"  required="true"   class="mini-textbox"/></td>
                <td class="name"><label>备注：</label></td>
                <td><input id="remark" name="REMARK"  vtype="maxLength:30"  required="true"   class="mini-textbox"/></td>
                <td class="name"><label><span style="color:red;">*</span>创建时间：</label></td>
                <td><input id="create_date" name="CREATE_DATE" allowinput="false" required="true" class="mini-datepicker"    format="yyyy-MM-dd" showTime="true"  showClearButton="true"/></td>
           </tr>
            <tr>
                <td class="name"><label>创建人：</label></td>
                <td><input id="create_userid" name="CREATE_USERID"  vtype="maxLength:30"  required="true"   class="mini-textbox"/></td>
                <td class="name"><label>修改时间：</label></td>
                <td><input id="modify_date" name="MODIFY_DATE" allowinput="false" required="true" class="mini-datepicker"    format="yyyy-MM-dd" showTime="true"  showClearButton="true"/></td>
                <td class="name"><label>修改人：</label></td>
                <td><input id="modify_userid" name="MODIFY_USERID"  vtype="maxLength:30"  required="true"   class="mini-textbox"/></td>
                <td class="name"><label>1：</label></td>
                <td><input id="1" name="1"  vtype="maxLength:30"  required="true"   class="mini-textbox"/></td>
           </tr>

		</table>
	</fieldset>
	</div>
	<fieldset style="margin-top:5px;">
		<legend style="font-weight:bold">从表1</legend>
		<div id="hide1" class="mini-toolbar" style="margin-top:2px;">
			<a id="addId" class="mini-button mini-button-success" onclick="add">新增</a>
			<a id="editId" class="mini-button mini-button-primary" onclick="saveGrid">保存</a>
			<a id="removeId" class="mini-button mini-button-info" onclick="remove">删除</a>
		</div>
	<div id="grid1" class="mini-datagrid" style="height:350px;width:99%" url="<%=path%>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="20">
		<div property="columns">
			<div type="checkcolumn" headerAlign="center" width="10"></div>
			<div type="indexcolumn" headerAlign="center" width="10">序号</div>
				 
            <div field="USER_ID" headerAlign="center"  align="center" width="50px" vtype="required" visible="true"  type="comboboxcolumn" >
                                   账号<input property="editor" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'1',name:'男'},{pk:'0',name:'女'}]" allowInput="false" showNullItem="false" />
            </div> 
            <div field="PASSWORD" headerAlign="center"  align="center" width="50px" vtype="required" visible="true" >
                                   密码<input property="editor" class="mini-textbox"  maxlength="30"/>
            </div> 
            <div field="DEPT_ID" headerAlign="center"  align="center" width="50px" vtype="required" visible="true"  type="comboboxcolumn" >
                                   部门<input property="editor" class="mini-combobox" valueField="PK" textField="NAME" url="<%=path%>/common/getCityKV.do" allowInput="false" showNullItem="false" />
            </div> 
            <div field="ROLE_ID" headerAlign="center"  align="center" width="50px" vtype="required" visible="true"  type="comboboxcolumn" >
                                   角色<input property="editor" class="mini-combobox" valueField="PK" textField="NAME" url="<%=path%>/common/getCityKV.do" allowInput="false" showNullItem="false" />
            </div> 
            <div field="REAL_NAME" headerAlign="center"  align="center" width="50px" vtype="required" visible="true" >
                                   真实姓名<input property="editor" class="mini-textbox"  maxlength="30"/>
            </div>
		</div>
	</div>
	</fieldset>
	<fieldset style="margin-top:5px;">
		<legend style="font-weight:bold">从表2</legend>
		<div id="hide2" class="mini-toolbar" style="margin-top:2px;">
			<a id="add2Id" class="mini-button mini-button-success" onclick="add2">新增</a>
			<a id="edit2Id" class="mini-button mini-button-primary" onclick="saveGrid2">保存</a>
			<a id="remove2Id" class="mini-button mini-button-info" onclick="remove2">删除</a>
		</div>
	<div id="grid2" class="mini-datagrid" style="height:350px;width:99%" url="<%=path%>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="20">
		<div property="columns">
			<div type="checkcolumn" headerAlign="center" width="10"></div>
			<div type="indexcolumn" headerAlign="center" width="10">序号</div>
				 
            <div field="PARENTID" headerAlign="center"  align="center" width="50px" visible="true" >
                                   父节点<input property="editor" class="mini-textbox"  maxlength="30"/>
            </div> 
            <div field="NAME_" headerAlign="center"  align="center" width="50px" visible="true" >
                                   代码名称<input property="editor" class="mini-textbox"  maxlength="30"/>
            </div> 
            <div field="PROPERTY" headerAlign="center"  align="center" width="50px" visible="true" >
                                   代码类型<input property="editor" class="mini-textbox"  maxlength="30"/>
            </div>
		</div>
	</div>
	</fieldset>
	<div  style="margin:0 auto;text-align: center;margin-top:10px;margin-bottom:20px;">
		<a id="save" class="mini-button mini-button-success" onclick="onSaveSubmit">保存</a>
		<a  id="close" class="mini-button mini-button-danger" onclick="onClose">关闭</a>
	</div>
	</div>
	
	<iframe id="downloadfile" style="display: none"></iframe>
<script type="text/javascript">
	mini.parse();
	var form = new mini.Form("#form1");
	var grid = mini.get("grid1");
	var grid2 = mini.get("grid2");
    //修改时回显数据
    var formData='${dataShow}';
    if(formData!=''){
    	var data = mini.decode(decodeURIComponent(formData));
    	/////////////////////////
    	//加载表单数据
    	form.setData(data);
    	/*
    	//grid回显
    	var where = " and t.tablename = '${tableName}'";
    	//alert(where);
    	var replace = {"##replace##":where};
    	grid.load({data:'${grid1ShowData}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
    	//排序
   	    grid.sortBy("sort","asc");//状态 */
    	/*
    	//grid2回显
    	var where2 = " and t.tablename = '${tableName}'";
    	//alert(where2);
    	var replace2 = {"##replace##":where2};
    	grid2.load({data:'${grid1ShowData}',replace:encodeURI(mini.encode(replace2))});//加载datagrid2数据
    	//排序
   	    grid2.sortBy("sort","asc");//状态 */
        
    }
  	//新增grid
    function add(){
		var row = {};
		grid.addRow(row,0);
	}
	//单独保存grid1
	function saveGrid(){
		grid.validate();//验证
		if(grid.isValid()){
			var addRows = grid.getChanges('added', false);//获得新增行
			var modifyRows = grid.getChanges('modified', false);//获得修改行	
			var msgid = mini.loading("操作中，请稍后......","提示");
			$.ajax({
					type: 'POST',
					url: '<%=path%>/hhhController/commitSave.do',
					dataType: 'json',
					data: {
							'addRows':encodeURI(mini.encode(addRows)),
							'modifyRows':encodeURI(mini.encode(modifyRows)),
							'id':encodeURI(mini.encode(${id}))
						  },
					success: function(data) {
						if(data!=""){
							mini.alert("保存成功！"+data.flag, "提示");
							grid.reload();
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

  	//新增grid2
    function add(){
		var row = {};
		grid2.addRow(row,0);
	}
	//单独保存grid2
	function saveGrid2(){
		grid2.validate();//验证
		if(grid2.isValid()){
			var addRows = grid2.getChanges('added', false);//获得新增行
			var modifyRows = grid2.getChanges('modified', false);//获得修改行	
			var msgid = mini.loading("操作中，请稍后......","提示");
			$.ajax({
					type: 'POST',
					url: '<%=path%>/hhhController/commitSave.do',
					dataType: 'json',
					data: {
							'addRows2':encodeURI(mini.encode(addRows)),
							'modifyRows2':encodeURI(mini.encode(modifyRows)),
							'id':encodeURI(mini.encode(${id}))
						  },
					success: function(data) {
						if(data!=""){
							mini.alert("保存成功！"+data.flag, "提示");
							grid2.reload();
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

 	// 保存提交调用方法
	function onSaveSubmit() {
		var formdata = form.getData();//获取表单数据
		grid.validate();//验证
		grid2.validate();//验证
		if(!form.isValid()){
			mini.alert("数据校验不通过,请填写列表中标红色的格子！", "提示");
			return;
		} 	
		if(grid.isValid()&&grid2.isValid()){
			var addRows = grid.getChanges('added', false);//获得新增行
			var modifyRows = grid.getChanges('modified', false);//获得修改行
			var addRows2 = grid2.getChanges('added', false);//获得新增行
			var modifyRows2 = grid2.getChanges('modified', false);//获得修改行
			var msgid = mini.loading("操作中，请稍后......","提示");
			$.ajax({
					type: 'POST',
					url: '<%=path%>/hhhController/commitSave.do',
					dataType: 'json',
					data: {
							'addRows':encodeURI(mini.encode(addRows)),
							'modifyRows':encodeURI(mini.encode(modifyRows)),
							'addRows2':encodeURI(mini.encode(addRows2)),
							'modifyRows2':encodeURI(mini.encode(modifyRows2)),
							'forms':encodeURI(mini.encode(formdata))
						  },
					success: function(data) {
						if(data!=""){
							mini.alert("保存成功！"+data.flag, "提示",function(){
								location.href ="<%=path%>/hhhController/openDetail.do?id="+data.id;
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
 	
	// 删除grid
	function remove() {
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
							url: "<%=path%>/hhhController/deleteData.do?ids=" + id,
							dataType: 'json',
							success: function (flag) {
								if (flag != "0") {
									mini.alert("删除成功！", "提示");
									grid.reload();
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
    
 	
	// 删除grid2
	function remove() {
		var rows = grid2.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除选中记录？", "确定？", 
			function (action) {
				if (action == "ok") {
					var ids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined"||rows[i].PK==''||rows[i].PK==null) {
							grid2.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
						}
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						$.ajax({
							url: "<%=path%>/hhhController/deleteData.do?ids=" + id,
							dataType: 'json',
							success: function (flag) {
								if (flag != "0") {
									mini.alert("删除成功！", "提示");
									grid2.reload();
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