<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>  
  <head>
  	<meta charset="UTF-8">
    <title>首页</title>
    <link href="<%=basePath%>resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/> 
    <script src="<%=basePath%>resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>resources/jquery/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <style type='text/css'>
      body {
        background-color: #CCC;
      }
    </style>
  </head>
<body>
<div class="container">
	<h1>用户管理</h1>
	<nav class="navbar navbar-inverse">
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
	            <span class="sr-only">切换导航</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </button>
	        <a class="navbar-brand" href="#">品牌</a>
	    </div>
	    <div id="navbar-menu" class="collapse navbar-collapse">
	        <ul class="nav navbar-nav">
	            <li class="active"><a href="#">主页</a></li>
	            <li><a href="#">页面一</a></li>
	            <li><a href="#">页面二</a></li>
	        </ul>
	    </div>
	</nav>
	<div id="content" class="row-fluid">
		<div class="col-md-9">
			<table>
			  <caption>主要内容部分</caption>
			  <thead>
			    <tr>
			      <th>111</th>
			      <th>222</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td>333</td>
			      <td>444</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		<div class="col-md-3">
		    <h2>侧边栏</h2>
		    <ul class="nav nav-tabs nav-stacked">
		        <li class="active"><a href='#'>另一个链接 1</a></li>
		        <li><a href='#'>另一个链接 2</a></li>
		        <li><a href='#'>另一个链接 3</a></li>
		    </ul>    
		</div>
	</div>
</div>
</body>
</html> 