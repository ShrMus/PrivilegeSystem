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
    <title>添加用户</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
		<form class="layui-form" action="${pageContext.request.contextPath}/user/add" method="post">
			<table class="layui-table" lay-size="lg" width="100%">
				<tr>
					<td>角色</td>
					<td>
						<select name="roleId" lay-verify="">
							<c:forEach var="role" items="${roleList}">
							<option value="${role.roleId}" <c:if test="${role.roleId eq roleId}">selected</c:if>>${role.roleName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>用户名</td>
					<td><input class="layui-input" type="text" name="userUsername" value="${user.userUsername}"/><span style="color:red;">${requestScope.message}</span></td>
				</tr>
				<tr>
					<td>密　码</td>
					<td><input class="layui-input" type="password" name="userPassword" value="${user.userPassword}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input class="layui-btn" type="submit" value="添加" />
					<input class="layui-btn" type="reset" value="取消" />
					</td>
				</tr>
			</table>
		</form>
    </div>
</body>
</html>