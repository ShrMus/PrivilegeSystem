<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/myel" prefix="myel"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; Charset=utf-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>分配权限 - 斑点博客</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/Logo_40.png" type="image/x-icon">
    <!--Layui-->
    <link href="${pageContext.request.contextPath}/plug/layui/css/layui.css" rel="stylesheet" />
    <!--font-awesome-->
    <link href="${pageContext.request.contextPath}/plug/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" />
    <!-- 本页样式表 -->
    <link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/about.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <!-- 主体（一般只改变这里的内容） -->
    <div class="blog-body">
        <!-- 这个一般才是真正的主体内容 -->
        <div class="blog-container">
        	<blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow">
                <a href="${pageContext.request.contextPath}/main" title="首页">首页</a>
                <a><cite>分配权限</cite></a>
            </blockquote>
            <div class="blog-main">
            	<div class="child-nav shadow">
                    <span class="child-nav-btn child-nav-btn-this">为${type eq 'role'?'角色':type eq 'user'?'用户':'角色'}：<c:if test="${type eq 'role'}">${object.name}</c:if><c:if test="${type eq 'user'}">${object.username}</c:if>分配权限</span>
                </div>
				<div class="shadow" style="text-align:left;font-size:16px;padding:40px 240px;background:#fff;margin-bottom:15px;">
					<form class="layui-form" action="${pageContext.request.contextPath}/privilege/${type}/${typeId}" method="post">
					<ul>
						<c:forEach var="privilege" items="${privilegeList}">
							<li>
								<input lay-skin="primary" name="privilegeIds" type="checkbox" value="${privilege.id}" 
								<%-- 已有的权限回显 --%>
								<%-- <c:forEach var="i" begin="0" end="${fn:length(object.privilegeList)}" items="${object.privilegeList}" step="1">
									<c:if test="${privilege.id eq i}">checked="checked"</c:if>
								</c:forEach> --%>
								<c:if test="${myel:contains(object.privilegeList,privilege)}">checked="checked"</c:if>
								/>
								${privilege.name}
							</li>
							<br>
								<ul style="margin-left:30px;">
									<c:forEach var="childPrivilege" items="${privilege.childPrivilegeList}">
										<li>
											<input lay-skin="primary" name="privilegeIds" type="checkbox" value="${childPrivilege.id}" 
											<%-- 已有的权限回显 --%>
											<%-- <c:forEach var="i" begin="0" end="${fn:length(object.privilegeList)}" items="${object.privilegeList}" step="1">
												<c:if test="${childPrivilege.id eq i}">checked="checked"</c:if>
											</c:forEach> --%>
											<c:if test="${myel:contains(object.privilegeList,childPrivilege)}">checked="checked"</c:if>
											/>
											${childPrivilege.name}
										</li>
										<br>
									</c:forEach>
								</ul>
						</c:forEach>
					</ul>
					<br>
					<input class="layui-btn" type="submit" value="提交"/>
					</form>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</body>
<!-- layui.js -->
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<!-- 本页脚本 -->
<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script type="text/javascript">
</script>
</html>