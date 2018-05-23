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
    <title>修改权限</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <!-- 主体（一般只改变这里的内容） -->
    <div class="blog-body">
		<form class="layui-form" action="${pageContext.request.contextPath}/privilege/update" method="post">
			<input type="hidden" name="privilegeId" value="${privilege.privilegeId}">
			<table class="layui-table" lay-size="lg" width="100%">
				<tr>
					<td>父权限</td>
					<td>
						<select name="privilegeId" lay-verify="">
							<option value="0">无</option>
							<c:forEach var="parentPrivilege" items="${parentPrivilegeList}">
							<option value="${parentPrivilege.privilegeId}" 
							<c:if test="${parentPrivilege.privilegeId eq privilege.privilegeParentId}">selected</c:if>>
							${parentPrivilege.privilegeName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>权限名称</td>
					<td><input type="text" name="privilegeName" value="${privilege.privilegeName}" class="layui-input"/></td>
				</tr>
				<tr>
					<td>权限URL</td>
					<td><input type="text" name="privilegeUrl" value="${privilege.privilegeUrl}" class="layui-input"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input class="layui-btn" type="submit" value="修改" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>