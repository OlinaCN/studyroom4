<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>编辑自习室信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>编辑自习室信息</h1>
    <form action="studyroom/modify" method="post" class="edit-form">
        <div class="form-group">
            <label for="id">自习室ID：</label>
            <input type="text" id="id" name="id" value="${studyrooms.id}" readonly>
        </div>
        <div class="form-group">
            <label for="status">状态：</label>
            <select id="status" name="status">
                <option value="available" ${studyrooms.status == true ? 'selected' : ''}>可用</option>
                <option value="unavailable" ${studyrooms.status == false ? 'selected' : ''}>不可用</option>
            </select>
        </div>
        <div class="action-buttons">
            <button type="submit" class="btn btn-submit">提交</button>
            <a href="b_detail.jsp" class="btn btn-back">返回</a>
        </div>
    </form>
</div>
</body>
</html>
