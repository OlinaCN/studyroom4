<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布公告</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Microsoft YaHei', Arial, sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* 页面高度填满视口 */
            padding: 20px;
        }

        .container {
            max-width: 800px;
            width: 100%;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .header {
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 20px;
            border-bottom: 2px solid #eee;
        }

        .header h1 {
            color: #2c3e50;
            font-size: 24px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #2c3e50;
            font-weight: bold;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }

        textarea.form-control {
            min-height: 200px;
            resize: vertical;
        }

        .btn {
            padding: 12px 24px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            color: white;
            transition: all 0.3s ease;
        }

        .btn-publish {
            background-color: #2ecc71;
        }

        .btn-publish:hover {
            background-color: #27ae60;
        }

        .btn-back {
            background-color: #95a5a6;
            text-decoration: none;
            display: inline-block;
            margin-right: 10px;
        }

        .btn-back:hover {
            background-color: #7f8c8d;
        }

        .button-group {
            margin-top: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>发布新公告</h1>
    </div>

    <form action="announcements" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label for="title">公告标题</label>
            <input type="text" id="title" name="title" class="form-control" required>
        </div>

        <div class="form-group">
            <label for="content">公告内容</label>
            <textarea id="content" name="content" class="form-control" required></textarea>
        </div>

        <div class="button-group">
            <a href="list.jsp" class="btn btn-back">返回</a>
            <button type="submit" class="btn btn-publish">发布公告</button>
        </div>
    </form>
</div>
</body>
</html>
