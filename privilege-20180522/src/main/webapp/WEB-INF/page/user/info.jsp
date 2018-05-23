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
    <title>用户信息 - 斑点博客</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/Logo_40.png" type="image/x-icon">
    <!--Layui-->
    <link href="${pageContext.request.contextPath}/plug/layui/css/layui.css" rel="stylesheet" />
    <!--font-awesome-->
    <link href="${pageContext.request.contextPath}/plug/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <!--全局样式表-->
    <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" />
    <!--本页样式表-->
    <link href="${pageContext.request.contextPath}/css/article.css" rel="stylesheet" />
</head>
<body>
    <jsp:include page="/banner.jsp"/>
    <!-- 主体（一般只改变这里的内容） -->
    <div class="blog-body">
        <div class="blog-container">
            <blockquote class="layui-elem-quote sitemap layui-breadcrumb shadow">
                <a href="${pageContext.request.contextPath}/main" title="首页">首页</a>
                <a><cite>用户信息</cite></a>
            </blockquote>
            <div class="blog-main">
            	<div class="child-nav shadow">
                    <span class="child-nav-btn child-nav-btn-this">用户信息</span>
                    <!-- <span class="child-nav-btn">源码</span>
                    <span class="child-nav-btn">工具</span>
                    <span class="child-nav-btn">电子书</span> -->
                </div>
                <div class="blog-main-left">
                    <div class="shadow" style="text-align:center;font-size:16px;padding:40px 15px;background:#fff;margin-bottom:15px;height:600px;">
	                    <div style="float:right;width:90%;">
							<input class="input-userId" type="hidden" value="${requestScope.user.id}"/>
							<%-- <object height="100%" width="100%" type="text/x-scriptlet" data="${pageContext.request.contextPath}/article/list"> --%>
							<%-- <iframe class="iframe-src" src="${pageContext.request.contextPath}/user/info/${requestScope.user.id}" width="100%" height="1000px" scrolling="auto" frameborder="0"></iframe> --%>
							<table class="layui-table" lay-size="lg" width="100%">
								<tr>
									<td>真实姓名</td>
									<td>${user.realname}</td>
								</tr>
								<tr>
									<td>性别</td>
									<td>${user.gender eq 1?'男':'女'}</td>
								</tr>
								
								<tr>
									<td>手机</td>
									<td>${user.phone}</td>
								</tr>
								<tr>
									<td>邮箱</td>
									<td>${user.email}</td>
								</tr>
								<tr>
									<td>积分</td>
									<td>${user.integral}</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
									<c:if test="${sessionScope.user.id eq requestScope.user.id}">
									<a class="layui-btn" href="${pageContext.request.contextPath}/user/updateui/${user.id}">编辑个人信息</a>
									</c:if>
									<a class="layui-btn" href="${pageContext.request.contextPath}/main">返回</a>
									</td>
								</tr>
							</table>
						</div>
                    </div>
                </div>
                <div class="blog-main-right">
                    <div class="article-category shadow">
                        <div class="article-category-title">想要查看</div>
                        <a href="${pageContext.request.contextPath}/article/user${requestScope.user.id}" target="_blank">博客</a>
                        <a href="${pageContext.request.contextPath}/album/userinfo${requestScope.user.id}" target="_blank">相册</a>
                        <a href="${pageContext.request.contextPath}/message/user${requestScope.user.id}" target="_blank">留言</a>
                        <div class="clear"></div>
                    </div>
                    <!--右边悬浮 平板或手机设备显示-->
                    <div class="category-toggle"><i class="fa fa-chevron-left"></i></div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <!-- layui.js -->
    <script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
    <!-- 全局脚本 -->
    <script src="${pageContext.request.contextPath}/js/global.js"></script>
</body>
</html>