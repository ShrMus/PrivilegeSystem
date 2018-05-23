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
    <title>修改角色</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
		<form action="${pageContext.request.contextPath}/role/update" method="post">
			<table class="layui-table" lay-size="lg" width="100%">
				<tr>
					<td>ID</td>
					<td><input class="layui-input" type="text" name="roleId" value="${role.roleId}" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>角色名</td>
					<td><input class="layui-input" type="text" name="roleName" value="${role.roleName}"/></td>
				</tr>
				<tr>
					<td>描述</td>
					<td><input class="layui-input" type="text" name="roleDescription" value="${role.roleDescription}"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input class="layui-btn" type="submit" value="修改" />
					<input class="layui-btn" type="reset" value="取消" />
					</td>
				</tr>
			</table>
		</form>
    </div>
</body>
</html>