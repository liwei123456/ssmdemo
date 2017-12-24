<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>登录页面</title>
<link href="<%=basePath%>resources/user/css/login.css" rel="stylesheet" type="text/css"/>
<script src="<%=basePath%>resources/jquery/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>resources/artdialog/artDialog.source.js?skin=black"></script>
<script type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
	});
	function regist(){
		var html="<div><span class='u_logo'></span><input class='ipt' id='userName' name='userName' type='text' placeholder='请输入用户名或邮箱'/></div>";
		html+="<div><span class='p_logo'></span><input class='ipt' id='password' name='password' type='password' placeholder='请输入密码'/></div>";
		artDialog(
				{
					title:'注册',
		            content:html,
		            lock:true,
		            style:'succeed noClose'
		        },
		        function(){
			        alert("你点了确定");
		        },
		        function(){
			        alert("你点了取消");
		        }
		);
	}
	function login(){
		if($("#userName").val()==""){
			alert("帐号不能为空！");
			$("#userName").focus();
			return;
		}else if($("#password").val()==""){
			alert("密码不能为空！");
			$("#password").focus();
			return;
		}
		$.ajax({
			url:"<%=basePath%>user/login",
			data:$("#form").serialize(),
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.message!=""){
					alert(data.message);
				}else{
					if(data.user!="" && data.user.id!=""){
						location.href="<%=basePath%>user/showUser?id="+data.user.id;
					}
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="top_div"></div>
	<div style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou"></div>
			<div class="initial_left_hand" id="left_hand"></div>
			<div class="initial_right_hand" id="right_hand"></div>
		</div>
		<form id="form" name="form" method="post">
			<p style="padding: 30px 0px 10px; position: relative;">
				<span class="u_logo"></span> 
				<input class="ipt" id="userName" name="userName" type="text" placeholder="请输入用户名或邮箱" value=""/>
			</p>
			<p style="position: relative;">
				<span class="p_logo"></span>
				<input class="ipt" id="password" name="password" type="password" placeholder="请输入密码" value="" />
			</p>
			<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
				<p style="margin: 0px 35px 20px 45px;">
					<span style="float: left;">
						<a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
					</span>
					<span style="float: right;">
						<a style="color: rgb(204, 204, 204); margin-right: 10px;cursor: pointer;" onclick="regist()">注册</a>
						<a style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold; cursor: pointer;"
						onclick="login()">登录</a>
					</span>
				</p>
			</div>
		</form>
	</div>
	<div style="text-align: center;">
		<p>来源:<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
	</div>
</body>
</html>
