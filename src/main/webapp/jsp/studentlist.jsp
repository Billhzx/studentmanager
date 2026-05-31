<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentmanager.entity.Student, java.util.List" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生信息列表</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="page-container">
        <div class="page-header">
            <h1>学生信息列表</h1>
            <div class="header-actions">
                <a href="InsertStudentServlet.do" class="btn btn-primary">新增学生</a>
                <a href="ImportStudentServlet.do" class="btn btn-success">Excel导入</a>
            </div>
        </div>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
            <div class="alert alert-info"><%= msg %></div>
        <% } %>

        <%
            List<Student> studentList = (List<Student>) request.getAttribute("studentList");
            if (studentList != null && !studentList.isEmpty()) {
        %>
            <div class="table-card">
                <div class="table-summary">
                    共 <strong><%= studentList.size() %></strong> 名学生
                </div>
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>专业</th>
                            <th>班级</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Student s : studentList) { %>
                        <tr>
                            <td><%= s.getId() %></td>
                            <td><%= s.getStudentNo() %></td>
                            <td><%= s.getName() %></td>
                            <td><%= s.getGender() %></td>
                            <td><%= s.getAge() %></td>
                            <td><%= s.getMajor() %></td>
                            <td><%= s.getClassName() %></td>
                            <td>
                                <a href="UpStudentServlet.do?id=<%= s.getId() %>" class="btn-sm btn-edit">修改</a>
                                <a href="ShowStudentServlet.do?id=<%= s.getId() %>" class="btn-sm btn-del">删除</a>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        <% } else { %>
            <div class="empty-state">
                <div class="empty-icon">&#128218;</div>
                <h3>暂无学生数据</h3>
                <p>系统中还没有任何学生信息记录。</p>
                <a href="InsertStudentServlet.do" class="btn btn-primary">添加学生</a>
            </div>
        <% } %>
    </div>
</body>
</html>
