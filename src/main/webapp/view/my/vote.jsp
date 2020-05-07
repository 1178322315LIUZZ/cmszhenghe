<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--??  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>I投票</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript"
	src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<form id="form1">
		<div class="form-group">
			<label for="title"> 投票标题： </label> <input id="title" type="text"
				class="form-control" name="title">
		</div>
		<div class="form-group" id="options">
			<label for="title"> 投票项目：
				<button class="btn btn-info" type="button" onclick="addOptions()">增加项目</button>
			</label> <input type="text" name="options" class="form-control">
		</div>
		<div class="form-group">
			<button class="btn btn-warning" type="button" onclick="publishVote()">发起投票</button>
		</div>
	</form>
	<script type="text/javascript">
		function addOptions() {
			$("#options").append("<input  type='text' name='options' class='form-control' style='margin-top:5px'>")
		}
		function publishVote() {
			$.post(
					"/my/publishVote",
					$("#form1").serialize(),
					function(as){
						if(as>0){
							alert("发布成功");
					 		location.href="/my";
						}else{
							 alert("发布失败")
							 location.reload()
						 }
					 }
				)
		}
	</script>
</body>
</html>