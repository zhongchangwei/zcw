
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>用户管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
    <script src="<%=path%>/res/miniui/swfupload/swfupload.js" type="text/javascript"></script> 
    <script src="<%=path%>/res/miniui/scripts/boot.js" type="text/javascript"></script>
    <link href="<%=path%>/res/css/global.css" rel="stylesheet" type="text/css" ></link>
    <link href="<%=path%>/res/css/index.css" rel="stylesheet" type="text/css" ></link>
    <link href="<%=path%>/res/miniui/scripts/miniui/themes/bootstrap/skin.css" rel="stylesheet" type="text/css"/>
  </head>
<body class="body1">
	<div class="global2">
		<div class="datagridtitle">用户管理</div>
		<div class="mini-toolbar">
		账户：<input id="rName" class="mini-textbox" maxlength="20" style="width:90px" />
		<a class="mini-button mini-button-primary" onclick="search()">查询</a>
		<a id="add" class="mini-button mini-button-success" onclick="add">新增</a>
		<a id="edit" class="mini-button mini-button-info" onclick="edit">修改</a>
		<a id="edit" class="mini-button mini-button-info" onclick="del">删除</a>
		</div>
	</div>
	<div class="mini-fit">
	<div id="grid1" class="mini-datagrid" allowSortColumn="false" enableGroupOrder="false"  style="height:100%;width:100%" url="<%=path %>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="50">
	<div property="columns">
		<div type="checkcolumn" headerAlign="center" width="20"></div>
		<div type="indexcolumn" headerAlign="center" width="20">序号</div>
		<div field="REAL_NAME" headerAlign="center" width="90">真实姓名</div>
		<div field="USER_ID" headerAlign="center" width="40" visible="true">账号</div>
		<div field="PASSWORD" headerAlign="center" width="70">密码</div>
		<div field="DEPT_ID" headerAlign="center" width="40">部门</div>
		<div field="ROLE_ID" headerAlign="center" width="100">角色</div>
		<div field="AGE" headerAlign="center" width="90">性别</div>
		<div field="PHONE" headerAlign="center" width="90">电话</div>
		<div field="ADDRESSES" headerAlign="center" width="90">住址</div>
		<div field="REMARK" headerAlign="center" width="65">备注</div>
		<div field="CREATE_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="75px" >创建时间</div>
		<div field="CREATE_USERID" headerAlign="center" width="75">创建人</div>
		<div field="MODIFY_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="70px" >修改时间</div>
		<div field="MODIFY_USERID" headerAlign="center" width="40">修改人</div>
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
	
	
	 //提交
	 function commitSubs(){
		 var rows = grid.getSelecteds();
			if (rows.length <= 0) {
				mini.alert("请选中一条记录");
				return;
			}
			var ids = [];
			for (var i = 0, l = rows.length; i < l; i++) {
				var pk = rows[i].PK;
				
				if(pk==null || pk==''){//新增的，还没保存的行
					grid.removeRow(rows[i], false);//从列表上去掉已删的行
				} else {
					ids.push(rows[i].CASEID);
					
					if (rows[i].STATE =="已提交") {
						mini.alert("温馨提示：该案件已提交，不需重复提交","提示");
						return;
					}
				}
			}
			if(ids.length <= 0 ){
				return;//不需要提交后台
			}
			var id = ids.join(',');
			
			mini.confirm("是否确定要提交？", "确定？", 
				function (action) {
					if (action == "ok") {
						var msgid = mini.loading("操作中，请稍后......");
						$.ajax({
							url: "<%=path%>/enforce/receive/receiveCommit.do?ids=" +id,
							dataType: 'json',
							success: function (flag) {
								if(flag=="1") {//提交成功
									grid.removeRows(rows, false);
									mini.alert("提交成功！", "提示");
									grid.reload();
								} else {
									mini.alert("提交不成功！", "提示");
								}
								mini.hideMessageBox(msgid);
							},
							error: function () {
								mini.hideMessageBox(msgid);
								mini.alert("提交数据时发生错误，请与管理员联系！", "提示");
							}
						});
					}
				});
	 }
	 
	 
	
	 
	 
	 
	 
	 
	 //弹出新增窗口
    function add(){
    	var rows = grid.data;
    	mini.open({
    		url:"<%=path%>/userController/openDetail.do",
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
    		url:"<%=path%>/userController/openDetail.do?id="+rows[0].PK,
    		title:"用户修改",
    		showMaxButton:true,
    		height:618,width:1000,
    		ondestroy:function(action) {
       			grid.reload();
       		}
    	});
    }
    
    
 // 删除
	function del() {
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
							}
							
					}
					if(ids.length > 0) {
						var id = ids.join(',');
						$.ajax({
							url: "<%=path%>/userController/delete.do?ids=" + id,
							dataType: 'json',
							success: function (flag) {
								if (flag =="1") {
									mini.alert("删除成功！", "提示");
									grid.reload();
								}else if(flag == "2"){
									mini.alert("无法删除，此案件有函件！", "提示");
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
    
    
   
    
    
    
    
    
    
    
    
    
    
</script>
  </body>
</html>