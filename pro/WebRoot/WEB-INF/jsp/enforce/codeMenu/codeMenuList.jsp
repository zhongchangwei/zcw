<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>部门管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
    <!-- 公用样式含miniUI -->
 	<%@include file="/common/common.jsp" %>
 </head>
<body class="body1">
	<div class="global2">
		<div class="datagridtitle">菜单管理</div>
		<div class="mini-toolbar">
		账户：<input id="rName" class="mini-textbox" maxlength="20" style="width:90px" />
		<a class="mini-button mini-button-primary" onclick="search()">查询</a>
		<a id="add" class="mini-button mini-button-success" onclick="add">新增</a>
		<a id="edit" class="mini-button mini-button-info" onclick="edit">修改</a>
		</div>
	</div>
	<div class="mini-fit">
	<div id="grid1" class="mini-datagrid" allowSortColumn="false" enableGroupOrder="false"  style="height:100%;width:100%" url="<%=path %>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="50">
	<div property="columns">
		<div type="checkcolumn" headerAlign="center" width="20"></div>
		<div type="indexcolumn" headerAlign="center" width="20">序号</div>
		<div field="PARENTID" headerAlign="center" width="90">父ID</div>
		<div field="PARNAME" headerAlign="center" width="65">父节点名称</div>
		<div field="CODENAME" headerAlign="center" width="65">名称</div>
		<div field="URL_" headerAlign="center" width="65">菜单URL</div>
		<div field="PROPERTY" headerAlign="center" width="65">代码属性(类型、代码)</div>
		<!-- <div field="TYPE" headerAlign="center"  width="65">类型名称-用于开发调用</div> -->
		<div field="SORT" headerAlign="center" width="65">排序</div>
		<div field="CREATE_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="70px" >创建时间</div>
		<div field="CREATE_USER_NAME" headerAlign="center" width="70">创建人</div>
		 <div field="MODIFY_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="70px" >修改时间</div>
		<div field="MODIFY_USER" headerAlign="center" width="40">修改人</div> 
	 </div>
	</div> 
	</div>
<script type="text/javascript">

    mini.parse();
    var grid = mini.get("grid1");
	 var replace = {"##replace##":""};
	 grid.load({data:'${data}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
    
    //grid.clearSort ( );
	 //查询
	function search(){
		var rname= mini.get("rName").getValue();	
		 var tem="";
		 if(rname!=""&&rname!=null){
			 tem+=" and u.user_name like '%"+rname+"%'";
		 }
		 var replace = {"##replace##":tem};
		 grid.load({data:'${data}',replace:encodeURI(mini.encode(replace))});//加载datagrid数据
	 }
	
	
	
	
	 
	 
	 
	 
	 
	 //弹出新增窗口
	    function add(){
	    	var rows = grid.data;
	    	mini.open({
	    		url:"<%=path%>/codeMenuController/openDetail.do",
	    		title:"用户新增",
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
	    		url:"<%=path%>/codeMenuController/openDetail.do?id="+rows[0].PK,
	    		title:"用户修改",
	    		showMaxButton:true,
	    		height:618,width:1000,
	    		ondestroy:function(action) {
	       			grid.reload();
	       		}
	    	});
	    }
	    
    
    
 // 删除
	function onRemove() {
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			mini.confirm("确定删除选中记录？", "确定？", 
			function (action) {
				if (action == "ok") {
					var ids = [];
					var caseids = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined") {
							grid.removeRow(rows[i], false);//从列表上去掉已删的行
						} else {
							ids.push(rows[i].PK);
							caseids.push(rows[i].CASEID);
							
							if (rows[i].STATE =="已提交") {
								mini.alert("案件已提交！不得删除");
								return;
							
							}
							
						}
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						var caseids = caseids.join(',');
						$.ajax({
							url: "<%=path%>/enforce/receive/receiveManageDelete.do?ids=" + id+"&caseids="+caseids,
							dataType: 'json',
							success: function (flag) {
								if (flag =="1") {
									mini.alert("删除成功！", "提示");
									grid.reload();
									//grid.removeRows(rows, false); //从列表上去掉已删的行
								}else if(flag == "2"){
									mini.alert("无法删除，此案件有函件！", "提示");
								}								else {
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
		} else {
			mini.alert("请选中一条或多条记录","提示");
		}
	}
    
    
    
</script>
  </body>
</html>