<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>错误信息页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	<link rel="stylesheet" type="text/css" href="flat/css/vendor/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="flat/css/flat-ui.min.css">
	<link href="flat/docs/assets/css/demo.css" rel="stylesheet">
	<link href="css/styles.css" type="text/css" rel="stylesheet">
	<!-- import music player css -->
	<link rel="stylesheet" type="text/css" href="css/mplayer.css">

  </head>
  
  <body>
    <jsp:include page="header.jsp"></jsp:include>
    
    <div class="container">
    	<div class="alert alert-danger">
		    <strong>错误!</strong> <s:fielderror></s:fielderror>
		    <button class="btn btn-info" onclick="javascript:history.back(-1);">点击返回</button>
		</div>
    </div>
    
    <!-- jQuery -->
    <script type="text/javascript" src="flat/scripts/jquery-3.3.1.min.js" ></script>
    <!-- Bootstrap 4 requires Popper.js -->
    <script type="text/javascript" src="flat/scripts/popper.min.js" ></script>
    <script type="text/javascript" src="flat/scripts/bootstrap.min.js" ></script>
    <!-- Flat JS引入-->
    <script type="text/javascript" src="flat/scripts/flat-ui.min.js" ></script>
    <script type="text/javascript" src="flat/scripts/prettify.js" ></script>
    <script type="text/javascript" src="flat/scripts/application.js" ></script>
  </body>
</html>
