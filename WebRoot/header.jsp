<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<nav class="navbar navbar-inverse navbar-fixed navbar-embossed navbar-expand-lg" role="navigation">
		  <a class="navbar-brand" href="#"><img style="height:30px;margin:0;" alt="TeenMusic" src="img/TeenMusicLogo.png"></a>
		  <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse-01"></button>
		  <div class="collapse navbar-collapse" id="navbar-collapse-01">
		    <ul class="nav navbar-nav mr-auto" id="navmain">
		      <li><a href="">首页</a></li>
		      <li><a href="HotlistShow">热歌榜</a></li>
		      <li><a href="SingersShow">歌手分类</a></li>
		    </ul>
		    <form class="navbar-form form-inline my-2 my-lg-0" action="SearchSongs" role="search">
		      <div class="form-group">
		        <div class="input-group">
		          <input class="form-control" name="keywords" id="navbarInput-01" type="search" placeholder="Search">
		          <span class="input-group-btn">
		            <button type="submit" class="btn"><span class="fui-search"></span></button>
		          </span>
		        </div>
		      </div>
		    </form>
		  </div><!-- /.navbar-collapse -->
		</nav><!-- /navbar -->
		<script type="text/javascript" src="js/nav.js"></script>
  </body>
</html>
