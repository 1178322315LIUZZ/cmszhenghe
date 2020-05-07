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
<title>Insert title here</title>
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
	<c:forEach items="${g }" var="c">
		<div class="media" style="margin-top: 5px">
			<img src="/pic/${c.picture }" class="mr-3" alt="..." width="100px" height="100px">
			<div class="media-body">
				<h5 class="mt-0">${c.title }</h5>
			</div>
			<div style="margin-top: 30px">
				<!-- Button trigger modal -->
			 <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" onclick="look(${c.id})">
  				详情
			</button> 
			<%-- <a href="/show?id=${c.id }">详情</a> --%>
			</div>
		</div>
		<hr>
	</c:forEach>
	<jsp:include page="/view/common/pages.jsp"></jsp:include>
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><span id="title"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="content">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
	function goPage(page) {
		$("#centers").load("/my/articles?page="+page)
	}
	 function look(id) {
		$.post(
				"/chaxun/articlecha",
				{idd:id},
				function (a) {
					$("#content").empty()
					$("#title").empty()
					$("#title").append(a.title)
					$("#content").append(a.content)
				})
	} 
</script>
</html>