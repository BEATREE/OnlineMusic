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
    
    <title>重新编辑歌单</title>
    
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
	    	
	      <h1>修改歌单</h1>
	      
      	  	<form class="form-horizontal" role="form" action="reEditPlaylist" method="post" enctype="multipart/form-data">
      	  		<div class="form-group">
      	  			<input name="playlist.plid" type="hidden" value="${reEditPlaylist.getPlid() }">
      	  			<p class="col-2">歌单名称：</p>
      	  			<input name="playlist.plname" class="col-4 form-control"  type="text" value="${reEditPlaylist.getPlname() }">
      	  		</div>
      	  		
      	  		<div class="form-group">
      	  			<p class="col-2">歌单简介：</p>
      	  			<textarea name="playlist.pldesc" id="editor" class="col-4 form-control">${reEditPlaylist.getPldesc() }</textarea>
      	  		</div>
      	  		<div class="form-group">
      	  			<p class="col-2">加入歌曲：</p>
				  
				  <select name="allSongs" data-toggle="select" multiple class="col-4 form-control multiselect multiselect-success mrs mbm">
			          <s:iterator value="#session.reEditPlaylist.getPlsongs()" id="singlePlsong">
						  <option value="${singlePlsong.getSong().getSid() }" selected="selected">${singlePlsong.getSong().getSongname() }</option>
					  </s:iterator>
					  <s:iterator value="#session.allSongs" id="singleSong">
						  <option value="${singleSong.getSid() }">${singleSong.getSongname() }</option>
					  </s:iterator>
			      </select>

      	  		</div>
      	  		<input type="submit" class="col-md-8 btn btn-success" value="点击提交">
      	  	</form>		
      	  	
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
