<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页 - 斑点博客</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/Logo_40.png" type="image/x-icon">
<!--Layui-->
<link href="${pageContext.request.contextPath}/plug/layui/css/layui.css" rel="stylesheet" />
<!--font-awesome-->
<link href="${pageContext.request.contextPath}/plug/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<!--全局样式表-->
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" />
<!-- 本页样式表 -->
<link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/resource.css" rel="stylesheet" />
<%-- <link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" /> --%>
</head>
<body>
<%-- 
<div style="width:100%;">
	<div>
		<ul>
			<li><span style="color:black;"><b>斑点博客</b></span>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/main">首页</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/user/userinfo/${sessionScope.user.id}">个人信息</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/article/list">博客</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/album/user${sessionScope.user.id}">相册</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/message/user${sessionScope.user.id}">留言</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/resource/list">资源库</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/question/list">问答</a>&nbsp;</li>
			<c:if test="${fn:contains(sessionScope.user.userRoleList[0].name,'管理员')}">
			<li><a href="${pageContext.request.contextPath}/articletype/list">文章类型管理</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/user/list">用户管理</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/role/list">角色管理</a>&nbsp;</li>
			<li><a href="${pageContext.request.contextPath}/privilege/list">权限管理</a>&nbsp;</li>
			</c:if>
		</ul>
	</div>
	<div>
		<ul>
			<li>
			<input id="qstring-input" type="text" name="keywords" value="${keywords}"/>
			<input id="search-button" type="button" value="搜索"/>
			</li>
			<li><c:if test="${empty sessionScope.user}">
					<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
					<a href="${pageContext.request.contextPath}/user/info/${sessionScope.user.id}">${sessionScope.user.nickname}</a>&nbsp;
					<a href="${pageContext.request.contextPath}/user/logout">退出</a>
				</c:if>
			</li>
		</ul>
	</div>
	<br>
	<hr>
</div> --%>

	<!-- 导航 -->
    <nav class="blog-nav layui-header">
        <div class="blog-container">
            <!-- QQ互联登陆 -->
            <c:if test="${empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/user/loginbyqqbefore" class="blog-user">
                <i class="fa fa-qq"></i>
            </a>
            </c:if>
            <c:if test="${!empty sessionScope.user}">
            <a href="javascript:;" class="blog-user layui-hide">
                <img src="${user.icon}" alt="${user.nickname}" title="${user.nickname}" />
            </a>
            </c:if>
            <c:if test="${empty sessionScope.user}">
				<a href="${pageContext.request.contextPath}/login.jsp" class="blog-user-login">登录</a>
			</c:if>
			<c:if test="${!empty sessionScope.user}">
				<a href="${pageContext.request.contextPath}/user/logout" class="blog-user-login">退出</a>
			</c:if>
            <!-- 斑点博客 -->
            <a class="blog-logo" href="${pageContext.request.contextPath}/main">斑点博客</a>
            <!-- 导航菜单 -->
            <ul class="layui-nav" lay-filter="nav">
                <%-- <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/main">&nbsp;首页</a>
                </li> --%>
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/article/list">&nbsp;文章专栏</a>
                </li>
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/resource/list">&nbsp;资源分享</a>
                </li>
                <li class="layui-nav-item">
                    <a href="${pageContext.request.contextPath}/question/list">&nbsp;问答</a>
                </li>
                <c:forEach var="privilege" items="${sessionScope.user.privilegeList}">
                <c:if test="${privilege.pid eq 0}">
                <li class="layui-nav-item">
               		<c:choose>
	                	<c:when test="${fn:contains(privilege.name,'留言')}">
                			<a href="${pageContext.request.contextPath}${privilege.url}${sessionScope.user.id}">&nbsp;${privilege.name}</a>
	                	</c:when>
	                	<c:when test="${fn:contains(privilege.name,'相册')}">
                			<a href="${pageContext.request.contextPath}${privilege.url}${sessionScope.user.id}">&nbsp;${privilege.name}</a>
	                	</c:when>
                		<c:otherwise>
		                    <a href="${pageContext.request.contextPath}${privilege.url}">&nbsp;${privilege.name}</a>
                		</c:otherwise>
               		</c:choose>
                </li>
                </c:if>
                </c:forEach>
            </ul>
            <!-- 手机和平板的导航开关 -->
            <a class="blog-navicon" href="javascript:;">
                <i class="fa fa-navicon"></i>
            </a>
        </div>
    </nav>
    
    <!--侧边导航-->
    <ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
        <li class="layui-nav-item layui-this">
            <a href="${pageContext.request.contextPath}/main"><i class="fa fa-home fa-fw"></i>&nbsp;首页</a>
        </li>
        <c:forEach var="privilege" items="${sessionScope.user.privilegeList}">
        <c:if test="${privilege.pid eq 0}">
        <li class="layui-nav-item">
            <a href="${pageContext.request.contextPath}${privilege.url}">&nbsp;${privilege.name}</a>
        </li>
        </c:if>
        </c:forEach>
    </ul>
</body>
<!-- layui.js -->
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<!-- 全局脚本 -->
<%-- <script src="${pageContext.request.contextPath}/js/global.js"></script> --%>
<!-- 本页脚本 -->
<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$("#search-button").click(function(){
		var qString = $("#qstring-input").val();
		window.location.href="${pageContext.request.contextPath}/article/index/search?keywords="+qString;
	});
</script>
</html>