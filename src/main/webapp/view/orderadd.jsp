<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css"href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript"src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<form:form action="/add" method="post" enctype="multipart/form-data" modelAttribute="o">
				节目名称<form:input path="name"/><br>
				节目状态<form:input path="state"/><br>
				开播时间<form:input path="start"/><br>
				节目时长<form:input path="end"/><br>
				<input type="file" name="f"><br>
				<input type="submit" value="添加">
			</form:form>
		</div>
	</div>
</body>
</html>