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
    <title>修改用户 - 斑点博客</title>
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
                <a><cite>修改用户</cite></a>
            </blockquote>
            <div class="blog-main">
            	<div class="child-nav shadow">
                    <span class="child-nav-btn child-nav-btn-this">修改用户</span>
                </div>
				<div class="shadow" style="text-align:left;font-size:16px;padding:40px 240px;background:#fff;margin-bottom:15px;">
						<form class="layui-form" id="form-updateuser" action="${pageContext.request.contextPath}/user/update" method="post">
						<input type="hidden" name="id" value="${user.id}"/>
						<input type="hidden" name="integral" value="${user.integral}"/>
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
								<td><input class="layui-input" type="text" name="username" value="${user.username}" readonly="readonly"/></td>
							</tr>
							
							<tr>
								<td>密　码</td>
								<td><input class="layui-input" type="password" name="password" value="${user.password}"/></td>
							</tr>
							
							<tr>
								<td>昵称</td>
								<td><input class="layui-input" type="text" name="nickname" value="${user.nickname}"/></td>
							</tr>
							<tr>
								<td>真实姓名</td>
								<td><input class="layui-input" type="text" name="realname" value="${user.realname}"/></td>
							</tr>
							
							<tr>
								<td>性别</td>
								<td>
									<select name="gender" lay-verify=""> 
										<option value="1" 
										<c:if test="${user.gender eq 1}">selected="selected"</c:if>>男</option>
										<option value="0" 
										<c:if test="${user.gender eq 0}">selected="selected"</c:if>>女</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<td>手机</td>
								<td><input class="layui-input" type="text" name="phone" value="${user.phone}"/></td>
							</tr>
							<tr>
								<td>邮箱</td>
								<td><input class="layui-input" type="text" name="email" value="${user.email}"/></td>
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
                <div class="clear"></div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/js/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/dist/js/lib/jquery-1.10.2.min.js"></script>
<!-- layui.js -->
<script src="${pageContext.request.contextPath}/plug/layui/layui.js"></script>
<!-- 全局脚本 -->
<script src="${pageContext.request.contextPath}/js/global.js"></script>
<!-- 本页脚本 -->
<script src="${pageContext.request.contextPath}/js/home.js"></script>
<script type="text/javascript">
	$(".button-updateuser").click(function(){
		$("#form-updateuser").submit();
	});
	$(".button-cancel").click(function(){
		var url = "${pageContext.request.contextPath}/main";
		window.location.href=url;
	});
</script>
</html>