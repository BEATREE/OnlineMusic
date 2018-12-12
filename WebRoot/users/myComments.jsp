<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- import bootstrap css file -->
	<link rel="stylesheet" type="text/css" href="flat/css/vendor/bootstrap.min.css">
	<!-- import flat css file -->
	<link rel="stylesheet" type="text/css" href="flat/css/flat-ui.min.css">
	<link rel="stylesheet" type="text/css" href="css/nav_style.css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/responsive-nav.js"></script>

  </head>
  
  <body>
    	<jsp:include page="slider.jsp"></jsp:include>
    	<div class="container">
    	<div role="main" class="main">
	    	
	      <h1>我的评论</h1>
			<table class="table table-striped table-hover">
				<tr>
					<th>相关歌曲</th><th>评论内容</th><th>评论时间</th><th>操作</th>
				</tr>
				<s:iterator value="#session.myComments" id="myComments">
					<tr>
						<td><a style="color:#666666;" href="DetailSongs?sid=${myComments.getSong().getSid() }" target="_blank">${myComments.getSong().getSongname() }</a></td>
						<td>${myComments.getCcontent() }</td>
						<td>${myComments.getCtime() }</td>
						<td>
							<a href="delMycommentsUser?cid=${myComments.getCid() }" class="btn btn-success" onclick="return confirm('您确定要删除这条评论吗？\n此操作无法恢复')">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>	      
		</div>
	    </div>
	    <!-- import basic JS -->
	    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	    <script type="text/javascript" src="js/bootstrap.min.js"></script>
  </body>
</html>