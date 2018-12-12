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
    
    <title>后台管理系统</title>
    
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
	
	<style type="text/css">
		.showbox{
			display:inline-block;
			width: 30%;
			height: 270px;
			/* border:1px dashed grey;
			padding:5px;
			border-radius: 5px; */
			
		}
	</style>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/responsive-nav.js"></script>
  </head>
  
  <body>
  		<s:action name="initAdmin" executeResult="false"></s:action>
    	<jsp:include page="slider.jsp"></jsp:include>
    	
    	<div role="main" class="main">
	      <h1>后台管理系统</h1>
      		<s:debug></s:debug>
		  <div class="container">
		  	<h2>欢迎来到您的后台管理系统 </h2>
		  	<p>这里是网站的管理中心，您可以在这里，管理所有分享的音乐以及所发布的评论。</p>
		  	
		  	<hr>
		  	
		  		<div class="showbox">
		  			<h2>总分享次数</h2>
		  			<b> <s:property value="#session.allSongs.size()"/> </b><br>
		  			<a href="ShowSongsAdmin" class="btn btn-success">前往管理</a>
		  		</div>
		  		<div class="showbox">
		  			<h2>总评论次数</h2>
		  			<b> <s:property value="#session.allComments.size()"/> </b><br>
		  			<a href="ShowCommentsAdmin" class="btn btn-success">前往管理</a>
		  		</div>
		  		<%-- <div class="showbox">
		  			<h2>歌曲推荐</h2>
	  				<s:iterator value="#session.hotSongList" id="sl">
	  					<a style="color:black;" href="DetailSongs?sid=${sl.getSid() }" target="_blank" ><b>${sl.getSongsinger() }</b>的
	  					${sl.getSongname() }</a><br/>
	  				</s:iterator>
		  		</div> --%>
		  	</div>
	    </div>
	   
  </body>
</html>
