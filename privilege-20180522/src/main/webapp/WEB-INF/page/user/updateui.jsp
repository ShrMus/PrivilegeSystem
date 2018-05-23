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
    <title>修改用户</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
			<form class="layui-form" id="form-updateuser" action="${pageContext.request.contextPath}/user/update" method="post">
			<input type="hidden" name="userId" value="${user.userId}"/>
			<table class="layui-table" lay-size="lg" width="100%">
				<%-- <tr>
					<td>角色</td>
					<td>
						<select name="roleId">
							<c:forEach var="role" items="${roleList}">
							<option value="${role.id}" 
							<c:if test="${user.userRoleList[0].id eq role.id}">selected="selected"</c:if>>
							${role.name}
							</option>
							</c:forEach>
						</select>
					</td>
				</tr> --%>
				<tr>
					<td>用户名</td>
					<td><input class="layui-input" type="text" name="userUsername" value="${user.userUsername}" readonly="readonly"/></td>
				</tr>
				
				<tr>
					<td>密　码</td>
					<td><input class="layui-input" type="password" name="password" value="${user.userPassword}"/></td>
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