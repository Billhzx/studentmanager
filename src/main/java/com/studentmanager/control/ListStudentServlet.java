package com.studentmanager.control;

import com.studentmanager.entity.Student;
import com.studentmanager.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学生信息列表控制器
 * 查询所有学生信息并转发到列表页面
 */
@WebServlet("/ListStudentServlet.do")
public class ListStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StudentModel model = new StudentModel();
        List<Student> studentList = model.getAllStudents();
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/jsp/studentlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
