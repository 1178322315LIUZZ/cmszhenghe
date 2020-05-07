<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布文章</title>
<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content"]', {
				cssPath : '/kindeditor1/plugins/code/prettify.css',
				uploadJson : '/kindeditor1/jsp/upload_json.jsp',
				fileManagerJson : '/kindeditor1/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
 <script type="text/javascript">
//文档就绪函数
 $(function(){
 // 为栏目添加内容-- 栏目和分类 二级联动
   $.get("/chaxun/chan",function(list){
    for(var i in list){
 	     //为栏目添加option
 	   $("#channels").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
    }
    //为栏目添加绑定点击事件
    $("#channels").change(function(){
 	   //获取当前栏目ID
 	   var channelId =$(this).val();
 	   //先清空原有的分类
 	   $("#categorys").empty();
 		   
 	   //根据栏目ID 查询其下分类
 	   $.get("/chaxun/cate",{channelId:channelId},function(list){
 		 
 		   
 		   for(var i in list){
 			     //为分类添加option
 			   $("#categorys").append("<option value='"+list[i].id+"'>"+list[i].name+"</option>")
 		   }
 	   })
 	   
    })
 })
 })
 //发布文章
 function publishs(){
	 var formData = new FormData($("#form1")[0]);
	 //需要重新封装  从富文本编辑器中 的html的内容 
	 formData.set("content",editor1.html());
	 
	 $.ajax({
		 type:"post",
		 url:"/chaxun/fabu",
		 processData:false,
		 contentType:false,
		 data:formData,
		 success:function(flag){
			 if(flag){
				 alert("发布成功");
				 location.href="/my"
			 }
			 else{
				 alert("发布失败,请重新登陆后再试")
			 }
		 }
		 
	 })
	 
 }
 
 </script>
  
</head>
<body>
	<form id="form1">
		<div class="form-group">
			<label for="title">文章标题</label> <input id="title"
				class="form-control form-control-sm" type="text" name="title">
		</div>
		<div class="form-group">
			<label for="summary">文章摘要</label> <input id="summary"
				class="form-control form-control-sm" type="text" name="summary">
		</div>
		<div class="form-group">
			<label for="summary">关键字</label> <input id="keywords"
				class="form-control form-control-sm" type="text" name="keywords">
		</div>
		<div class="form-group">
			<label for="summary">来源</label> <input id="original"
				class="form-control form-control-sm" type="text" name="original">
		</div>
		<div class="form-group">
			<label for="file">标题图片</label> <input id="file"
				class="form-control-file form-control-sm" type="file" name="file2">
		</div>
		<div class="form-group form-inline">
			<label for="title">所属栏目：</label> 
			<select name="channelId"
				class="form-control form-control-sm" id="channels">
				<option value="">请选择</option>
			</select> <label for="title">所属分类：</label> <select name="categoryId"
				class="form-control form-control-sm" id="categorys">
				<option value="">请选择</option>
			</select>
		</div>

		<textarea name="content" cols="100" rows="8"
			style="width: 100%; height: 200px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br /> <input type="button" class="btn btn-info" name="button" value="提交内容"  onclick="publishs()" />
	</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
</html>