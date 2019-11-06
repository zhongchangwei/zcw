<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <!-- 公用样式含miniUI -->
 	<%@include file="/common/common.jsp" %>
  <title>角色管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
  </head>
<body class="body1">
	<div class="global2">
		<div class="datagridtitle">角色管理</div>
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
		<div type="checkcolumn" headerAlign="center" width="10"></div>
		<div type="indexcolumn" headerAlign="center" width="10">序号</div>
		<div field="ID" headerAlign="center" width="50px"  visible="false" >主键id</div>
        <div field="ROLE_NAME" headerAlign="center" width="50px"  visible="true" >角色名</div>
        <div field="DESCRIPTION" headerAlign="center" width="50px"  visible="true" >角色描述</div>
        <div field="CREATE_DATE" headerAlign="center" width="50px"  allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss"  visible="true" >创建时间</div>
        <div field="CREATE_USERID" headerAlign="center" width="50px"  visible="true" >创建人id</div>
        <div field="CREATE_USER" headerAlign="center" width="50px"  visible="true" >创建人名称</div>
        <div field="MODIFY_DATE" headerAlign="center" width="50px"  allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss"  visible="true" >修改时间</div>
        <div field="MODIFY_USERID" headerAlign="center" width="50px"  visible="true" >修改人id</div>
        <div field="STATE" headerAlign="center" width="50px"  visible="false" >状态0：删除、1：正常</div>
        
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
    	mini.open({
    		url:"<%=path%>/roleController/openDetail.do",
    		title:"角色管理新增页面",
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
    		url:"<%=path%>/roleController/openDetail.do?id="+rows[0].PK,
    		title:"角色管理修改页面",
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
						if (typeof(rows[i].PK) == "undefined"||rows[i].PK==''||rows[i].PK==null) {
							grid.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
							}
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						$.ajax({
							url: "<%=path%>/roleController/remove.do?ids=" + id,
							dataType: 'json',
							success: function (data) {
								if (data.flag !="") {
									mini.alert(data.flag, "提示");
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