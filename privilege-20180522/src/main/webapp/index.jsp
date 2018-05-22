<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>主页 - 斑点博客</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/dist/css/wangEditor.css"
	rel="stylesheet" type="text/css">
<style type="text/css">

</style>
</head>
<body>
	<jsp:forward page="/main"/>
	<%-- <div>
		<!-- begin -->
	<div>
		<jsp:include page="${pageContext.request.contextPath}/banner.jsp"/>
	</div>
		<!-- end -->
		
		<div>
			<span style="font-size:x-large;">最新文章</span>
		</div>
		
		<div>
			<c:forEach var="article" items="${articleList}">
			<div>
				<span>
					<a href="${pageContext.request.contextPath}/article/search/${article.articleId}">${article.articleTitle}</a>
				</span>
				<br>
				<span>
					${article.articleContent}
				</span>
				<div style="float:left;">
					<span style="margin-right:1.5rem">
					发表时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${article.articleUpdateTime}"/>&nbsp;&nbsp;&nbsp;
					</span>
					<span style="margin-right:1.5rem">
					阅读量：${article.articleCountClick}
					</span>
				</div>
					<div style="margin-left:auto;">
						<a href="${pageContext.request.contextPath}/article/updateui/${article.articleId}">编辑</a>&nbsp;
						<a href="${pageContext.request.contextPath}/article/nocomment/${article.articleId}">禁止评论</a>&nbsp;
						<a href="${pageContext.request.contextPath}/article/top/${article.articleId}/1">置顶</a>&nbsp;
						<a href="${pageContext.request.contextPath}/article/delete/${article.articleId}">删除</a>&nbsp;
					</div>
			</div>
			<br>
			<hr>
		</c:forEach>
		</div>
	</div> --%>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$("#search-button").click(function(){
		var qString = $("#qstring-input").val();
		window.location.href="${pageContext.request.contextPath}/article/index/search?q="+qString;
	});
</script>
</html>