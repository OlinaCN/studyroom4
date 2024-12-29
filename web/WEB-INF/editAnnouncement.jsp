<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="studyroom.model.Announcement"%>
<%@ page import="studyroom.service.AnnouncementService"%>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  AnnouncementService service = new AnnouncementService();
  Announcement announcement = service.getAnnouncementById(id);
%>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Announcement</title>
</head>
<body>
<h2>编辑公告</h2>
<form action="announcements" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="id" value="<%= announcement.getId() %>">
  标题: <input type="text" name="title" value="<%= announcement.getTitle() %>"><br>
  内容: <textarea name="content"><%= announcement.getContent() %></textarea><br>
  <button type="submit">更新</button>
</form>
</body>
</html>