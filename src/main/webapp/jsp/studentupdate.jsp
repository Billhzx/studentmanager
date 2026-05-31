<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentmanager.entity.Student" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改学生信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="page-container">
        <div class="page-header">
            <h1>修改学生信息</h1>
            <a href="ListStudentServlet.do" class="btn-back">返回列表</a>
        </div>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
            <div class="alert alert-error"><%= msg %></div>
        <% } %>

        <%
            Student s = (Student) request.getAttribute("student");
            if (s != null) {
        %>
        <div class="form-card">
            <form action="DoStudentServlet.do" method="post">
                <input type="hidden" name="id" value="<%= s.getId() %>">
                <div class="form-group">
                    <label for="studentNo">学号</label>
                    <input type="text" id="studentNo" name="studentNo" value="<%= s.getStudentNo() %>" required>
                </div>
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" id="name" name="name" value="<%= s.getName() %>" required>
                </div>
                <div class="form-group">
                    <label for="gender">性别</label>
                    <select id="gender" name="gender">
                        <option value="男" <%= "男".equals(s.getGender()) ? "selected" : "" %>>男</option>
                        <option value="女" <%= "女".equals(s.getGender()) ? "selected" : "" %>>女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="number" id="age" name="age" value="<%= s.getAge() %>" required>
                </div>
                <div class="form-group">
                    <label for="major">专业</label>
                    <input type="text" id="major" name="major" value="<%= s.getMajor() != null ? s.getMajor() : "" %>">
                </div>
                <div class="form-group">
                    <label for="className">班级</label>
                    <input type="text" id="className" name="className" value="<%= s.getClassName() != null ? s.getClassName() : "" %>">
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">确认修改</button>
                    <a href="ListStudentServlet.do" class="btn btn-secondary">取消</a>
                </div>
            </form>
        </div>
        <% } else { %>
            <div class="empty-state">
                <div class="empty-icon">&#9888;</div>
                <h3>未找到学生信息</h3>
                <p>请从学生列表页面选择要修改的学生。</p>
                <a href="ListStudentServlet.do" class="btn btn-primary">返回列表</a>
            </div>
        <% } %>
    </div>
</body>
</html>
