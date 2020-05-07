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
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px">节目详情</font>
			</div>
			<div class="col-md-12"
				style=" height: 34px ;margin-top: 15px">
				节目名称:${order.name }<br>
				节目状态:${order.state }<br>
				开始时间:${order.start }<br>
				节目时长:${order.end }<br>
				节目地址:<%-- <img alt="无" src="/pic/${order.addr }" style="width: 100px;height: 100px"><br> --%>
				<video id="video" controls="controls" width="640px" height="480" onclick="play()">
    				<source src="/pic/${order.addr }" type="video/mp4">
				</video>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function play(){
        var url = URL.createObjectURL(file);
        console.log(url);
        $("#video").attr("src",url);
   		 }
	</script>
</body>
</html>