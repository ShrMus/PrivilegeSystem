<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户日志 - 斑点博客</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<div>
	

	<div>
		<h1>用户操作日志</h1>
		<c:forEach var="userOperation" items="${userOperationList}">
			[<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${userOperation.time}"/>]：
			${userOperation.log}<br>
		</c:forEach>
	</div>
</div>
</body>
</html>