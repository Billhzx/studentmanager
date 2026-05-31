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
 * 修改前控制器：根据ID查询学生信息并转发到修改页面
 */
@WebServlet("/UpStudentServlet.do")
public class UpStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentModel model = new StudentModel();
        Student student = model.getStudentById(id);

        if (student != null) {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/jsp/studentupdate.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "未找到该学生信息！");
            request.getRequestDispatcher("/ListStudentServlet.do").forward(request, response);
        }
    }
}
