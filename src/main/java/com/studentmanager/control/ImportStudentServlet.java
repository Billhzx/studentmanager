package com.studentmanager.control;

import com.studentmanager.entity.Student;
import com.studentmanager.model.StudentModel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel批量导入学生信息控制器
 * 接收上传的Excel文件，解析并批量导入数据库
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

        Part filePart = request.getPart("excelFile");
        String fileName = filePart.getSubmittedFileName();

        if (fileName == null || !fileName.endsWith(".xlsx")) {
            request.setAttribute("msg", "请上传.xlsx格式的Excel文件！");
            request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
            return;
        }

        List<Student> students = new ArrayList<>();
        int successCount = 0;
        int failCount = 0;

        try (InputStream is = filePart.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            // 从第2行开始读取（第1行为标题行）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    Student s = new Student();
                    s.setStudentNo(getCellString(row, 0));
                    s.setName(getCellString(row, 1));
                    s.setGender(getCellString(row, 2));
                    s.setAge((int) getCellNumeric(row, 3));
                    s.setMajor(getCellString(row, 4));
                    s.setClassName(getCellString(row, 5));

                    if (s.getStudentNo() != null && !s.getStudentNo().isEmpty()
                            && s.getName() != null && !s.getName().isEmpty()) {
                        students.add(s);
                    }
                } catch (Exception e) {
                    failCount++;
                }
            }

            if (!students.isEmpty()) {
                StudentModel model = new StudentModel();
                successCount = model.insertStudentBatch(students);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Excel文件解析失败：" + e.getMessage());
            request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
            return;
        }

        request.setAttribute("totalRows", students.size() + failCount);
        request.setAttribute("successCount", successCount);
        request.setAttribute("failCount", failCount);
        request.setAttribute("msg", String.format("导入完成！共读取%d条记录，成功导入%d条，跳过%d条。",
                students.size() + failCount, successCount, failCount));
        request.getRequestDispatcher("/jsp/studentimport.jsp").forward(request, response);
    }

    private String getCellString(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private double getCellNumeric(Row row, int col) {
        Cell cell = row.getCell(col);
        if (cell == null) return 0;
        return cell.getNumericCellValue();
    }
}
