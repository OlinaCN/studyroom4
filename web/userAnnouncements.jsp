<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>历史公告</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Microsoft YaHei', Arial, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            line-height: 1.6;
        }

        .container {
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
            padding: 20px 0;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .header h1 {
            color: #2c3e50;
            font-size: 24px;
            margin-bottom: 10px;
        }

        .announcements-container {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 20px;
        }

        .announcement-item {
            border-bottom: 1px solid #eee;
            padding: 20px;
            margin-bottom: 15px;
            transition: transform 0.2s ease;
        }

        .announcement-item:last-child {
            border-bottom: none;
        }

        .announcement-item:hover {
            transform: translateX(5px);
            background-color: #f8f9fa;
        }

        .announcement-title {
            font-size: 18px;
            color: #2c3e50;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .announcement-content {
            color: #666;
            font-size: 16px;
            margin-bottom: 10px;
            line-height: 1.6;
        }

        .announcement-time {
            color: #999;
            font-size: 14px;
            text-align: right;
        }

        .no-announcements {
            text-align: center;
            padding: 40px;
            color: #666;
            font-style: italic;
        }

        .back-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #2980b9;
        }

        .back-container {
            text-align: center;
            margin-top: 20px;
        }

        /* 添加响应式设计 */
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }

            .header h1 {
                font-size: 20px;
            }

            .announcement-item {
                padding: 15px;
            }

            .announcement-title {
                font-size: 16px;
            }

            .announcement-content {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>历史公告</h1>
    </div>

    <div class="announcements-container">
        <c:choose>
            <c:when test="${empty announcements}">
                <div class="no-announcements">
                    <p>暂无公告信息</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="announcement" items="${announcements}">
                    <div class="announcement-item">
                        <div class="announcement-title">
                                ${announcement.title}
                        </div>
                        <div class="announcement-content">
                                ${announcement.content}
                        </div>
                        <div class="announcement-time">
                            发布时间：${announcement.createdAt}
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="back-container">
        <a href="function/show" class="back-button">返回预约页面</a>
    </div>
</div>

<script>
    // 添加动画效果
    document.addEventListener('DOMContentLoaded', function() {
        const items = document.querySelectorAll('.announcement-item');
        items.forEach(item => {
            item.addEventListener('mouseenter', function() {
                this.style.transition = 'transform 0.2s ease';
            });
        });
    });
</script>
</body>
</html>