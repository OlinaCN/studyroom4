<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>自习室信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>自习室信息</h1>
    <!-- 自习室数据展示表 -->
    <table class="studyroom-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" varStatus="status" var="studyroom">
            <tr>
                <td>${status.count}</td>
                <td>
                    <span class="${studyroom.status == true ? 'status-available' : 'status-unavailable'}">
                            ${studyroom.status == true ? '可用' : '不可用'}
                    </span>
                </td>
                <td>
                    <a href="javascript:void(0)" onclick="del('${studyroom.id}')">删除</a>
                    <a href="${pageContext.request.contextPath}/studyroom/update?id=${studyroom.id}">修改</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="action-buttons">
        <a href="list.jsp" class="btn btn-back">返回</a>
    </div>
</div>
<script type='text/javascript'>
    function del(id){
        if(window.confirm('删除不可恢复！')){
            document.location.href='<%=request.getContextPath()%>/studyroom/delete?id='+id
        }
    }
</script>
</body>
</html>
