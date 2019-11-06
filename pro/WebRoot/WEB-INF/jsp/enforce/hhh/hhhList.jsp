<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <!-- 公用样式含miniUI -->
 	<%@include file="/common/common.jsp" %>
  <title>测试管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
  </head>
<body class="body1">
	<div class="global2">
		<div class="datagridtitle">测试管理</div>
		<div class="mini-toolbar">
		账户：<input id="rName" class="mini-textbox" maxlength="20" style="width:90px" />
		<a class="mini-button mini-button-primary" onclick="search()">查询</a>
		<a id="add" class="mini-button mini-button-success" onclick="add()">新增</a>
		<a id="edit" class="mini-button mini-button-info" onclick="edit()">修改</a>
		<a id="remove" class="mini-button mini-button-info" onclick="remove()">删除</a>
		<a id="export" class="mini-button mini-button-info" onclick="ExportDate()">导出</a>
		<a id="import" class="mini-button mini-button-info" onclick="ImportDate()">导入</a>
		</div>
	</div>
	<div class="mini-fit">
	<div id="grid" class="mini-datagrid" allowSortColumn="false" enableGroupOrder="false"  style="height:100%;width:100%" url="<%=path %>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="50">
	<div property="columns">
		<div field="PID" headerAlign="center" width="50px"  visible="true" >上级部门</div>
        <div field="DEPT_NAME" headerAlign="center" width="50px"  visible="true" >部门名称</div>
        <div field="REMARK" headerAlign="center" width="50px"  visible="true" >备注</div>
        <div field="CREATE_DATE" headerAlign="center" width="50px"  visible="false" >创建时间</div>
        <div field="CREATE_USERID" headerAlign="center" width="50px"  visible="true" >创建人</div>
        <div field="MODIFY_DATE" headerAlign="center" width="50px"  visible="false" >修改时间</div>
        <div field="MODIFY_USERID" headerAlign="center" width="50px"  visible="false" >修改人</div>
        <div field="1" headerAlign="center" width="50px"  visible="true" >1</div>
        
	 </div>
	</div> 
	</div>
<script type="text/javascript">

    mini.parse();
    var grid = mini.get("grid");
	 var replace = {"##replace##":""};
	 grid.load({data:'${data}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
    
	 //查询
	function search(){
		var rname= mini.get("rName").getValue();	
		 var tem="";
		 if(rname!=""&&rname!=null){
			 tem+=" and u.user_id like '%"+rname+"%'";
		 }
		 var replace = {"##replace##":tem};
		 grid.load({data:'${data}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
	 }
	
	
	 //弹出新增窗口
    function add(){
    	var rows = grid.data;
    	mini.open({
    		url:"<%=path%>/hhhController/openDetail.do",
    		title:"测试管理新增页面",
    		showMaxButton:true,
    		height:618,width:1000,
    		ondestroy:function(action) {
   			grid.reload();
   		}
    	});
    }
  //弹出修改窗口
    function edit(){
    	var rows = grid.getSelecteds();
		 if (rows.length !=1) {
			mini.alert("请选中一条记录");
			return;
		} 
    	mini.open({
    		url:"<%=path%>/hhhController/openDetail.do?id="+rows[0].PK,
    		title:"测试管理修改页面",
    		showMaxButton:true,
    		height:618,width:1000,
    		ondestroy:function(action) {
       			grid.reload();
       		}
    	});
    }
    
    
 	// 删除
	function remove() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除选中记录？", "确定？", 
			function (action) {
				if (action == "ok") {
					var ids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined") {
							grid.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
							}
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						$.ajax({
							url: "<%=path%>/hhhController/delete.do?ids=" + id,
							dataType: 'json',
							success: function (data) {
								if (data =="success") {
									mini.alert("删除成功！", "提示");
									grid.reload();
								}else {
									mini.alert("删除失败！", "提示");
								}
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
		} else {mini.alert("请选中一条或多条记录","提示");}
	}
    
    
   // 导出
	function ExportDate() {}
   // 导入
	function ImportDate() {}
    
    
    
    
    
    
    
    
    
    
</script>
  </body>
</html>