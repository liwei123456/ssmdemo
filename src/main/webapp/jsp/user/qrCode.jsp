<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>  
  <head>
  	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <title>Bootstrap二维码在线生成代码</title>
    <link href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/> 
    <script src="<%=basePath%>resources/jquery/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/user/js/modernizr-2.8.3.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
  	<!-- 推荐脚本定位 -->
    <script src="<%=basePath%>resources/user/js/qrcode.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>resources/artdialog/artDialog.source.js?skin=black"></script>
  	<script>
  		function setting(){
  			var html="<div><input class='ipt' id='userName' name='userName' type='text' placeholder='请输入用户名或邮箱'/></div>";
  			html+="<div><input class='ipt' id='password' name='password' type='password' placeholder='请输入密码'/></div>";
  			artDialog(
  					{
  						title:'个人信息',
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
  	</script>
  </head>
<body class="indexbody">
	<div class="container">
		<div>
			<ul class="nav pull-right">
				<li id="fat-menu" class="dropdown">
					<a href="#" id="drop3" role="button" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-user"></i>
							${user.userName}
						<i class="icon-caret-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" style="cursor: pointer;" onclick="setting()">设置</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" href="<%=basePath%>user/logout">登出</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!--路径导航-->
		<div class="row">
			<ol class="breadcrumb clearfix">
				<li><span>首页</span></li>
				<li><span>小工具</span></li>
				<li><a style="cursor: pointer;">二维码生成</a></li>
			</ol>
		</div>
		<!--/路径导航-->
		
		<!--表单-->
		<div class="panel panel-sm">
			<div class="panel-heading clearfix">
				<label><i class="fa fa-bookmark"></i> 二维码生成</label>
			</div>
			<form action="" class="form-horizontal panel-body" id="form1" method="post">
				<ul class="nav nav-tabs nav-tabs-sm" role="tablist" id="mytabs">
					<li role="presentation" class="active">
						<a href="http://www.baidu.com" role="tab" data-toggle="tab" id="tab1" aria-controls="pane1"><strong>QRCode Generator</strong></a>
					</li>
				</ul>
				<div class="tab-content" style="padding-top: 15px;">
					<div role="tabpanel" class="tab-pane fade in active" id="pane1" aria-labelledby="tab1">
						<div class="form-group form-group-sm">
							<label class="control-label col-md-2">二维码内容</label>
							<div class="col-md-10">
								<textarea name="txtContent" id="txtContent" rows="2" class="form-control input-normal" placeholder="http://www.baidu.com"></textarea>
							</div>
						</div>

						<div class="form-group form-group-sm">
							<label class="control-label col-md-2">二维码图像</label>
							<div class="col-md-10">
								<div id="qrcode"></div>
								<p class="m-top-10 text-muted">使用手机扫一扫</p>
							</div>
						</div>
						
						<div class="form-group form-group-sm">
							<div class="col-md-10 col-md-offset-2">
								<p class="form-control-static text-danger" id="tips"></p>
							</div>
						</div>
					</div>
				</div>
				<hr class="sm">
				<div class="btn-footer">
					<button name="btnCreate" class="btn btn-success" type="button" onClick="f_onCreateQrcode();" title="将根据您填写的内容生成二维码图像">开始生成</button>
					<button name="btnClear" class="btn btn-default" type="button" onClick="f_onClearQrcode();" title="清除">清除</button>
				</div>
			</form>    
		</div>
	</div>
	<!--/主体内容-->
    <script type="text/javascript">
        //二维码内容
        var txtContent = document.getElementById("txtContent"), tips = $("#tips");

        // 设置 qrcode 参数
        var qrcode = new QRCode('qrcode', {
            text: 'www.baidu.com',
            width: 256,
            height: 256,
            colorDark: '#000000',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
        });

        //生成二维码图像
        function f_onCreateQrcode() {
            if (!txtContent) {
                return false;
            } else {
                if (!txtContent.value) {
                     tips.html("！请填写<strong>二维码内容</strong>再提交生成");
                } else {
                    // 使用 API
                    qrcode.clear();
					tips.empty();
                    qrcode.makeCode(txtContent.value);
                }
                return false;
            }
        }

        //清除二维码内容
        function f_onClearQrcode() {
            if (txtContent) {
                txtContent.value = "www.baidu.com";
                // 使用 API
                qrcode.clear();
                qrcode.makeCode('请输入二维码内容再生成图像');
            }
        }
    </script>
	<div style="text-align:center;">
		<p><a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>
	</div>
</body>
</html> 