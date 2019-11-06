<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <!-- 公用样式含miniUI -->
 	<%@include file="/common/common.jsp" %>
  </head>
  
<body class="12"  style="overflow: auto;">
	<div class="mini-layout" style="width:100%;height:100%;">
			<div region="west" title="${title}" showHeader="true" bodyStyle="padding-left:1px;" width="210" minWidth="100" maxWidth="350">
				<div size="250" maxSize="220" minSize="100" showCollapseButton="true" style="border:0;">
		                <!--OutlookTree-->
		                <div id="leftTree" class="mini-tree" url="<%=path %>/loadMiniUIData/loadArray.do?data=${treeData}" resultAsTree="false" onnodeclick="onNodeSelect"
		                    textField="TEXT" idField="PK" parentField="PID" showTreeIcon="true" expandOnLoad="false">
		                </div>
		        </div>  
		    </div>
		    
		   
		    <div region="center" style="border:0;">
		           <div showCollapseButton="false" style="border:0;width:100%;height:100%;">
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
    

     var url = "";	
     	url = "${Path}roleController/list.do"
   
    load(url);//默认加载所有数据
    
    function load(url){
        var id="tab$0";
	    var tabInit = tabs.getTab(id);
	    tabInit = {};
	    //tabInit._nodeid ="0";
	    tabInit.name = id;
	    tabInit.showCloseButton = false;
	    tabInit.url = url;
	    tabInit.refreshOnClick=false;
	    tabs.addTab(tabInit);
	    tabs.activeTab(tabInit);
	    var tree = mini.get("leftTree"); 
	    //tree.expandAll();
    }
       
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
           tab.url = url+"?pid="+node.PK;
		   tabs.addTab(tab);
		   //tabs.updateTab(tab,)
		   //tabs.reloadTab(tab);
		}
		tab.refreshOnClick=true;
		tabs.activeTab(tab);
	}
    
	//选中节点 
	function onNodeSelect(e) {
	    var ta = tabs.getTab("tab$0");
  		tabs.removeAll(ta); 
		var ta1 = tabs.getTab("tab$");
  		tabs.removeAll(ta1);  
     	
		var node = e.node;
		var isLeaf = e.isLeaf;
		if (!isLeaf) {
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
