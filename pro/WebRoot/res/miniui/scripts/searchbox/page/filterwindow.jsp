<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<title></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<style type="text/css">
	html, body{
		margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
	}	
	</style>

	<script src="../../boot.js" type="text/javascript"></script>		
	<link href="../../miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
	<script src="../js/filtercontrol.js" type="text/javascript"></script>
	<link href="../js/filtercontrol.css" rel="stylesheet" type="text/css" />
</head>
<body>	
	<div class="mini-fit">
		<div id="outlookbar1" class="mini-outlookbar "  borderStyle="border-left:0;border-top:0;border-right:0;"
			style="width:100%;height:100%;" autoCollapse="true" activeIndex="0">			
			<div title="查询配置" iconCls="icon-search" >				
				<div id="search" style="padding:5px;"></div>
				<div id="filter" style="padding:5px;"></div>
			</div>
			<div title="查询方案"  iconCls="icon-search">
				<div id="fanganGrid" class="mini-datagrid" style="width:100%;height:auto;" borderStyle="border:0;"
					idField="id" allowSortColumn="false" showPager="false"  
					url="../js/list.txt" onrowdblclick="onRowDblClick">
					<div property="columns">
						<div type="indexcolumn" ></div>
						<div field="name" width="120" headerAlign="center" allowSort="true">方案名称</div>	
						<div field="user" width="120" headerAlign="center" allowSort="true">创建用户</div>							
						<div field="time" width="100"  align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">创建日期</div>				
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center;padding:10px;">
		<a class="mini-button" iconCls="icon-reload" onclick="onMyFangan()">我的方案</a>
		<a class="mini-button" iconCls="icon-reload" onclick="onAllClick()">全部</a>
		<a class="mini-button" iconCls="icon-save" onclick="onSave()">保存</a>
		<a class="mini-button" iconCls="icon-remove" onclick="onRemove()">删除</a>
		<a class="mini-button" iconCls="icon-search" onclick="onSearch()">查询</a>
		<a class="mini-button" iconCls="icon-cancel"onclick="onCancel()">取消</a>
		<br />
		<div>请点击“查询”按钮，得到最后生成的查询条件数组</div>
	</div>
</body>
</html>


<script>
	mini.parse();
	//这里配置要查询的字段
	var fields = null;
	var filter =null;
	var search=null;
	var replace = null;
	/**
	 * data:查询字段数据
	 */
	function SetData(data,replaces){
		fields = mini.clone(data);
		//高级查询组件
		 filter = new FilterControl();
		filter.render(document.getElementById("filter"));
		filter.setFields(fields);
		
		//快速查询组件
		search = new SearchControl();
		search.render(document.getElementById("search"));
		search.setFields(fields);
		
		replace = replaces;
	}

	var outlookbar = mini.get("outlookbar1");

	//查询方案表格
	var fanganGrid = mini.get("fanganGrid");
	fanganGrid.load();

	//点击查询方案自动添加条件至条件列表
	function onRowDblClick(e) {
		var record = e.record;
		filter.setData(record.data);
		search.setData(record.data);
		outlookbar.expandGroup(0);
	}
	////////////////////////////////////////////////////////////
	
	


	////////////////////////////////////////////////////////////
	function CloseWindow(action) {
		if (action == "close" && form.isChanged()) {
			if (confirm("数据被修改了，是否先保存？")) {
				return false;
			}
		}
		if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		else window.close();
	}

	function onSearch() {
		var data = filter.getData();
		var data2 = search.getData();
		data.addRange(data2);

		//保存前判断，如果有必填项，必须先录入
		if (!ValidFilter(data, fields)) {
			return;
		}
    	var json = mini.encode(data);
		var w = top["${param.win}"];
		
		var sqls = "";
		var sqlHidden = w.mini.get("dataId");
		if(sqlHidden!=null){
			sqls = sqlHidden.getValue();
		}
		var keys = "${param.key}";
		w.mini.get("${param.id}").load({filter:encodeURI(json),key:keys,data:sqls,replace:replace});
		//导出Excel的参赛
		var aa= w.mini.get("excelfilterId"); //家培用到
		if(aa!=null){
			aa.setValue(encodeURI(json));
		}
		var bb = w.mini.get("filter"); //晓洁用到
		if(bb!=null){
			bb.setValue(encodeURI(json));
		}
		//zjp  让高级查询框增加特定回调方法 begin  2014-09-24
		var isQueryCallback = "${param.isQueryCallback}";
		if(isQueryCallback == 1){
			w.queryCallback();  //可以回调父页面queryCallback()的方法
		}
		//zjp end  2014-09-24
		//家培修改：客户需要查询后关闭窗口
		 CloseWindow("cancel");
	}
	function onMyFangan() {
		outlookbar.expandGroup(1);
	}
	function onAllClick() {
		outlookbar.expandGroup(1);
	}

	function onSave() {
		alert("保存");
	}
	function onRemove() {
		alert("删除");
	}

	function onCancel() {
		CloseWindow("cancel");
	}

</script>