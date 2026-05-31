package com.studentmanager.control;

import com.studentmanager.entity.Student;
import com.studentmanager.model.StudentModel;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 学生信息列表控制器
 * 查询所有学生信息并转发到列表页面
 */
public class ListStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("=== ListStudentServlet called ===");
        StudentModel model = new StudentModel();
        List<Student> studentList = model.getAllStudents();
        System.out.println("=== studentList size: " + studentList.size() + " ===");
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/jsp/studentlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
