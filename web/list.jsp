<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List, studyroom.bean.Message" %>
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
            flex-direction: column; /* 主体采用纵向布局 */
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
            flex-grow: 1; /* 使文字在导航栏中间 */
            text-align: center; /* 使文字居中 */
        }

        .main-content {
            display: flex;
            margin-top: 60px; /* 给主内容区留出导航栏的高度 */
        }

        .sidebar {
            width: 250px;
            background-color: #34495e;
            color: white;
            display: flex;
            flex-direction: column;
            padding: 20px;
            position: fixed;
            top: 60px; /* 将侧边栏向下偏移，避开导航栏 */
            left: 0;
            height: calc(100vh - 60px); /* 确保侧边栏在导航栏下面显示 */
            z-index: 1;
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
            background-color: transparent; /* 默认透明 */
            color: white;
            border: none; /* 无边框 */
        }

        .sidebar a:hover,
        .sidebar form:hover {
            background-color: #2c3e50;
        }

        .sidebar form button {
            background: none; /* 移除按钮默认样式 */
            border: none;
            color: inherit; /* 使用父元素的文字颜色 */
            font: inherit; /* 使用父元素的字体 */
            cursor: pointer;
            width: 100%; /* 与父元素一致 */
            height: 100%; /* 与父元素一致 */
            text-align: center;
        }

        .content {
            flex-grow: 1;
            padding: 20px;
            margin-left: 250px; /* 给内容区域留出侧边栏的宽度 */
        }

        .announcement-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .announcement-content {
            flex-grow: 1;
        }

        .announcement-actions {
            display: flex;
            gap: 10px;
        }

        .announcement-actions form, .announcement-actions a {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 80px;
            height: 40px;
            margin: 0;
            padding: 0;
            border-radius: 4px;
            text-align: center;
        }

        .announcement-actions a {
            background-color: lightblue;
            border: 2px solid blue;
            color: blue;
        }

        .announcement-actions form button {
            background-color: lightgreen;
            border: 2px solid green;
            color: green;
            cursor: pointer;
            width: 100%;
            height: 100%;
        }

        .announcement-actions form button:hover, .announcement-actions a:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>管理员界面</h1>
    <p>欢迎管理员! 在这你可以管理用户、自习室还有订单</p>
</div>

<div class="main-content">
    <h2>管理员界面 - 留言管理</h2>

    <!-- 留言一览表 -->
    <table border="1" cellspacing="0" cellpadding="5">
        <tr>
            <th>编号</th>
            <th>用户ID</th>
            <th>内容</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        <%
            List<studyroom.bean.Message> messageList =
                    (List<studyroom.bean.Message>) request.getAttribute("messageList");
            if (messageList != null) {
                for (studyroom.bean.Message msg : messageList) {
        %>
        <tr>
            <td><%= msg.getId() %></td>
            <td><%= msg.getUserId() %></td>
            <td><%= msg.getContent() %></td>
            <td><%= msg.getCreateTime() %></td>
            <td>
                <!-- 编辑留言 -->
                <form action="<%= request.getContextPath() %>/MessageServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" value="<%= msg.getId() %>">
                    <input type="text" name="content" value="<%= msg.getContent() %>">
                    <input type="submit" value="修改">
                </form>

                <!-- 删除留言 -->
                <form action="<%= request.getContextPath() %>/MessageServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= msg.getId() %>">
                    <input type="submit" value="删除">
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <!-- 跳转到公告功能或其他功能的按钮，示例： -->
    <p><a href="<%= request.getContextPath() %>/listAnnouncement.jsp">查看公告</a></p>
    <p><a href="<%= request.getContextPath() %>/someOtherPage.jsp">其他功能</a></p>
    <div class="sidebar">
        <a href="addAnnouncement.jsp" class="btn-add">发布公告</a>
        <!-- 使用 form 保留功能，样式与 a 保持一致 -->
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
        <div id="announcements">
            <ul>
                <c:forEach var="announcement" items="${announcements}">
                    <li class="announcement-item">
                        <div class="announcement-content">${announcement.title}: ${announcement.content}</div>
                        <div class="announcement-actions">
                            <a href="editAnnouncement.jsp?id=${announcement.id}">编辑</a>
                            <form action="announcements" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${announcement.id}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit">删除</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
