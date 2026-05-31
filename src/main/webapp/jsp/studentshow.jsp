<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentmanager.entity.Student" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>确认删除学生</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="page-container">
        <div class="page-header">
            <h1>确认删除学生</h1>
            <a href="ListStudentServlet.do" class="btn-back">返回列表</a>
        </div>

        <%
            Student s = (Student) request.getAttribute("student");
            if (s != null) {
        %>
        <div class="form-card">
            <div class="alert alert-warning">
                <strong>警告：</strong>您确定要删除以下学生信息吗？此操作不可撤销！
            </div>
            <table class="data-table">
                <tr><th style="width:100px;">ID</th><td><%= s.getId() %></td></tr>
                <tr><th>学号</th><td><%= s.getStudentNo() %></td></tr>
                <tr><th>姓名</th><td><%= s.getName() %></td></tr>
                <tr><th>性别</th><td><%= s.getGender() %></td></tr>
                <tr><th>年龄</th><td><%= s.getAge() %></td></tr>
                <tr><th>专业</th><td><%= s.getMajor() != null ? s.getMajor() : "-" %></td></tr>
                <tr><th>班级</th><td><%= s.getClassName() != null ? s.getClassName() : "-" %></td></tr>
            </table>
            <div class="form-actions" style="margin-top:20px;">
                <a href="DeleteStudentServlet.do?id=<%= s.getId() %>" class="btn btn-danger">确认删除</a>
                <a href="ListStudentServlet.do" class="btn btn-secondary">取消</a>
            </div>
        </div>
        <% } else { %>
            <div class="empty-state">
                <div class="empty-icon">&#9888;</div>
                <h3>未找到学生信息</h3>
                <p>请从学生列表页面选择要删除的学生。</p>
                <a href="ListStudentServlet.do" class="btn btn-primary">返回列表</a>
            </div>
        <% } %>
    </div>
</body>
</html>
