<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
   <!-- Bootstrap引用    start -->
    <link href="<%=basePath%>rs/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
	<!-- 可选的Bootstrap主题文件（一般不使用） -->
	<link href="<%=basePath%>rs/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=basePath%>rs/bootstrap/js/bootstrap.min.js"></script>  
<!-- Bootstrap引用    end --> 

<!-- 树形菜单zTree_v3引用    start -->
  	<link rel="stylesheet" href="<%=basePath%>rs/zTree_v3/css/demo.css" type="text/css">
	<link href="<%=basePath%>rs/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=basePath%>rs/zTree_v3/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>rs/zTree_v3/js/jquery.ztree.exedit.js"></script>
    <script type="text/javascript" src="<%=basePath%>rs/zTree_v3/js/jquery.ztree.excheck.js"></script>
<!-- 树形菜单zTree_v3引用    end -->
   
  </head>
  
  <body>
  </body>
</html>
