<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增学生信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="page-container">
        <div class="page-header">
            <h1>新增学生信息</h1>
            <a href="ListStudentServlet.do" class="btn-back">返回列表</a>
        </div>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
            <div class="alert alert-info"><%= msg %></div>
        <% } %>

        <div class="form-card">
            <form action="InsertStudentServlet.do" method="post">
                <div class="form-group">
                    <label for="studentNo">学号</label>
                    <input type="text" id="studentNo" name="studentNo" placeholder="请输入学号" required>
                </div>
                <div class="form-group">
                    <label for="name">姓名</label>
                    <input type="text" id="name" name="name" placeholder="请输入姓名" required>
                </div>
                <div class="form-group">
                    <label for="gender">性别</label>
                    <select id="gender" name="gender">
                        <option value="男" selected>男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="age">年龄</label>
                    <input type="number" id="age" name="age" placeholder="请输入年龄" required>
                </div>
                <div class="form-group">
                    <label for="major">专业</label>
                    <input type="text" id="major" name="major" placeholder="请输入专业">
                </div>
                <div class="form-group">
                    <label for="className">班级</label>
                    <input type="text" id="className" name="className" placeholder="请输入班级">
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">提交新增</button>
                    <button type="reset" class="btn btn-secondary">重置</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
