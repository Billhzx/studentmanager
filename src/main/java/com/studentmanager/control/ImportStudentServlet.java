package com.studentmanager.control;

import com.studentmanager.entity.Student;
import com.studentmanager.model.StudentModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量导入学生信息控制器（CSV格式）
 * 接收上传的CSV文件，解析并批量导入数据库
 */
public class ImportStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Part filePart = request.getPart("csvFile");
        String fileName = filePart.getSubmittedFileName();

        if (fileName == null || (!fileName.endsWith(".csv") && !fileName.endsWith(".txt"))) {
            request.setAttribute("msg", "请上传.csv或.txt格式的文件！");
            request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
            return;
        }

        List<Student> students = new ArrayList<>();
        int totalRows = 0;
        int failCount = 0;

        try (InputStream is = filePart.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            boolean firstRow = true;

            while ((line = reader.readLine()) != null) {
                if (firstRow) {
                    firstRow = false;
                    continue; // 跳过标题行
                }

                line = line.trim();
                if (line.isEmpty()) continue;

                totalRows++;
                String[] cols = line.split(",", -1);

                if (cols.length < 2) {
                    failCount++;
                    continue;
                }

                try {
                    Student s = new Student();
                    s.setStudentNo(cols[0].trim());
                    s.setName(cols[1].trim());
                    s.setGender(cols.length > 2 ? cols[2].trim() : "男");
                    s.setAge(cols.length > 3 && !cols[3].trim().isEmpty() ? Integer.parseInt(cols[3].trim()) : 0);
                    s.setMajor(cols.length > 4 ? cols[4].trim() : "");
                    s.setClassName(cols.length > 5 ? cols[5].trim() : "");

                    if (!s.getStudentNo().isEmpty() && !s.getName().isEmpty()) {
                        students.add(s);
                    } else {
                        failCount++;
                    }
                } catch (NumberFormatException e) {
                    failCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "文件解析失败：" + e.getMessage());
            request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
            return;
        }

        int successCount = 0;
        if (!students.isEmpty()) {
            StudentModel model = new StudentModel();
            successCount = model.insertStudentBatch(students);
        }

        request.setAttribute("msg", String.format("导入完成！共读取%d条，成功导入%d条，跳过%d条。",
                totalRows, successCount, failCount));
        request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
    }
}
