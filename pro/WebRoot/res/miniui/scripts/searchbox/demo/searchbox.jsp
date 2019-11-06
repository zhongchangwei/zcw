<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<script src="../../boot.js" type="text/javascript"></script>
	<link href="../../miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级查询框demo</title>
</head>
<body>  
	 <a class="mini-button" iconCls="icon-search" onclick="test();">查询</a>
	 <!-- grid -->
	 <div id="datagrid1" class="mini-datagrid" style="width:100%;height:400px;" 
    url="<%=basePath %>minidemo/loaddata.do"  idField="NID" allowResize="true"
     allowAlternating="true" >  
    <div property="columns"> 
        <div type="indexcolumn" ></div> 
        <div field="NID" width="120" allowSort="true">NID</div>
        <div field="ProductID" width="120" allowSort="true">ProductID</div>  
        <div field="ProdName" width="120" allowSort="true">ProdName</div>  
        <div field="Unit" width="120" allowSort="true">Unit</div>  
        <div field="NID" width="120" allowSort="true">NID</div>               
    </div>
	</div> 
</body>
 <script type="text/javascript">
//这里配置要查询的字段
 var fields = [
				{ name: 'NID', field: 'NID', type: 'string', table: '', fastQueryId: "c", fastQuery: true, queryArgs: true, defaultVal: "", notNull: false, defOperator: "等于" },
               { name: '产品id', field: 'ProductID', type: 'string', table: '', fastQueryId: "a", fastQuery: true, queryArgs: true, defaultVal: "80100125000", notNull: false, defOperator: "等于" },
               { name: '产品名称', field: 'ProdName', type: 'string', table: '', fastQueryId: "b", fastQuery: true, queryArgs: true, defaultVal: "", notNull: false, defOperator: "等于" }
               
               
           ];
        mini.parse();
        var grid = mini.get("datagrid1");        
        grid.load();
        //打开高级查询框  备注：查询方案暂时不能用
        top["windemo"] = window;
        function test() {
        	 
            
            mini.open({
                url: '/HIP/htwyRes/miniui/scripts/searchbox/page/filterwindow.jsp?id=datagrid1&win=windemo',
                maskOnLoad: false,
                width: 600,
                height: 400,
                onload: function () {
	                        var iframe = this.getIFrameEl();
	                        var data = fields;
	                        iframe.contentWindow.SetData(data);
	                    }
            });

        }
       
    </script>
</html>