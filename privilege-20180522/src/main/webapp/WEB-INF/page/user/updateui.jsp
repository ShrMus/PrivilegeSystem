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
			<table class="layui-table">
				<tr>
					<td>角色</td>
					<td>
						<c:forEach var="role" items="${roleList}">
							<label><input type="checkbox" name="roleId" value="${role.roleId}" 
							<c:if test="${fn:contains(userRoleIdList,role.roleId)}">checked</c:if>
							/>${role.roleName}</label><br>
						</c:forEach>
						<span style="color:red;">选择角色这里用js控制至少选择一个角色</span>
					</td>
				</tr>
				<tr>
					<td>用户名</td>
					<td>
						<input type="hidden" name="userId" value="${userinfo.userId}"/>
						<input class="layui-input" type="text" name="userUsername" value="${userinfo.userUsername}" readonly="readonly"/>
					</td>
				</tr>
				
				<tr>
					<td>密　码</td>
					<td><input class="layui-input" type="password" name="userPassword" value="${userinfo.userPassword}"/></td>
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