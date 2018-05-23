<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>用户列表</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
		<table class="layui-table">
		<tr>
			<th>ID</th>
			<th>用户名</th>
			<th>角色</th>
			<th>操作</th>
		</tr>
		<c:forEach var="user" items="${userList}">
		<tr>
			<td>${user.userId}</td>
			<td>${user.userUsername}</td>
			<td>${user.userRoleList[0].roleName}</td>
			<td align="center">
				<a class="layui-btn" href="${pageContext.request.contextPath}/user/updateui/${user.userId}">修改</a>|
				<a style="margin-top:10px;" class="layui-btn" href="${pageContext.request.contextPath}/user/delete/${user.userId}">删除</a>|
				<a style="margin-top:10px;" class="layui-btn" href="${pageContext.request.contextPath}/privilege/userui/${user.userId}">分配权限</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div><a class="layui-btn" href="${pageContext.request.contextPath}/user/addui">添加</a></div>
    </div>
</body>
</html>