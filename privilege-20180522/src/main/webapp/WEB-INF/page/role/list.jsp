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
    <title>角色列表</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
		<table class="layui-table">
			<tr>
				<th>ID</th>
				<th>角色名</th>
				<th>描述</th>
				<th>操作</th>
			</tr>
			<c:forEach var="role" items="${roleList}">
			<tr>
				<td>${role.roleId}</td>
				<td>${role.roleName}</td>
				<td>${role.roleDescription}</td>
				<td>
					<a class="layui-btn" href="${pageContext.request.contextPath}/role/updateui/${role.roleId}">修改</a>|
					<a class="layui-btn" href="${pageContext.request.contextPath}/role/delete/${role.roleId}">删除</a>|
					<a class="layui-btn" href="${pageContext.request.contextPath}/privilege/roleui/${role.roleId}">分配权限</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td><a class="layui-btn" href="${pageContext.request.contextPath}/role/addui">添加</a></td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
function addPrivilege(){
	var url = "${pageContext.request.contextPath}/privilege/addui";
	window.location.href = url;
}
</script>
</html>