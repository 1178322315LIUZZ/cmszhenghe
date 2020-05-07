<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--??  -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript" src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid">
		<c:forEach items="${g }" var="c">
		<h6>${c.text }</h6>
		收藏时间:<fmt:formatDate value="${c.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
		<a href="javascript:del(${c.id })">删除</a>
		<hr>
		</c:forEach>
	</div>
	<script type="text/javascript">
	function del(idd) {
		$.post(
				"/deleteCollect",
				{id:idd},
				function(as) {
					if(as>0){
						alert("删除成功")
						location.reload();
					}else{
						alert("删除失败")
					}
				})
	}
	</script>
</body>
</html>