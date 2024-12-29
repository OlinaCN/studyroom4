<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>自习室</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="container">
    <h1>欢迎来到自习室系统</h1>
    <p>欢迎登录并预约自习室进行学习</p>
    <form action="user/login" method="POST">
        <input type="text" name="username" placeholder="请输入你的账户" required>
        <input type="password" name="password" placeholder="请输入你的密码" required>
        <input type="checkbox" name="f" value="1">七天内免登录<br>
        <button type="submit">登录</button>
        <button type="submit">注册</button>

    </form>
</div>
</body>
</html>
