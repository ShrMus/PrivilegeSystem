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
    <title>权限列表</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
	<div>
		<button class="layui-btn" onclick="addPrivilege()">添加权限</button><br><br>
		<ul>
			<c:forEach var="parentPrivile" items="${parentPrivilegeList}">
				<li>
					<span><b>${parentPrivile.privilegeName}</b></span>
					<a class="layui-btn" href="${pageContext.request.contextPath}/privilege/updateui/${parentPrivile.privilegeId}">修改</a>|
					<a class="layui-btn" href="${pageContext.request.contextPath}/privilege/delete/${parentPrivile.privilegeId}">删除</a>
				</li>
				<br>
					<ul style="margin-left:30px;text-align:left;">
					<c:forEach var="childPrivilege" items="${parentPrivile.childPrivilegeList}">
						<li>
							<span>${childPrivilege.privilegeName}</span>
							<a class="layui-btn" href="${pageContext.request.contextPath}/privilege/updateui/${childPrivilege.privilegeId}">修改</a>|
							<a class="layui-btn" href="${pageContext.request.contextPath}/privilege/delete/${childPrivilege.privilegeId}">删除</a>
						</li>
						<br>
					</c:forEach>
					</ul>
			</c:forEach>
		</ul>
	</div>
</body>
<script type="text/javascript">
function addPrivilege(){
	var url = "${pageContext.request.contextPath}/privilege/addui";
	window.location.href = url;
}
</script>
</html>