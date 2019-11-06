
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>生成管理</title>
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
		<div class="datagridtitle">生成管理</div>
		<div class="mini-toolbar">
		账户：<input id="rName" class="mini-textbox" maxlength="20" style="width:90px" />
		<a class="mini-button mini-button-primary" onclick="search()">查询</a>
		<a id="add" class="mini-button mini-button-success" onclick="add">新增</a>
		<a id="edit" class="mini-button mini-button-info" onclick="edit">修改</a>
		<a id="remove" class="mini-button mini-button-info" onclick="remove">删除</a>
		<a id="genderateLabel" class="mini-button mini-button-primary" onclick="genderateLabel('1')">生成一套</a>
		</div>
	</div>
	<div class="mini-fit">
	<div id="grid1" class="mini-datagrid" allowSortColumn="false" enableGroupOrder="false"  style="height:100%;width:100%" url="<%=path %>/loadMiniUIData/load.do" idField="PK" multiSelect="true" allowResize="true" allowCellEdit="true"  allowCellSelect="true" pageSize="50">
	<div property="columns">
		<div type="checkcolumn" headerAlign="center" width="10"></div>
		<div type="indexcolumn" headerAlign="center" width="10">序号</div>
		<div field="TABLENAME" headerAlign="center" width="50">主表</div>
		<div field="PK_TABLENAME" headerAlign="center" width="60">从表</div>
		<div field="FIELD" headerAlign="center" width="50">类名</div>
		<div field="BEANNAME" headerAlign="center" width="40">实体名</div>
		<div field="TNAME" headerAlign="center" width="30">标题</div>
		<div field="PACKAGENAME" headerAlign="center" width="50">二级路径</div>
		<div field="REMARK" headerAlign="center" width="65" visible="false">备注</div>
		<div field="CREATE_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="75px" >创建时间</div>
		<div field="CREATE_USERID" headerAlign="center" width="75" visible="false">创建人</div>
		<div field="MODIFY_DATE" headerAlign="center" allowSort="true" dateFormat="yyyy-MM-dd HH:mm:ss" width="70px" >修改时间</div>
		<div field="MODIFY_USERID" headerAlign="center" width="40" visible="false">修改人</div>
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
			 tem+=" and t.tname like '%"+rname+"%'";
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
    		url:"<%=path%>/genertaFieldController/openDetail.do",
    		title:"新增",
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
    		url:"<%=path%>/genertaFieldController/openDetail.do?id="+rows[0].PK+"&param="+rows[0].TABLENAME,
    		title:"修改",
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
					var tbn = [];
					for (var i = 0, l = rows.length; i < l; i++) {
						if (typeof(rows[i].PK) == "undefined") {
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
							url: "<%=path%>/genertaFieldController/remove.do?type=all&ids=" + id+"&tableName="+tbName,
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
    
    
   
	function genderateLabel(type){
		var rows = grid.getSelecteds();
		 if (rows.length !=1) {
			mini.alert("请选中一条记录");
			return;
		} 
		 $.ajax({
    		url:"<%=path%>/genertaFieldController/generateLabel.do?tableName="+rows[0].TABLENAME+"&id="+rows[0].PK+"&type="+type,
    		dataType: 'json',
    		success: function (flag) {
    			if(typeof(flag.fail) != "undefined"){
					mini.alert(flag.fail,"提示");
    			}else if(typeof(flag.result) != "undefined"){
					alert(flag.result,"提示");
    			}else{
					mini.alert("失败："+flag,"提示");
    			}
			},
			error: function () {
				mini.alert("生成数据时发生错误，请与管理员联系！", "提示");
			}
    	});
	}
    
    
    
    
    
    
    
    
    
</script>
  </body>
</html>