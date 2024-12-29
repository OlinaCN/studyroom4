<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>编辑留言</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <style>
    .message-form {
      width: 80%;
      margin: 20px auto;
      padding: 20px;
      background-color: #f5f5f5;
      border-radius: 5px;
    }
    .message-form textarea {
      width: 100%;
      min-height: 100px;
      margin: 10px 0;
      padding: 10px;
      border: 1px solid #ddd;
      border-radius: 4px;
    }
    .button-group {
      margin-top: 15px;
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
      background-color: #4CAF50;
      color: white;
    }
    .cancel-btn {
      background-color: #f44336;
      color: white;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>编辑留言</h2>
  <div class="message-form">
    <form action="${pageContext.request.contextPath}/user/messages" method="post">
      <input type="hidden" name="action" value="update">
      <input type="hidden" name="id" value="${message.id}">
      <div class="form-group">
        <label for="content">留言内容：</label>
        <textarea id="content" name="content" required>${message.content}</textarea>
      </div>
      <div class="form-group">
        <button type="submit" class="btn btn-primary">保存修改</button>
        <a href="${pageContext.request.contextPath}/user/messages" class="btn btn-secondary">返回</a>
      </div>
    </form>
  </div>
</div>
</body>
</html>