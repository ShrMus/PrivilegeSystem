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
    <title>权限管理系统</title>
</head>
<body>
	<jsp:include page="/banner.jsp"/>
    <div class="blog-body">
       	<form action="${pageContext.request.contextPath}/user/login" method="post">
       		<%-- <div><span style="color:red;">${sessionScope['org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS'][0]['message1']}</span></div> --%>
       		<div><span style="color:red;">${param.message}</span></div>
       		<table class="layui-table" lay-size="lg">
       			<tr>
       				<td>用户名</td>
       				<td><input type="text" name="userUsername" required="required" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input"></td>
       			</tr>
       			<tr>
       				<td>密码</td>
       				<td><input type="password" name="userPassword" required="required" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input"></td>
       			</tr>
       			<tr>
       				<td colspan="2" align="center">
       					<input class="layui-btn layui-btn-primary" type="submit" value="登录">
       				</td>
       			</tr>
       		</table>
       	</form>
    </div>
</body>
</html>