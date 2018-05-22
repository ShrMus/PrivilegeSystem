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
    <title>用户列表 - 斑点博客</title>
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
                <a><cite>用户列表</cite></a>
            </blockquote>
            <div class="blog-main">
            	<div class="child-nav shadow">
                    <span class="child-nav-btn child-nav-btn-this">查看</span>
                </div>
				<div class="shadow" style="text-align:left;font-size:16px;padding:40px 0px;background:#fff;margin-bottom:15px;">
					<table class="layui-table" lay-size="lg" width="100%">
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>角色</th>
						<th>头像</th>
						<th>昵称</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>手机</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
					<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.userRoleList[0].name}</td>
						<td><img src="${user.icon}"/></td>
						<td>${user.nickname}</td>
						<td>${user.realname}</td>
						<td>${user.gender eq 1?'男':'女'}</td>
						<td>${user.phone}</td>
						<td>${user.email}</td>
						<td align="center">
							<a class="layui-btn" href="${pageContext.request.contextPath}/user/updateui/${user.id}">修改</a><br>
							<%-- <a style="margin-top:10px;" class="layui-btn" href="${pageContext.request.contextPath}/user/delete/${user.id}">删除</a><br> --%>
							<a style="margin-top:10px;" class="layui-btn" href="${pageContext.request.contextPath}/privilege/userui/${user.id}">分配权限</a><br>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div><a class="layui-btn" href="${pageContext.request.contextPath}/user/addui">添加</a></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/js/lib/jquery-1.10.2.min.js"></script>
<!-- layui.js -->
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<!-- 本页脚本 -->
<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script type="text/javascript">
</script>
</html>