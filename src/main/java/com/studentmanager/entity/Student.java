package com.studentmanager.entity;

/**
 * 学生实体类
 * 对应数据库 student 表
 */
public class Student implements java.io.Serializable {

    private int id;
    private String studentNo;
    private String name;
    private String gender;
    private int age;
    private String major;
    private String className;

    public Student() {
    }

    public Student(int id, String studentNo, String name, String gender,
                   int age, String major, String className) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.major = major;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
