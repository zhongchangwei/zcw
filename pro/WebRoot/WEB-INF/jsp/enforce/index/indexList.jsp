<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <title>zcw权限系统</title>    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" ></meta>
    <!-- 公用样式含miniUI -->
 	<%@include file="/common/common.jsp" %>
 
	 <style type="text/css">
    body a:hover.mini-button
	{    
   	 	padding:0;
   	 	border:1px solid #A9ACB5;
    	background: gray url(enforce/res/images/button/hover.png) repeat-x 0 0;      
    	text-decoration:none;
	}
    </style>
  </head>
<body class="12"  style="overflow: auto;">
<div class="mini-layout" style="width:100%;height:100%;">
    <div title="" region="north" class="header" bodyStyle="overflow:hidden;" height="100" showHeader="true" showSplit="true">
    	<div style="position:absolute;left: 0px;margin-top: 0px"><img src="<%=path%>/res/images/top_01.jpg" /></div>
        <div style="position:absolute;top:23px;right:25px;">
        	<a class="mini-button" iconCls="top_right_1"  plain="true"><font color="white">${loginUserName}</font></a>
            <a class="mini-button" iconCls="top_right_2" onclick="topRight(1)" plain="true"><font color="white">关闭全部</font></a>
            <a class="mini-button" iconCls="top_right_3" onclick="topRight(2)" plain="true"><font color="white">帮助</font></a>    
            <a class="mini-button" iconCls="top_right_4" onclick="topRight(3)"  plain="true" ><font color="white">退出</font></a>        
        </div>
    </div>
<!--     <div showHeader="false" region="south" style="border:0;text-align:center;" height="25" showSplit="false"> -->
<!--     </div> -->
    <div region="west" title="系统操作菜单" showHeader="true" bodyStyle="padding-left:1px;" width="210" minWidth="100" maxWidth="350">
    	<div size="250" maxSize="220" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-tree" url="<%=path %>/loadMiniUIData/loadArray.do?data=${indexTreeData}" resultAsTree="false" onnodeclick="onNodeSelect"
                    textField="TEXT" idField="PK" parentField="PID" showTreeIcon="true" expandOnLoad="false">
                </div>
        </div>  
    </div>
    <div region="center" style="border:0;">
           <div showCollapseButton="false" style="border:0;width:100%;height:100%;">
               <!--Tabs-->
			    <div id="mainTabs" class="mini-tabs" activeIndex="0" refreshOnClick="true" style="width:100%;height:100%;" >
		        </div>        
	       </div> 
	</div>
</div>
<script type="text/javascript">
    mini.parse();
    //top["suppDateListTop"] = window;
    //一来就加载默认的tabs
    var tabs = mini.get("mainTabs");
    var id="tab$0";
    var tabInit = tabs.getTab(id);
    tabInit = {};
    tabInit._nodeid ="0";
    tabInit.name = id;
    tabInit.title = "欢迎";
    tabInit.showCloseButton = false;
    tabInit.url = "";
    tabInit.refreshOnClick=true;
    tabs.addTab(tabInit);
    tabs.activeTab(tabInit);
    var tree = mini.get("leftTree");
    //tree.expandAll();
    
    
       
    //打开标签页
    function showTab(node) {
         //var tabs = mini.get("mainTabs");
         var id ="tab$" + node.PK;
         var tab = tabs.getTab(id);
     	 //拼接标签
         if (!tab) {
           tab = {};
           tab._nodeid = node.PK;
           tab.name = id;
           tab.title = node.TEXT;
           tab.showCloseButton = true;
           tab.url = node.URL;
		   tabs.addTab(tab);
		}
		//tab.refreshOnClick=true;
		tabs.activeTab(tab);
	}
    
    //右上角的三个按钮
    function topRight(n){
        if(n == 1){
        	var tab = tabs.getTab("tab$0");
        	tabs.removeAll(tab);
        }
        else if(n == 2){
        	mini.alert("请联系系统管理员！", "提示");
        }
        else if(n == 3){
        	window.location.href="<%=path%>/logout";
        }
        
    }
    
	//选中节点
	function onNodeSelect(e) {
		var node = e.node;
		var isLeaf = e.isLeaf;
		if (isLeaf) {
			showTab(node);
		}
	}

	//标签窗口被改变
	function onTabsActiveChanged(e) {
		var tabs = e.sender;
		var tab = tabs.getActiveTab();
		if (tab && tab._nodeid) {
			var node = tree.getNode(tab._nodeid);
			if (node && !tree.isSelectedNode(node)) {
				tree.selectNode(node);
			}
		}
	}
</script>
  </body>
</html>