<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头头条-个人中心</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css"href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript"src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="background-color: #009FD9">
			<div class="col-md-12"><img alt="" src="/css/img/2.jpg"
				style="width: 55px; height: 55px" class="rounded-circle" onclick="tiao()"><font color="white" style="margin-left: 5px">管理员后台系统</font>
				<div style="float: right;padding-top: 10px"> <a href="/passport/logout"><font style="color: white"> 注销</font></a></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2" >
				<ul class="list-group">
					<li class="list-group-item list-group-item-action list-group-item-primary"><a href="#" data="/admin/articles"style="text-decoration:none">文章管理</a></li>
					<li class="list-group-item list-group-item-action list-group-item-secondary"><a href="#" data="/admin/user"style="text-decoration:none">用戶管理</a></li>
					<li class="list-group-item list-group-item-action list-group-item-success"><a href="#" style="text-decoration:none">友情鏈接</a></li>
					<li class="list-group-item list-group-item-action list-group-item-danger"><a href="#" style="text-decoration:none">系統設置</a></li>
				</ul>
			</div>
			<div class="col-md-10" id="center">
			
			</div>
		</div>
	</div>
</body>
 <script type="text/javascript">
 $("#center").load("/admin/articles")
  	$("li").click(function() {
		var url=$(this).children().attr("data")
		 $("#center").load(url) 
	})
function tiao() {
	location="/"
}
 </script>
</html>