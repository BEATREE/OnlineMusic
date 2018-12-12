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
    
    <title>在线音乐平台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="MUSIC,OnlineMusic,音乐，在线音乐">
	<meta http-equiv="description" content="在线音乐平台，为音乐爱好者提供分享的平台">
	
	<link rel="stylesheet" type="text/css" href="flat/css/vendor/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="flat/css/flat-ui.min.css">
	<link href="flat/docs/assets/css/demo.css" rel="stylesheet">
	<link href="css/styles.css" type="text/css" rel="stylesheet">
	<!-- import music player css -->
	<link rel="stylesheet" type="text/css" href="css/mplayer.css">
  </head>
  
  <body>
	    <jsp:include page="header.jsp"></jsp:include>
	    <s:action name="initAction" executeResult="false"></s:action>
		<!-- 轮播图片 -->
		<div class="container">
			<div class="carousel slide" id="carousel-599109">
				<ol class="carousel-indicators">
					<li data-slide-to="0" data-target="#carousel-599109">
					</li>
					<li data-slide-to="1" data-target="#carousel-599109">
					</li>
					<li data-slide-to="2" data-target="#carousel-599109" class="active">
					</li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item">
						<img class="d-block w-100" alt="Carousel Bootstrap First" src="img/chengmeng.jpg" />
						<!-- <div class="carousel-caption">
							<h4>
								橙梦
							</h4>
							<p>
								简单自然，随意如梦
							</p>
						</div> -->
					</div>
					<div class="carousel-item">
						<img class="d-block w-100" alt="Carousel Bootstrap Second" src="img/fangdanshaonian.jpg" />
						
					</div>
					<div class="carousel-item active">
						<img class="d-block w-100" alt="Carousel Bootstrap Third" src="img/shuishunana.jpg" />
						
					</div>
				</div> <a class="carousel-control-prev" href="#carousel-599109" data-slide="prev"><span class="carousel-control-prev-icon"></span> <span class="sr-only">Previous</span></a> <a class="carousel-control-next" href="#carousel-599109" data-slide="next"><span class="carousel-control-next-icon"></span> <span class="sr-only">Next</span></a>
			</div>
			<!-- 轮播结束 -->
			<div class="leftPart">
				<div class="bigPart">
					<div class="row">
						<h4>热门合辑</h4>
					</div>
					<div class="row">
						<ul>
							<s:iterator value="#session.allPlayList" id="playlist">
								<a href="DetailPlaylist?plid=${playlist.getPlid() }"><li><img class="repImg" src="${playlist.getPlimg() }"/>
									<div><small style="color:black;">${playlist.getPlname() }</small> </div>
								</li></a>
							</s:iterator>
						</ul>
					</div>
				</div>
				<div class="bigPart listPart">
					<div class="row">
						<h4>小小排行榜</h4>
					</div>
					<div class="row">
						<div class="split-part col-sm-6">
							<b>最热歌曲</b>
							<ul>
								<s:iterator value="#session.hotSongList" id="hsl">
									<a href="DetailSongs?sid=${hsl.getSid() }"><li>${hsl.getSongname() }<span style="float:right;margin-right:8px;">${hsl.getSongsinger() }</span></li></a>
									
								</s:iterator>
							</ul>
						</div>
						<div class="split-part col-sm-6">
							<b>最新上传</b>
							<ul>
								<s:iterator value="#session.newSongList" id="nsl">
									<a href="DetailSongs?sid=${nsl.getSid() }"><li>${nsl.getSongname() }<span style="float:right;margin-right:8px;">${nsl.getSongsinger() }</span></li></a>
									
								</s:iterator>
							</ul>
						</div>
					</div>
				</div>
				
			</div>
			<div class="rightPart">
				<div class="loginPart">
					<s:if test="#session.currentUser==null">
					登录TeenMusic，享受分享的乐趣
					<div class="row">
						<div class="col-sm-3"></div>
						<button class="col-sm-6 btn btn-danger" data-toggle="modal" data-target="#loginModal">前往登录</button>
						
					</div>
					</s:if><s:else>
						天天开心哦~<s:property value="#session.currentUser.getNickname()" />
						<div class="row">
							<div class="col-sm-3"></div>
							<button class="col-sm-6 btn btn-danger" onclick="window.location.href='users/index.jsp'">个人中心</button>
							
						</div>
					</s:else>
					<!-- 登录模态框 -->
					<div class="modal fade" id="loginModal">
					  <div class="modal-dialog">
					    <div class="modal-content">
					 
					      <!-- 模态框头部 -->
					      <div class="modal-header">
					        <h6 class="modal-title">登录</h6>
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
					 
					      <!-- 模态框主体 -->
					      <div class="modal-body">
					        	<form class="form" role="form" action="LoginAction" method="post">
					        		 <div class="form-group row">
									    <label for="staticEmail" class="col-sm-3 col-form-label">帐号：</label>
									    <div class="col-sm-8">
									      <input type="text" class="form-control" name="user.username" placeholder="Username">
									    </div>
									  </div>
									  <div class="form-group row">
									    <label for="inputPassword" class="col-sm-3 col-form-label">密码：</label>
									    <div class="col-sm-8">
									      <input type="password" class="form-control" name="user.userpasswd" placeholder="Password">
									    </div>
									  </div>
									  <div class="form-group row">
									  		<div class="col-sm-2"></div>
									  		<input type="button" class="col-sm-3 btn btn-info" data-dismiss="modal" data-toggle="modal" data-target="#registerModal" value="前往注册" />
									   		<div class="col-sm-2"></div>
									   		<input type="submit" value="点击登录" class="col-sm-3 btn btn-success"/>
									  </div>
					        	</form>
					      </div>
					 
					      <!-- 模态框底部-->
					      
					      <div class="modal-footer">
					      	<s:fielderror></s:fielderror>
					       <!--  <button type="button" class="btn btn-secondary" data-dismiss="modal" data-toggle="modal" data-target="#registerModal">前往注册</button> -->
					      </div> 
					 
					    </div>
					  </div>
					</div>
					<div class="modal fade" id="registerModal">
					  <div class="modal-dialog">
					    <div class="modal-content">
					 
					      <!-- 模态框头部 -->
					      <div class="modal-header">
					        <h6 class="modal-title">注册帐号</h6>
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					      </div>
					 
					      <!-- 模态框主体 -->
					      <div class="modal-body">
					        	<form class="form" role="form" action="RegisterAction" method="post">
					        		 <div class="form-group row">
									    <label for="staticEmail" class="col-sm-3 col-form-label">帐号：</label>
									    <div class="col-sm-8">
									      <input type="text" class="form-control" name="user.username" placeholder="Username">
									    </div>
									  </div>
									  <div class="form-group row">
									    <label for="staticEmail" class="col-sm-3 col-form-label">昵称：</label>
									    <div class="col-sm-8">
									      <input type="text" class="form-control" name="user.nickname" placeholder="Username">
									    </div>
									  </div>
									  <div class="form-group row">
									    <label for="inputPassword" class="col-sm-3 col-form-label">密码：</label>
									    <div class="col-sm-8">
									      <input type="password" class="form-control" name="user.userpasswd" placeholder="Password">
									    </div>
									  </div>
									  <div class="form-group row">
									    <label for="inputPassword" class="col-sm-3 col-form-label">重复密码：</label>
									    <div class="col-sm-8">
									      <input type="password" class="form-control" placeholder="Password">
									    </div>
									  </div>
									  <div class="form-group row">
									  		<div class="col-sm-2"></div>
									  		<input type="button" class="col-sm-3 btn btn-info" data-dismiss="modal" data-toggle="modal" data-target="#loginModal" value="前往登录" />
									   		<div class="col-sm-2"></div>
									   		<input type="submit" value="点击注册" class="col-sm-3 btn btn-success"/>
									  </div>
					        	</form>
					      </div>
					      <!--  模态框底部 
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">前往注册</button>
					      </div> -->
					 
					    </div>
					  </div>
					</div>
				</div>
				<div class="hotList">
					<h5>热门歌曲</h5>
					<ul>
						<s:iterator value="#session.hotSongList" id="hsl">
							<a href="DetailSongs?sid=${hsl.getSid() }"><li>${hsl.getSongname() }&nbsp;————&nbsp;${hsl.getSongsinger() }</li></a>
						</s:iterator>
					</ul>
				</div>
			</div>
			
	    </div>
	  <!-- define a box of player -->
	  <div class="mp">
			<div class="mp-box">
				<img src="img/mplayer_error.png" alt="music cover" class="mp-cover">
				<div class="mp-info">
					<p class="mp-name">燕归巢</p>
					<p class="mp-singer">许嵩</p>
					<p><span class="mp-time-current">00:00</span>/<span class="mp-time-all">00:00</span></p>
				</div>
				<div class="mp-btn">
					<button class="mp-prev" title="上一首"></button>
					<button class="mp-pause" title="播放"></button>
					<button class="mp-next" title="下一首"></button>
					<button class="mp-mode" title="播放模式"></button>
					<div class="mp-vol">
						<button class="mp-vol-img" title="静音"></button>
						<div class="mp-vol-range" data-range_min="0" data-range_max="100" data-cur_min="80">
							<div class="mp-vol-current"></div>
							<div class="mp-vol-circle"></div>
						</div>
					</div>
				</div>
				<div class="mp-pro">
					<div class="mp-pro-current"></div>
				</div>
				<div class="mp-menu">
					<button class="mp-list-toggle"></button>
					<button class="mp-lrc-toggle"></button>
				</div>
			</div>
			<button class="mp-toggle">
				<span class="mp-toggle-img"></span>
			</button>
			<div class="mp-lrc-box">
				<ul class="mp-lrc"></ul>
			</div>
			<button class="mp-lrc-close"></button>
			<div class="mp-list-box">
				<ul class="mp-list-title"></ul>
				<table class="mp-list-table">
					<thead>
						<tr>
							<th>歌名</th>
							<th>歌手</th>
							<th>时长</th>
						</tr>
					</thead>
					<tbody class="mp-list"></tbody>
				</table>
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
	  
	  <!-- import music player js -->
	  <script src="http://www.jq22.com/jquery/jquery-2.1.1.js"></script>
	  	<script src="js/mplayer.js"></script>
		<script src="js/mplayer-list.js"></script>
		<script src="js/mplayer-functions.js"></script>
		<script src="js/jquery.nstSlider.js"></script>
		<script>
		var modeText = ['顺序播放','单曲循环','随机播放','列表循环'];
		var player = new MPlayer({
			// 容器选择器名称
			containerSelector: '.mp',
			// 播放列表
			songList: mplayer_song,
			// 专辑图片错误时显示的图片
			defaultImg: 'img/mplayer_error.png',
			// 自动播放
			autoPlay: true,
			// 播放模式(0->顺序播放,1->单曲循环,2->随机播放,3->列表循环(默认))
			playMode:0,
			playList:0,
			playSong:0,
			// 当前歌词距离顶部的距离
			lrcTopPos: 34,
			// 列表模板，用${变量名}$插入模板变量
			listFormat: '<tr><td>${name}$</td><td>${singer}$</td><td>${time}$</td></tr>',
			// 音量滑块改变事件名称
			volSlideEventName:'change',
			// 初始音量
			defaultVolume:60
		}, function () {
			// 绑定事件
			this.on('afterInit', function () {
				console.log('播放器初始化完成，正在准备播放');
			}).on('beforePlay', function () {
				var $this = this;
				var song = $this.getCurrentSong(true);
				var songName = song.name + ' - ' + song.singer;
				console.log('即将播放'+songName+'，return false;可以取消播放');
			}).on('timeUpdate', function () {
				var $this = this;
				console.log('当前歌词：' + $this.getLrc());
			}).on('end', function () {
				var $this = this;
				var song = $this.getCurrentSong(true);
				var songName = song.name + ' - ' + song.singer;
				console.log(songName+'播放完毕，return false;可以取消播放下一曲');
			}).on('mute', function () {
				var status = this.getIsMuted() ? '已静音' : '未静音';
				console.log('当前静音状态：' + status);
			}).on('changeMode', function () {
				var $this = this;
				var mode = modeText[$this.getPlayMode()];
				$this.dom.container.find('.mp-mode').attr('title',mode);
				console.log('播放模式已切换为：' + mode);
			});
		});
		
		
		$(document.body).append(player.audio); // 测试用
		
		setEffects(player);
		
		
		</script>
	  
  </body>
</html>
