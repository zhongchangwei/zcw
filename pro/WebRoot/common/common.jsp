<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="Path" value="<%=basePath%>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	 <script src="<%=basePath%>/res/jquery.min.js"></script> 
	
	
	
	<script src="<%=path%>/res/miniui/swfupload/swfupload.js" type="text/javascript"></script> 
    <script src="<%=path%>/res/miniui/scripts/boot.js" type="text/javascript"></script>
    <script src="<%=path%>/res/miniui/scripts/miniui/miniui.js" type="text/javascript"></script>
    <link href="<%=path%>/res/css/global.css" rel="stylesheet" type="text/css" ></link>
    <link href="<%=path%>/res/css/index.css" rel="stylesheet" type="text/css" ></link>
    <link href="<%=path%>/res/miniui/scripts/miniui/themes/bootstrap/skin.css" rel="stylesheet" type="text/css"/>
   
	<!-- 通用js -->
	<script src="<%=path%>/res/commonJs/common.js" type="text/javascript"></script> 
	

 </head>  
</html>
