<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'slider.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="//at.alicdn.com/t/font_851600_esgoz2bgr1u.css">
	

  </head>
  
  <body>
    <div role="navigation" id="foo" class="nav-collapse">
	  <div class="logoPic">
	  	<img alt="abc" src="img/TeenMusicLogo.png" onclick="window.location.href=''">
	  </div>
	  <ul>
	    <li><a href="admin/"><i class="fui-home"></i>&nbsp;后台主页</a></li>
	    <li><a href="ShowCommentsAdmin"><i class="fui-bubble"></i>&nbsp;评论信息</a></li>
	  	<li><a href="ShowSongsAdmin"><i class="fui-cmd"></i>&nbsp;歌曲信息</a></li>
	  	<li><a href="ShowUsersAdmin"><i class="fui-user"></i>&nbsp;用户信息</a></li>
	  	<li><a href="ShowPlaylistAdmin"><i class="fui-list-small-thumbnails"></i>&nbsp;歌单信息</a></li>
	  	<li><a href="admin/uploadMusic.jsp"><i class="fui-upload"></i>&nbsp;分享歌曲</a></li>
	  	<li><a href="admin/createPlaylist.jsp"><i class="fui-upload"></i>&nbsp;创建歌单</a></li>
	  	<li><a href="outLoginAdmin"><i class="fui-exit"></i>&nbsp;退出登录</a></li>
	  	
	  </ul>
	</div>
	<script type="text/javascript" src="js/slider.js"></script>
	<script>
	  var navigation = responsiveNav("foo", {customToggle: ".nav-toggle"});
	</script>
  </body>
</html>
