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
    
    <title>重新编辑音乐</title>
    
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
	    	
	      <h1>分享音乐</h1>
	      
      	  	<form class="form-horizontal" role="form" action="areUploadSongs" method="post">
      	  		<div class="form-group">
      	  			<input name="song.sid" type="hidden" value='<s:property value="#session.reEditSong.getSid()"/>' />
      	  			<p class="col-2">歌曲名称：</p>
      	  			<input name="song.songname" class="col-4 form-control"  type="text" value='<s:property value="#session.reEditSong.getSongname()"/>'>
      	  		</div>
      	  		<div class="form-group">
      	  			<p class="col-2">歌手：</p>
      	  			<input name="song.songsinger" class="col-4 form-control" data-role="tagsinput"  value='<s:property value="#session.reEditSong.getSongsinger()"/>'>
      	  		</div>
      	  		<div class="form-group">
      	  			<p class="col-2">歌曲简介：</p>
      	  			<textarea name="song.songdesc" id="editor" class="col-4 form-control"> <s:property value="#session.reEditSong.getSongdesc()"/> </textarea>
      	  		</div>
      	  		<div class="form-group">
      	  			<p class="col-2">歌曲地址</p>
      	  			<input name="song.songsrc" class="col-4 form-control" type="text" value='<s:property value="#session.reEditSong.getSongsrc()"/>'>
      	  			<small>由于主机存储空间原因，用户只能分享链接，管理员可上传音乐文件。</small>
      	  			<a href="http://music.teenshare.club" class="col-1 btn btn-sm btn-info" target="_blank">音乐地址</a>
      	  		</div>
      	  		<input type="submit" class="col-md-8 btn btn-success" value="点击提交">
      	  	</form>		
      	  	
		</div>
	    </div>
	    <!-- import basic JS -->
	    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	    <script type="text/javascript" src="js/bootstrap.min.js"></script>
  </body>
</html>
