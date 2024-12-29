<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>管理员编辑留言</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <style>
    .admin-message-form {
      width: 80%;
      margin: 20px auto;
      padding: 20px;
      background-color: #f5f5f5;
      border-radius: 5px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .message-info {
      margin-bottom: 15px;
      padding: 10px;
      background-color: #e9ecef;
      border-radius: 4px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    textarea {
      width: 100%;
      min-height: 120px;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
      resize: vertical;
    }
    .button-group {
      margin-top: 20px;
      text-align: center;
    }
    .button-group button {
      margin: 0 10px;
      padding: 8px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .submit-btn {
      background-color: #28a745;
      color: white;
    }
    .cancel-btn {
      background-color: #dc3545;
      color: white;
    }
  </style>
</head>
<body>
<div class="container">
  <title>管理员编辑留言</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
  </head>
  <body>
  <div class="container">
    <h2>管理员编辑留言</h2>
    <form action="${pageContext.request.contextPath}/admin/messages" method="post">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="${message.id}">
      <div class="form-group">
        <label>留言作者：${message.username}</label>
      </div>
      <div class="form-group">
        <label for="content">留言内容：</label>
        <textarea id="content" name="content" required>${message.content}</textarea>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary">保存修改</button>
        <a href="${pageContext.request.contextPath}/admin/messages" class="btn btn-secondary">返回</a>
      </div>
    </form>
  </div>
</body>
</html>