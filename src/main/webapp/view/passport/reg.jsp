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
<title>注册</title>
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
	<h1>${err }</h1>
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
				<label for="repassword">确认密码</label> <input id="repassword"
					type="password" class="form-control" name="repassword">
			</div>
			<div class="form-group form-inline">
				<label for="gender">性别</label> <input id="gender" type="radio"
					class="form-check-input" name="gender" value="1" checked>男 <input
					id="gender" type="radio" class="form-check-input" name="gender"
					value="0">女
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-info">注册</button>
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
				 repassword:{//确认密码和密码一致
					 equalTo:"#password"
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
					 repassword:{
						 equalTo:"两次密码不一致",
					 }, 
				 },submitHandler:function(flag){
					 //如果校验通过。则执行注册
					 $.post("/passport/login",$("#form1").serialize(),function(result){
						 if(result.code==200){//注册成功。跳转到登录页面
							 $("#title").html("<font color='red'>恭喜,注册成功,请登录</font>")
							 $("#passport").load("/passport/deng")
							 
						 }else{
							 alert(result.msg);
						 }
					 })
				 }  
		  }) 
	  })
	</script>
</body>
</html>