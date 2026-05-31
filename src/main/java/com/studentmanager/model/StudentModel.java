package com.studentmanager.model;

import com.studentmanager.dbutil.Dbconn;
import com.studentmanager.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息处理类（Model层）
 * 实现学生信息的增删改查操作
 */
public class StudentModel {

    /**
     * 查询所有学生
     */
    public List<Student> getAllStudents() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = Dbconn.getConnection();
            String sql = "SELECT * FROM student ORDER BY id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setStudentNo(rs.getString("studentNo"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setAge(rs.getInt("age"));
                s.setMajor(rs.getString("major"));
                s.setClassName(rs.getString("className"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 按ID查询学生
     */
    public Student getStudentById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Student s = null;
        try {
            conn = Dbconn.getConnection();
            String sql = "SELECT * FROM student WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getInt("id"));
                s.setStudentNo(rs.getString("studentNo"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setAge(rs.getInt("age"));
                s.setMajor(rs.getString("major"));
                s.setClassName(rs.getString("className"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt, rs);
        }
        return s;
    }

    /**
     * 新增学生
     */
    public int insertStudent(Student s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = Dbconn.getConnection();
            String sql = "INSERT INTO student (studentNo, name, gender, age, major, className) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getStudentNo());
            pstmt.setString(2, s.getName());
            pstmt.setString(3, s.getGender());
            pstmt.setInt(4, s.getAge());
            pstmt.setString(5, s.getMajor());
            pstmt.setString(6, s.getClassName());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt);
        }
        return result;
    }

    /**
     * 更新学生信息
     */
    public int updateStudent(Student s) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = Dbconn.getConnection();
            String sql = "UPDATE student SET studentNo=?, name=?, gender=?, age=?, major=?, className=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, s.getStudentNo());
            pstmt.setString(2, s.getName());
            pstmt.setString(3, s.getGender());
            pstmt.setInt(4, s.getAge());
            pstmt.setString(5, s.getMajor());
            pstmt.setString(6, s.getClassName());
            pstmt.setInt(7, s.getId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt);
        }
        return result;
    }

    /**
     * 删除学生
     */
    public int deleteStudent(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = Dbconn.getConnection();
            String sql = "DELETE FROM student WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt);
        }
        return result;
    }

    /**
     * 批量导入学生（用于Excel导入）
     */
    public int insertStudentBatch(List<Student> students) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            conn = Dbconn.getConnection();
            String sql = "INSERT INTO student (studentNo, name, gender, age, major, className) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Student s : students) {
                pstmt.setString(1, s.getStudentNo());
                pstmt.setString(2, s.getName());
                pstmt.setString(3, s.getGender());
                pstmt.setInt(4, s.getAge());
                pstmt.setString(5, s.getMajor());
                pstmt.setString(6, s.getClassName());
                pstmt.addBatch();
            }
            int[] results = pstmt.executeBatch();
            for (int r : results) {
                if (r > 0) count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Dbconn.close(conn, pstmt);
        }
        return count;
    }
}
