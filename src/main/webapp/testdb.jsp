<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head><title>DB Test</title></head>
<body>
<h2>数据库连接测试</h2>
<%
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        out.println("<p>驱动加载成功</p>");
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/students?useSSL=false&characterEncoding=utf8",
            "root", "hzx123aaaa");
        out.println("<p>连接成功</p>");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM student");
        if (rs.next()) {
            out.println("<p>student表记录数: " + rs.getInt(1) + "</p>");
        }
        rs = stmt.executeQuery("SELECT * FROM student");
        while (rs.next()) {
            out.println("<p>ID=" + rs.getInt("id") + ", 姓名=" + rs.getString("name") + "</p>");
        }
        conn.close();
    } catch (Exception e) {
        out.println("<pre>");
        e.printStackTrace(new java.io.PrintWriter(out));
        out.println("</pre>");
    }
%>
</body>
</html>
