<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>预约自习室</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* 保留原有样式 */
        .action-buttons {
            display: flex;
            gap: 10px;
            margin-top: 20px;
            flex-wrap: wrap;
        }

        .btn {
            padding: 8px 16px;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            border: none;
            font-size: 14px;
        }

        .btn-view-announcements {
            background-color: #17a2b8;
            color: white;
        }

        .btn-view-announcements:hover {
            background-color: #138496;
        }

        #announcements {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 5px;
        }

        #announcements ul {
            list-style: none;
            padding: 0;
        }

        #announcements li {
            margin-bottom: 10px;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        /* 留言板样式优化 */
        .message-section {
            margin: 30px 0;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        .message-section h3 {
            color: #333;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 2px solid #17a2b8;
        }

        .message-form {
            margin: 20px 0;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 6px;
        }

        .message-form textarea {
            width: 100%;
            min-height: 100px;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            resize: vertical;
            font-size: 14px;
        }

        .message-form button {
            background-color: #17a2b8;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .message-form button:hover {
            background-color: #138496;
        }

        .message-list {
            margin-top: 25px;
        }

        .message-item {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #f8f9fa;
            border-radius: 6px;
            border-left: 4px solid #17a2b8;
        }

        .message-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
            padding-bottom: 5px;
            border-bottom: 1px solid #eee;
        }

        .message-author {
            font-weight: bold;
            color: #333;
        }

        .message-time {
            color: #666;
            font-size: 0.9em;
        }

        .message-content {
            margin: 15px 0;
            line-height: 1.6;
            color: #444;
        }

        .message-actions {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 10px;
        }

        .edit-btn, .delete-btn {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 13px;
            text-decoration: none;
            transition: opacity 0.3s;
        }

        .edit-btn {
            background-color: #ffc107;
            color: #000;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        .edit-btn:hover, .delete-btn:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>预约自习室</h1>
    <!-- 保留原有的预约表单 -->
    <form action="function/deal" method="post" class="reserve-form">
        <div class="form-group">
            <label for="userName">用户名称：</label>
            <input type="text" id="userName" name="user_name" value="${user.name}" readonly>
            <span class="user-num">剩余次数：${user.num}</span>
        </div>
        <div class="form-group">
            <label for="studyroomId">选择自习室：</label>
            <select id="studyroomId" name="study_id">
                <c:forEach items="${studyrooms}" var="studyroom" varStatus="status">
                    <option value="${status.count}">
                        自习室ID: ${status.count} - 状态: ${studyroom.status == true ? '可用' : '不可用'}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="action-buttons">
            <button type="submit" class="btn btn-submit">提交预约</button>
            <a href="function/order" class="btn btn-history">查看正在进行的订单</a>
            <a href="user/announcements" class="btn btn-view-announcements">查看所有公告</a>
            <a href="user/exit" class="btn btn-back">退出登录</a>
        </div>
    </form>

    <!-- 优化后的留言板部分 -->
    <div class="message-section">
        <h3>留言板</h3>
        <div class="message-form">
            <form action="${pageContext.request.contextPath}/user/messages" method="post">
                <input type="hidden" name="action" value="add">
                <textarea name="content" placeholder="写下你的想法..." required></textarea>
                <button type="submit">发布留言</button>
            </form>
        </div>

        <div class="message-list">
            <c:forEach var="message" items="${messages}">
                <div class="message-item">
                    <div class="message-header">
                        <span class="message-author">${message.username}</span>
                        <span class="message-time">
                            <fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </span>
                    </div>
                    <div class="message-content">${message.content}</div>
                    <c:if test="${user.id == message.userId}">
                        <div class="message-actions">
                            <a href="${pageContext.request.contextPath}/edit-message?id=${message.id}"
                               class="edit-btn">编辑</a>
                            <form action="${pageContext.request.contextPath}/user/messages"
                                  method="post" style="display: inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${message.id}">
                                <button type="submit" class="delete-btn"
                                        onclick="return confirm('确定要删除这条留言吗？')">删除</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const flag = ${flag};
        if (!flag) {
            alert("余额不足，请充值后再预约！");
            const submitButton = document.querySelector('.btn-submit');
            if (submitButton) {
                submitButton.disabled = true;
            }
        }
    });
</script>
</body>
</html>