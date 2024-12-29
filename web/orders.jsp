<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>正在进行的订单</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>正在进行的订单</h1>
    <table class="history-table">
        <thead>
        <tr>
            <th>序号</th>
            <th>用户名</th>
            <th>自习室ID</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="order" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${order.user_name}</td>
                <td>${order.studyroom_id}</td>
                <td>
                    <form action="function/end" method="post" style="display: inline;">
                        <input type="hidden" name="username" value="${order.user_name}">
                        <input type="hidden" name="studyroom_id" value="${order.studyroom_id}">
                        <button type="submit" class="end-button">结束订单</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="footer">
        <a href="function/show" class="button">返回</a>
    </div>
</div>
</body>
</html>
