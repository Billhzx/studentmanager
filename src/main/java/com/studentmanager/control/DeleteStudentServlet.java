package com.studentmanager.control;

import com.studentmanager.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除后控制器：执行删除操作并重定向到列表页
 */
@WebServlet("/DeleteStudentServlet.do")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentModel model = new StudentModel();
        int result = model.deleteStudent(id);

        if (result > 0) {
            response.sendRedirect("ListStudentServlet.do");
        } else {
            request.setAttribute("msg", "删除学生信息失败！");
            request.getRequestDispatcher("/ListStudentServlet.do").forward(request, response);
        }
    }
}
