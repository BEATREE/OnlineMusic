<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="flat/css/vendor/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="flat/css/flat-ui.min.css">
<link href="css/styles.css" type="text/css" rel="stylesheet">

</head>

<body>
	<div class="container" style="width:940px;">
		<div class="login">
			
	       <div class="login-screen">
	         <div class="login-icon">
	           <img src="img/mplayer_error.png" alt="Welcome to Teen Music">
	           <h4>Welcome to <small>Admin Page</small></h4>
	         </div>
			<form action="AdminLoginAction" method="post">
	         <div class="login-form">
	           <div class="form-group">
	             <input name="admin.aname" type="text" class="form-control login-field" required placeholder="用户名">
	             <label class="login-field-icon fui-user" for="login-name"></label>
	           </div>
	
	           <div class="form-group">
	             <input name="admin.apasswd" type="password" class="form-control login-field" required placeholder="密码">
	             <label class="login-field-icon fui-lock" for="login-pass"></label>
	           </div>
	           <input class="btn btn-primary btn-lg btn-block" type="submit" value="登录">
	         </div>
	        </form>
	       </div>
      
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
