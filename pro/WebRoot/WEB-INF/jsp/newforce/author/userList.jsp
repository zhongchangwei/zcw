<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/common/common.jsp"%>
<title>树形</title>

</head>

<body>

	<!-- <div id="menuContent" class="menuContent" style="width:95%;border:1px solid rgb(170,170,170);z-index:10;">
         <ul id="treeDemo" class="ztree" style="margin-top:0; width:100%; height:auto;"></ul>
    </div>
 -->
			<input type="text" class="form-control" id="txt_ztree" placeholder="点击文本框出现ztree" onclick="showMenu()" />
            <div id="menuContent2" class="menuContent" style="display:none;position: absolute;width:95%;border:1px solid rgb(170,170,170);z-index:10;">
                <ul id="treeDemo2" class="ztree" style="margin-top:0; width:160px; height:auto;"></ul>
            </div>

</body>

<script type="text/javascript">

var setting = {
	    view: {
	        addHoverDom: addHoverDom,
	        removeHoverDom: removeHoverDom,
	        selectedMulti: false
	    },
	    check: {
	        enable: true
	    },
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    edit: {
	        enable: false
	    }
	};

	var zNodes = [
	    { id: 1, pId: 0, name: "父节点1", open: true },
	    { id: 11, pId: 1, name: "父节点11" },
	    { id: 111, pId: 11, name: "叶子节点111" },
	    { id: 112, pId: 11, name: "叶子节点112" },
	    { id: 113, pId: 11, name: "叶子节点113" },
	    { id: 114, pId: 11, name: "叶子节点114" },
	    { id: 12, pId: 1, name: "父节点12" },
	    { id: 121, pId: 12, name: "叶子节点121" },
	    { id: 122, pId: 12, name: "叶子节点122" },
	    { id: 123, pId: 12, name: "叶子节点123" },
	    { id: 124, pId: 12, name: "叶子节点124" },
	    { id: 13, pId: 1, name: "父节点13", isParent: true },
	    { id: 2, pId: 0, name: "父节点2" },
	    { id: 21, pId: 2, name: "父节点21", open: true },
	    { id: 211, pId: 21, name: "叶子节点211" },
	    { id: 212, pId: 21, name: "叶子节点212" },
	    { id: 213, pId: 21, name: "叶子节点213" },
	    { id: 214, pId: 21, name: "叶子节点214" },
	    { id: 22, pId: 2, name: "父节点22" },
	    { id: 221, pId: 22, name: "叶子节点221" },
	    { id: 222, pId: 22, name: "叶子节点222" },
	    { id: 223, pId: 22, name: "叶子节点223" },
	    { id: 224, pId: 22, name: "叶子节点224" },
	    { id: 23, pId: 2, name: "父节点23" },
	    { id: 231, pId: 23, name: "叶子节点231" },
	    { id: 232, pId: 23, name: "叶子节点232" },
	    { id: 233, pId: 23, name: "叶子节点233" },
	    { id: 234, pId: 23, name: "叶子节点234" },
	    { id: 3, pId: 0, name: "父节点3", isParent: true }
	];

	$(document).ready(function () {
	    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});

	function addHoverDom(treeId, treeNode) {
	    var sObj = $("#" + treeNode.tId + "_span");
	    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
	    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	        + "' title='add node' onfocus='this.blur();'></span>";
	    sObj.after(addStr);
	    var btn = $("#addBtn_" + treeNode.tId);
	    if (btn) btn.bind("click", function () {
	        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	        zTree.addNodes(treeNode, { id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++) });
	        return false;
	    });
	};
	function removeHoverDom(treeId, treeNode) {
	    $("#addBtn_" + treeNode.tId).unbind().remove();
	};


//_____________________________________分割线————————————————————————————
　var setting2 = {
	    check: {
	        enable: true,
	        chkStyle: "radio",
	        radioType: "all"
	    },
	    view: {
	        dblClickExpand: false
	    },
	    data: {
	        simpleData: {
	            enable: true
	        }
	    },
	    callback: {
	        onClick: onClickNode,
	        onCheck: onCheck
	    }
	};

	var zNodes = [
	    { id: 1, pId: 0, name: "父节点1", open: true },
	    { id: 11, pId: 1, name: "父节点11" },
	    { id: 111, pId: 11, name: "叶子节点111" },
	    { id: 112, pId: 11, name: "叶子节点112" },
	    { id: 113, pId: 11, name: "叶子节点113" },
	    { id: 114, pId: 11, name: "叶子节点114" },
	    { id: 12, pId: 1, name: "父节点12" },
	    { id: 121, pId: 12, name: "叶子节点121" },
	    { id: 122, pId: 12, name: "叶子节点122" },
	    { id: 123, pId: 12, name: "叶子节点123" },
	    { id: 124, pId: 12, name: "叶子节点124" },
	    { id: 13, pId: 1, name: "父节点13", isParent: true },
	    { id: 2, pId: 0, name: "父节点2" },
	    { id: 21, pId: 2, name: "父节点21", open: true },
	    { id: 211, pId: 21, name: "叶子节点211" },
	    { id: 212, pId: 21, name: "叶子节点212" },
	    { id: 213, pId: 21, name: "叶子节点213" },
	    { id: 214, pId: 21, name: "叶子节点214" },
	    { id: 22, pId: 2, name: "父节点22" },
	    { id: 221, pId: 22, name: "叶子节点221" },
	    { id: 222, pId: 22, name: "叶子节点222" },
	    { id: 223, pId: 22, name: "叶子节点223" },
	    { id: 224, pId: 22, name: "叶子节点224" },
	    { id: 23, pId: 2, name: "父节点23" },
	    { id: 231, pId: 23, name: "叶子节点231" },
	    { id: 232, pId: 23, name: "叶子节点232" },
	    { id: 233, pId: 23, name: "叶子节点233" },
	    { id: 234, pId: 23, name: "叶子节点234" },
	    { id: 3, pId: 0, name: "父节点3", isParent: true }
	];

	//初始化
	$(document).ready(function () {
	    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes);
	});

	//显示菜单
	function showMenu() {
	    $("#menuContent2").css({ left: "15px", top: "34px" }).slideDown("fast");

	    $("body").bind("mousedown", onBodyDown);
	}
	//隐藏菜单
	function hideMenu() {
	    $("#menuContent2").fadeOut("fast");
	    $("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
	    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent2" || event.target.id == "txt_ztree" || $(event.target).parents("#menuContent2").length > 0)) {
	        hideMenu();
	    }
	}

	//节点点击事件
	function onClickNode(e, treeId, treeNode) {
	    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	    zTree.checkNode(treeNode, !treeNode.checked, null, true);
	    return false;
	}

	//节点选择事件
	function onCheck(e, treeId, treeNode) {
	    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
	    nodes = zTree.getCheckedNodes(true),
	    v = "";
	    var parentid = "";
	    var parentlevel = "";
	    for (var i = 0, l = nodes.length; i < l; i++) {
	        v += nodes[i].name + ",";
	        parentid += nodes[i].id + ",";
	        parentlevel += nodes[i].menu_level + ",";
	    }
	    if (v.length > 0) {
	        v = v.substring(0, v.length - 1);
	        parentid = parentid.substring(0, parentid.length - 1);
	        parentlevel = parentlevel.substring(0, parentlevel.length - 1);
	    }
	    else {
	        return;
	    }

	    hideMenu();
	}
 </script>
</html>
