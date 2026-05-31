package com.studentmanager.control;

import com.studentmanager.entity.Student;
import com.studentmanager.model.StudentModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改后控制器：接收修改后的表单数据，更新数据库
 */
@WebServlet("/DoStudentServlet.do")
public class DoStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String studentNo = request.getParameter("studentNo");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String major = request.getParameter("major");
        String className = request.getParameter("className");

        Student s = new Student();
        s.setId(id);
        s.setStudentNo(studentNo);
        s.setName(name);
        s.setGender(gender);
        s.setAge(age);
        s.setMajor(major);
        s.setClassName(className);

        StudentModel model = new StudentModel();
        int result = model.updateStudent(s);

        if (result > 0) {
            response.sendRedirect("ListStudentServlet.do");
        } else {
            request.setAttribute("msg", "修改学生信息失败！");
            request.setAttribute("student", s);
            request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
        }
    }
}
