<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>Field</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
   <%@include file="/common/common.jsp" %>
   
   <style type="text/css">
    	table .name{
    		width:100px;
    		text-align: right;
    		font:12;
    	}
    	
    </style>
  </head>
<body class="body1" style="overflow: auto;">
	<div class="mini-panel" showHeader="false" style="border: red solid 1px;height: 95%;px;width: 100%;">
	
	<div id="form1">
	 <input id="pkId" name="PK"  class="mini-textbox" visible="false"/> 
	 
	<fieldset style="margin-top:5px;">
		<legend style="font-weight:bold">数据库表名称</legend>
		<table>
			<tr>
				<td class="name"><label><span style="color:red;">*</span>主表：</label></td>
				<td><input id="tablename_id" name="TABLENAME" required="true" onvaluechanged="loadGridField()" class="mini-combobox" style="width:150px;" textField="NAME" valueField="PK" emptyText="数据库表名称..."
   				 	url="<%=path%>/genertaFieldController/getUserTable.do"   multiSelect="false" allowInput="true" showNullItem="false" nullItemText="无结果..."/>         
				</td>
				<td class="name"><label><span style="color:red;">*</span>类名称：</label></td>
				<td><input id="field" name="FIELD"   vtype="maxLength:30"  class="mini-textbox" required="true"/></td>
				<td class="name"><label><span style="color:red;">*</span>页面标题：</label></td>
				<td><input id="tname" name="TNAME" required="true"  vtype="maxLength:30"    class="mini-textbox"/></td>
				<td class="name"><label><span style="color:red;">*</span>关联表：</label></td>
				<td><input id="pk_tablename" name="PK_TABLENAME"  class="mini-combobox" style="width:150px;" textField="NAME" valueField="PK" emptyText="从表名称..."
   				 		url="<%=path%>/common/getUserTable.do"    multiSelect="true" allowInput="true" showNullItem="false" nullItemText="无结果..."/>         
				</td>
			</tr>
			<tr>
				<td class="name"><label>输出路径：</label></td>
				<td><input id="outputpath" name="OUTPUTPATH"  vtype="maxLength:100" onclick="loll()"   class="mini-textbox"/></td>
				
				<td class="name"><label>是否禁用：</label></td>
				<td><input id="isblank" name="ISBLANK" required="true" value="no" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'no',name:'可生成'},{pk:'yes',name:'禁用生成'}]" allowInput="false" 
					showNullItem="false"	  />
				</td>
				
				<td class="name"><label><span style="color:red;">*</span>子包路径：</label></td>
				<td><input id="packagename" name="PACKAGENAME" required="true"  vtype="maxLength:30"  class="mini-textbox"/></td>
				<input type="file"/>
			</tr>
			
		</table>
	</fieldset>
	</div>
		
	<fieldset style="margin-top:5px;">
		<legend style="font-weight:bold">字段详细</legend>
	<div id="hide1" class="mini-toolbar" style="margin-top:2px;">
			<a id="add" class="mini-button mini-button-success" onclick="add()">新增</a>
			<a id="add" class="mini-button mini-button-success" onclick="upload()">上传</a>
			<a id="remove" class="mini-button mini-button-info" onclick="remove()">删除</a>
			
			<!-- <a id="remove" class="mini-button mini-button-primary" onclick="genderateLabel('3')">生成form表单</a>
			<a id="remove" class="mini-button mini-button-primary" onclick="genderateLabel('2')">生成list查看</a>
			 -->
			
	</div>
	<div id="grid1" class="mini-datagrid" style="height:350px;width:99%" url="<%=path%>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="30">
		<div property="columns">
		<div type="checkcolumn" headerAlign="center" width="10"></div>
		<div type="indexcolumn" headerAlign="center" width="10">序号</div>
		<div field="TABLENAME" headerAlign="center"  vtype="required" width="50" align="center" visible="false">表名称
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
		<div field="FIELD" headerAlign="center"  vtype="required" width="50" align="center">字段
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
		<div field="TNAME" headerAlign="center"  vtype="required" width="50" align="center">标题名称
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
		<div field="WIDTH" headerAlign="center"  vtype="required" width="30" align="center">宽(可百分比)
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
			
			
			
		<div field="DATEFORMAT" headerAlign="center"  type="comboboxcolumn" width="45" align="center">日期格式
			<input property="editor" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'yyyy-MM-dd HH:mm:ss',name:'yyyy-MM-dd HH:mm:ss'},{pk:'yyyy-MM-dd',name:'yyyy-MM-dd'}]" allowInput="false" 
				showNullItem="true"	  /></div>
		<div field="DATATYPE" headerAlign="center" vtype="required" type="comboboxcolumn" width="40" align="center">数据类型(生成标签)
			<input property="editor" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'optiondata',name:'下拉(data)'},{pk:'optionurl',name:'下拉(url)'},{pk:'text',name:'文本(text)'},{pk:'date',name:'时间(date)'},{pk:'int',name:'数字(int)'}]" allowInput="false" 
				showNullItem="false"	  /></div>
		<div field="ISHIDDEN" headerAlign="center" vtype="required" type="comboboxcolumn" width="20" align="center">是否隐藏
			<input property="editor" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'0',name:'隐藏'},{pk:'1',name:'显示'}]" allowInput="false" 
				showNullItem="false"	  /></div>
		<div field="ISBLANK" headerAlign="center" vtype="required" type="comboboxcolumn" width="20" align="center">是否必填
			<input property="editor" class="mini-combobox" valueField="pk" textField="name" data="[{pk:'no',name:'必填'},{pk:'yes',name:'可空'}]" allowInput="false" 
				showNullItem="false"	  /></div>
				
		
		<div field="SORT" headerAlign="center"  vtype="required" width="15" align="center">排序
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
		<div field="REMARK" headerAlign="center"   width="20" align="center">备注
			<input property="editor" class="mini-textbox"  maxlength="30"/></div>
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
	
	 //当输入表名，加载该表的所有字段
   function loadGridField(){
	 var tableName = mini.get("tablename_id").getValue().toUpperCase();
		// alert(tableName);
		 //Table_Name='#tableName'
	   	var where = " and f.Table_Name = '"+tableName+"' ";
	   	var replace = {"##replace##":where};
	   	grid.load({data:'${addShowGrid1Data}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
   		//排序
  	    grid.sortBy("sort","asc");//状态
   }
	//暂时用不到
   function onCloseClick(e) {
       var obj = e.sender;
       obj.setText("");
       obj.setValue("");
   }
    
    //修改时回显数据
    var formData='${dataShow}';
    if(formData!=''){
    	var data = mini.decode(decodeURIComponent(formData));
    	/////////////////////////
    	//$("#tablename_id").val('${tableName}');
    	//form.setEnabled(false);
    	//$("#tablename_id").attr("readonly",false);    //添加只读属性 
    	var tableName = mini.get("tablename_id").setEnabled(false);
    	//$("#tablename_id").removeAttr("readonly");    //去除只读属性///////
    	//加载表单数据
    	form.setData(data);
    	var where = " and t.tablename = '${tableName}'";
    	//alert(where);
    	var replace = {"##replace##":where};
    	grid.load({data:'${grid1ShowData}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
    	//排序
   	    grid.sortBy("sort","asc");//状态
        
    }else{
    	
    }
    
    var sort = "${maxSort}";
  //新增
    function add(){
		sort++;//每次新增加1
		var row = {
				TABLENAME:'${tableName}',
				//DATEFORMAT:'yyyy-MM-dd',
				WIDTH:'50px',
				ISBLANK:'yes',
				DATATYPE:'text',
				ISHIDDEN:'1',
				SORT:sort,
				
		};
		grid.addRow(row,0);
	}
    
    
    
 	// 保存提交调用方法
	function onSaveSubmit() {
		var formdata = form.getData();//获取表单数据
		grid.validate();//验证
		form.validate();//验证
		if(!form.isValid()){
			mini.alert("数据校验不通过,请填写列表中标红色的格子！", "提示");
			return;
		} 
		
		if(grid.isValid()){
			var addRows = grid.getChanges('added', false);//获得新增行
			var modifyRows = grid.getChanges('modified', false);//获得修改行	
			if(formData==''){//如果是新增
				addRows = grid.getData();
				modifyRows=new Array();
			}
			var msgid = mini.loading("操作中，请稍后......","提示");
			$.ajax({
					type: 'POST',
					url: '${Path}genertaFieldController/commitSave.do',
					dataType: 'json',
					data: {
							'addRows':encodeURI(mini.encode(addRows)),
							'modifyRows':encodeURI(mini.encode(modifyRows)),
							'forms':encodeURI(mini.encode(formdata))
						  },
					success: function(data) {
						//alert(data.flag);
						if(data!=""){
							//alert(data.tableName);
							mini.alert("保存成功！"+data.flag, "提示",function(){
								location.href ="<%=path%>/genertaFieldController/openDetail.do?param="+data.tableName+"&id="+data.id;
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
	
 	
 	
	// 删除
	function remove() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除选中记录？", "确定？", 
			function (action) {
				if (action == "ok") {
					var ids = [];
					var tbn = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined"||rows[i].PK==''||rows[i].PK==null) {
							grid.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
							tbn.push(rows[i].TABLENAME);
						}
					}
					if(ids.length > 0) {
						var id = ids.join("','");
						var tbName = tbn.join("','");
						$.ajax({
							url: "<%=path%>/genertaFieldController/remove.do?ids=" + id+"&tableName="+tbName,
							dataType: 'json',
							success: function (flag) {
									mini.alert(flag.flag, "提示");
									grid.reload();
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
    function loll() {
    	$.ajax({
			type: 'POST',
			url: '${Path}genertaFieldController/loadPaths.do',
			success: function(data) {
				alert(data);
			}
    	});
    }
    
	 //上传
	function upload() {
	 var row=grid.getSelected()
		mini.open({
    		url:'<%=path%>/common/uploadPage.do?filePath=upload/enforceFile/checkAttachment/',
    		width:460,height:230,
    		title:'选择文件',
    		ondestroy:function(action) {
    			 if (action == "ok") {
    				var iframe = this.getIFrameEl();
                    var data = iframe.contentWindow.GetData();
                    data = mini.clone(data); //必须
                    var newRow = {ATTACHMENT_NAME:data.attachmentName, ATTACHMENT_PATH:data.filePath, FILE_NAME:data.fileName};
            		grid.updateRow(row,newRow);
    			} 
    		}
    	});
    }
  
		
	
    
</script>
  </body>
</html>