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
<title>今日头条</title>
<%-- <link href="<%=request.getContextPath()%>/css/index3.css" rel="stylesheet">  --%>
<link rel="stylesheet" type="text/css" href="/view/common/index.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<link rel="stylesheet" type="text/css"
	href="/bootstrap-4.4.1-dist/css/bootstrap.min.css" />
<script type="text/javascript" src="/js/popper.min.js"></script>
<script type="text/javascript"
	src="/bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- head -->
		<div class="row">
			<div class="col-md-12"
				style="background-color: #222222; height: 34px">
				<font color="white" size="2px" style="margin-left: 10px">下载APP</font>
				<div style="float: right;">
				<c:if test="${null==sessionScope.user}">
						<button type="button" class="btn btn-link" onclick="reg()"
							data-toggle="modal" data-target="#exampleModal"><font color="white" size="2px">注册</font></button>
						<button type="button" class="btn btn-link" onclick="login()"
							data-toggle="modal" data-target="#exampleModal"><font color="white" size="2px">登录</font></button>
				</c:if>
				<c:if test="${null !=sessionScope.user}">
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-link dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">	<font color="white" size="2px">登录信息</font></button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">${sessionScope.user.username }</a> <a
									class="dropdown-item" href="/my">个人中心</a> <a
									class="dropdown-item" href="/passport/logout">注销</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-2" style="padding-top: 10px">
				<ul>
					<li><a class="channel" onclick="shua()"><img alt=""
							src="/view/common/logo-index.png"
							style="width: 108px; height: 27px; margin-bottom: 5px">
					</a></li>
					<li><a href="/?hot=1 "
						class="channel-item ${article.channelId==channel.id?'active':'' }">热点</a></li>
					<c:forEach items="${channels }" var="channel" varStatus="i">
						<li><a href="/?channelId=${channel.id} "
							class="channel-item ${article.channelId==channel.id?'active':'' }">${channel.name }</a></li>
					</c:forEach>
				</ul>
					<div class="card-header">问卷调查</div>
					<div class="card-body">
						<!-- 问卷调查 --10篇 -->
						<c:forEach items="${ll}" var="voteArticle">
							<div class="media">
							
								<div class="media-body">
									<p><a href="/voteDetail?id=${voteArticle.id }" target="_blank">${voteArticle.title }</a></p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
			</div>
			<div class="col-md-7">
				<c:if test="${article.channelId!=null }">
					<div class="subchannel">
						<ul>
							<li
								class="sub-item ${article.categoryId==null?'sub-selected':'' }"><a
								href="/?channelId=${article.channelId }">全部</a></li>
							<c:forEach items="${categorys}" var="category">
								<li
									class="sub-item ${article.categoryId==category.id?'sub-selected':'' }"><a
									href="/?channelId=${article.channelId}&categoryId=${category.id}">${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
					<!-- ==============================搜索框========================== -->
							<form action="${pageContext.request.contextPath }/search" method="get">
								<div class="input-group mb-3">
									<input type="text" name="key" value="${key}"
										class="form-control" placeholder="从es中搜索东西!!"
										aria-label="Recipient's username"
										aria-describedby="button-addon2">
									<div class="input-group-append">
										<button class="btn btn-outline-secondary" id="button-addon2">搜索</button>
									</div>
								</div>
							</form>
					<!-- ==========================搜索框end========================= -->
				
				<!--遍历轮播图  -->
				<c:if test="${article.channelId==null }">
					<div id="carouselExampleCaptions" class="carousel slide"
						data-ride="carousel" style="margin-top: 5px">
						<ol class="carousel-indicators">
							<c:forEach items="${li }" var="li" varStatus="i">
								<li data-target="#carouselExampleCaptions"
									data-slide-to="${i.index }" class="active"></li>
							</c:forEach>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${li }" var="li" varStatus="i">
								<div class="carousel-item ${i.index==0?"active":"" }">
									<img src="/pic/${li.url }" class="d-block w-100" alt=""
										style="width: 175px; height: 400px">
									<div class="carousel-caption d-none d-md-block">
										<h5>${li.title }</h5>
									</div>
								</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleCaptions"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
							role="button" data-slide="next"> <span
							class="carousel-control-next-icon" aria-hidden="true"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</c:if>
				<div>
					<c:forEach items="${g }" var="c">
						<div class="media" style="margin-top: 5px">
							<img src="/pic/${c.picture }" class="mr-3" alt="..."
								width="100px" height="100px">
							<div class="media-body">
								<h5 class="mt-0">
									<a href="/show?id=${c.id }">${c.title }</a>
								</h5>
								作者:${c.user.username }&nbsp;&nbsp; 浏览量:${c.hits }
							</div>
						</div>
						<hr>
					</c:forEach>
					<jsp:include page="/view/common/pages.jsp"></jsp:include>
				</div>
			</div>
			<div class="col-md-3">
			<div class="card" style="width: 18rem; margin-top: 6px">
			
					<div class="card-header">节目单</div>
					<div class="card-body">
						<c:forEach items="${order}" var="order">
							<div class="media">
							
								<div class="media-body">
									<p><a href="/order?id=${order.id }" target="_blank">${order.name }</a></p>
								</div>
							</div>
							<hr>
						</c:forEach>
					</div>
				</div>
		
				<div class="card-header">最新文章</div>
				
					<c:forEach items="${ss }" var="ss">
						<img src="/pic/${ss.picture }" class="card-img-top" alt="..." style="width: 60px;height: 60px">
					<div class="card-body">
						<a href="/show?id=${ss.id }">${ss.title }</a>
					</div>
					</c:forEach>
			</div>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel"><span id="title">用户注册</span></h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body" id="passport">
					
					</div>
				
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function goPage(page) {
			var key = '${key}';
			var channelId = '${article.channelId}';//栏目ID
			var categoryId = '${article.categoryId}';//分类ID
			var hot = '${article.hot}';//热点
			if(key!=null){
				location= "/search?channelId=" + channelId + "&page=" + page+"&key="+key;
			}else{
				location = "/?page=" + page + "&channelId=" + channelId
				+ "&categoryId=" + categoryId + "&hot=" + hot
			}
		}
		//注册
		function reg() {
			$("#title").empty()
			$("#title").append("用户注册");
			$("#passport").load("/passport/reg");
		}
		//登录
		function login() {
			$("#title").empty()
			$("#title").append("用户登录");
			$("#passport").load("/passport/deng");
		}
		function shua() {
			location.replace("/?hot=1")
		}
	</script>
</body>
</html>