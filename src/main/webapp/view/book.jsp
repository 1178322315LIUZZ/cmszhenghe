<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--视窗  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fulid" style="background-color: red">
		<table>
			<tr>
			<td>头像</td>
			<td>
				<img alt="" src="" width="50px" height="50px">
				<input type="file" name="f">
			</td>
			</tr>
			<tr>
			<td>名称</td>
			<td>
			<input type="text" name="sname" onchange="zz(this.value)">
			<form:errors path="sname" cssStyle="color:red"></form:errors>
			</td>
			</tr>
			<tr>
			<td>密码</td>
			<td>
			<input type="text" name="pwd">
			<form:errors path="pwd" cssStyle="color:red"></form:errors>
			</td>
			</tr>
			<tr>
			<td>年龄</td>
			<td>
			<input type="text" name="age">
			<form:errors path="age" cssStyle="color:red"></form:errors>
			</td>
			</tr>
			<tr>
			<td>性别</td>
			<td>
			<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" value="女">女
			<form:errors path="sex" cssStyle="color:red"></form:errors>
			</td>
			</tr>
			<tr>
			<td>科目</td>
			<td>
			<span id="sp"></span>
			</td>
			</tr>
			<tr>
				<td colspan="10"><input type="submit" value="添加"></td>
			</tr>
		</table> 
	</div> 	
</body>
</html>