<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .navbar {
            width: 100%;
            background-color: #2c3e50;
            color: white;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            z-index: 2;
        }

        .navbar h1 {
            margin: 0;
            font-size: 1.5em;
        }

        .navbar p {
            margin: 0;
            font-size: 1em;
            flex-grow: 1;
            text-align: center;
        }

        .main-content {
            display: flex;
            margin-top: 60px;
        }

        .sidebar {
            width: 250px;
            background-color: #34495e;
            color: white;
            display: flex;
            flex-direction: column;
            padding: 20px;
            position: fixed;
            top: 60px;
            left: 0;
            height: calc(100vh - 60px);
            z-index: 1;
            overflow-y: auto;
        }

        .sidebar a,
        .sidebar form {
            text-decoration: none;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 4px;
            transition: background 0.3s;
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            text-align: center;
            background-color: transparent;
            color: white;
            border: none;
        }

        .sidebar a:hover,
        .sidebar form:hover {
            background-color: #2c3e50;
        }

        .sidebar form button {
            background: none;
            border: none;
            color: inherit;
            font: inherit;
            cursor: pointer;
            width: 100%;
            height: 100%;
            text-align: center;
        }

        .content {
            flex-grow: 1;
            padding: 20px;
            margin-left: 250px;
        }

        /* 公告部分样式 */
        #announcements {
            margin-bottom: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
        }

        #announcements h2 {
            margin-bottom: 20px;
            color: #2c3e50;
        }

        .announcement-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 15px;
            border-bottom: 1px solid #eee;
        }

        .announcement-content {
            flex-grow: 1;
            margin-right: 20px;
        }

        .announcement-actions {
            display: flex;
            gap: 10px;
        }

        .announcement-actions a,
        .announcement-actions button {
            padding: 8px 15px;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            border: none;
            font-size: 14px;
            transition: opacity 0.3s;
        }

        .announcement-actions a {
            background-color: #3498db;
            color: white;
        }

        .announcement-actions button {
            background-color: #e74c3c;
            color: white;
        }

        /* 留言管理部分样式 */
        .admin-message-section {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
            margin-top: 20px;
        }

        .admin-message-section h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        .admin-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        .admin-table th,
        .admin-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        .admin-table th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #2c3e50;
        }

        .admin-table tr:hover {
            background-color: #f8f9fa;
        }

        .admin-table .edit-btn,
        .admin-table .delete-btn {
            padding: 6px 12px;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
            border: none;
            font-size: 14px;
            margin-right: 5px;
        }

        .admin-table .edit-btn {
            background-color: #3498db;
            color: white;
        }

        .admin-table .delete-btn {
            background-color: #e74c3c;
            color: white;
        }

        .admin-table .actions {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>管理员界面</h1>
    <p>欢迎管理员! 在这你可以管理用户、自习室、公告和留言</p>
</div>

<div class="main-content">
    <div class="sidebar">
        <a href="addAnnouncement.jsp" class="btn-add">发布公告</a>
        <form action="announcements" method="post">
            <button type="submit">管理公告</button>
        </form>
        <a href="addUser.jsp">添加用户</a>
        <a href="admin/detail">查看所有用户</a>
        <a href="studyroom/add">添加自习室</a>
        <a href="studyroom/detail">查看所有自习室</a>
        <a href="order/detail">查看所有订单</a>
    </div>

    <div class="content">
        <!-- 公告管理部分 -->
        <div id="announcements">
            <h2>公告管理</h2>
            <ul>
                <c:forEach var="announcement" items="${announcements}">
                    <li class="announcement-item">
                        <div class="announcement-content">
                            <strong>${announcement.title}</strong>: ${announcement.content}
                            <div class="announcement-time">
                                <fmt:formatDate value="${announcement.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </div>
                        </div>
                        <div class="announcement-actions">
                            <a href="editAnnouncement.jsp?id=${announcement.id}">编辑</a>
                            <form action="announcements" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${announcement.id}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" onclick="return confirm('确定要删除这条公告吗？')">删除</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- 留言管理部分 -->
        <div class="admin-message-section">
            <h2>留言管理</h2>
            <div class="message-list">
                <table class="admin-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>作者</th>
                        <th>内容</th>
                        <th>发布时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="message" items="${messages}">
                        <tr>
                            <td>${message.id}</td>
                            <td>${message.username}</td>
                            <td>${message.content}</td>
                            <td><fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="actions">
                                <a href="${pageContext.request.contextPath}/edit-message?id=${message.id}"
                                   class="edit-btn">编辑</a>
                                <form action="${pageContext.request.contextPath}/admin/messages"
                                      method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${message.id}">
                                    <button type="submit" class="delete-btn"
                                            onclick="return confirm('确定要删除这条留言吗？')">删除</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>