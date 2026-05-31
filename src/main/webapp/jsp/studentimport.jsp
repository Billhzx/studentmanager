<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Excel批量导入学生信息</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="page-container">
        <div class="page-header">
            <h1>Excel批量导入学生信息</h1>
            <a href="ListStudentServlet.do" class="btn-back">返回列表</a>
        </div>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
            <div class="alert alert-info"><%= msg %></div>
        <% } %>

        <div class="form-card">
            <div class="import-info">
                <strong>导入说明：</strong><br>
                1. 请上传.xlsx格式的Excel文件（Office 2007+格式）；<br>
                2. Excel第一行为标题行，数据从第二行开始；<br>
                3. 列顺序为：学号、姓名、性别、年龄、专业、班级；<br>
                4. 学号和姓名为必填字段。
            </div>

            <form action="ImportStudentServlet.do" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="excelFile">选择Excel文件</label>
                    <input type="file" id="excelFile" name="excelFile" accept=".xlsx" required>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">开始导入</button>
                    <a href="ListStudentServlet.do" class="btn btn-secondary">取消</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
