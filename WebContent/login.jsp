<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login.action" method="post">
		用户名：<input type="text" name="name" required="required" placeholder="请输入用户名">
		密码：<input type="password" name="password" required="required" placeholder="请输入密码">
		<input type="submit" value="登录">
	</form>
</body>
</html>