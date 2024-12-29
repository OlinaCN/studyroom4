<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="studyroom.model.Announcement"%>
<%@ page import="studyroom.service.AnnouncementService"%>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  AnnouncementService service = new AnnouncementService();
  Announcement announcement = service.getAnnouncementById(id);
%>
<!DOCTYPE html>
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>编辑公告</title>
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
      padding: 20px;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    .header {
      text-align: center;
      margin-bottom: 30px;
      padding-bottom: 20px;
      border-bottom: 2px solid #eee;
    }

    .header h2 {
      color: #2c3e50;
      font-size: 24px;
    }

    .edit-form {
      margin-top: 20px;
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
      transition: border-color 0.3s ease;
    }

    .form-control:focus {
      outline: none;
      border-color: #3498db;
      box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
    }

    textarea.form-control {
      min-height: 200px;
      resize: vertical;
    }

    .button-group {
      display: flex;
      gap: 10px;
      margin-top: 30px;
      justify-content: flex-end;
    }

    .btn {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      transition: all 0.3s ease;
    }

    .btn-primary {
      background-color: #3498db;
      color: white;
    }

    .btn-secondary {
      background-color: #95a5a6;
      color: white;
    }

    .btn:hover {
      opacity: 0.9;
      transform: translateY(-1px);
    }

    .btn:active {
      transform: translateY(0);
    }

    @media (max-width: 768px) {
      .container {
        padding: 20px;
      }

      .header h2 {
        font-size: 20px;
      }

      .form-control {
        font-size: 14px;
      }

      .btn {
        padding: 8px 16px;
        font-size: 14px;
      }
    }
  </style>
</head>
<body>
<div class="container">
  <div class="header">
    <h2>编辑公告</h2>
  </div>

  <form class="edit-form" action="announcements" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= announcement.getId() %>">

    <div class="form-group">
      <label for="title">公告标题</label>
      <input type="text"
             id="title"
             name="title"
             class="form-control"
             value="<%= announcement.getTitle() %>"
             required>
    </div>

    <div class="form-group">
      <label for="content">公告内容</label>
      <textarea id="content"
                name="content"
                class="form-control"
                required><%= announcement.getContent() %></textarea>
    </div>

    <div class="button-group">
      <a href="javascript:history.back()" class="btn btn-secondary">取消</a>
      <button type="submit" class="btn btn-primary">保存更新</button>
    </div>
  </form>
</div>

<script>
  // 自动调整文本框高度
  const textarea = document.querySelector('textarea');
  textarea.addEventListener('input', function() {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
  });

  // 表单提交前验证
  document.querySelector('form').addEventListener('submit', function(e) {
    const title = document.getElementById('title').value.trim();
    const content = document.getElementById('content').value.trim();

    if (!title || !content) {
      e.preventDefault();
      alert('标题和内容都不能为空！');
    }
  });
</script>
</body>
</html>