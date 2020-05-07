<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--??  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<style type="text/css">
	.error{color:red}
</style>
</head>
<body>
	<div class="container">
		<h5 style="color: red" id="msg">${err }</h5>
		<form id="form1">
			<div class="form-group">
				<label for="username">用户名</label> <input id="username" type="text"
					class="form-control" name="username" onblur="yan()">
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input id="password"
					type="password" class="form-control" name="password">
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-info">登录</button>
				<button type="reset" class="btn btn-warning">重置</button>
			</div>

		</form>

	</div>
	
	<script type="text/javascript">
	  $(function(){
		  
		  $("#form1").validate({
			  
			  //1 .定义规则
			  rules:{
				 username:{
					 required:true,//用户名不能为空
					 rangelength:[2,10],//用户名长度在2-10之间
				 }, 
				 password:{
					 required:true,//密码不能为空
					 rangelength:[6,10],//密码长度在6-10之间
				 },
			  },
			  //2.定义消息提示
				 messages:{
					username:{
						 required:"用户名不能为空",
						 rangelength:"用户名长度在2-10之间",
					} , 
					 password:{
						 required:"密码不能为空",
						 rangelength:"密码长度在6-10之间"
					 }, 
				 },submitHandler:function(flag){
					 //如果校验通过。则执行注册
					 $.post("/passport/admindenglu",$("#form1").serialize(),function(result){
						 if(result.code==200){
								// $("#msg").html("<font color='red'>恭喜登录成功</font")
								 location.href="/admin";//刷新回到首页
							 }else{
								  $("#msg").html(result.msg) 
						 }
					 })
				 }  
		  })
	  })

	
	</script>
</body>
</html>