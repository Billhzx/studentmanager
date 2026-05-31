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
 * 新增学生信息控制器
 * 接收表单数据，插入数据库，重定向到列表页
 */
@WebServlet("/InsertStudentServlet.do")
public class InsertStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String studentNo = request.getParameter("studentNo");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String major = request.getParameter("major");
        String className = request.getParameter("className");

        Student s = new Student();
        s.setStudentNo(studentNo);
        s.setName(name);
        s.setGender(gender);
        s.setAge(age);
        s.setMajor(major);
        s.setClassName(className);

        StudentModel model = new StudentModel();
        int result = model.insertStudent(s);

        if (result > 0) {
            request.setAttribute("msg", "新增学生信息成功！");
        } else {
            request.setAttribute("msg", "新增学生信息失败！");
        }
        request.getRequestDispatcher("/jsp/studentinsert.jsp").forward(request, response);
    }
}
