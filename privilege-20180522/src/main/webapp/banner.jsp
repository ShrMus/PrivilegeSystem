<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导航</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" />
<body>

<div style="width:100%;">
	<div>
		<ul>
			<li><span style="color:black;"><b>权限管理系统</b></span>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/main">首页</a>&nbsp;</li>
			<%-- <li><a href="${pageContext.request.contextPath}/privilege/list">权限管理</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/role/list">角色管理</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/user/list">用户管理</a>&nbsp;</li> --%>
			
			<c:if test="${!empty sessionScope.user.privilegeList}">
				<c:forEach var="privilege" items="${sessionScope.user.privilegeList}">
			        <c:if test="${privilege.privilegeParentId eq 0}">
			        <li class="layui-nav-item">
			            <a href="${pageContext.request.contextPath}${privilege.privilegeUrl}">&nbsp;${privilege.privilegeName}&nbsp;</a>
			        </li>
			        </c:if>
		        </c:forEach>
        	</c:if>
		</ul>
	</div>
	<div>
		<ul>
			<li><c:if test="${empty sessionScope.user}">
					<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
					${sessionScope.user.userUsername}&nbsp;
					<%-- <a href="${pageContext.request.contextPath}/user/info/${sessionScope.user.userId}">${sessionScope.user.userUsername}</a>&nbsp; --%>
					<a href="${pageContext.request.contextPath}/user/logout">退出</a>
				</c:if>
			</li>
		</ul>
	</div>
	<br>
	<hr>
</div>

</body>
</html>