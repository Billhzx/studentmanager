# 学生信息管理系统 (Student Manager)

基于 JSP + JavaBean + Servlet 技术实现的 MVC 设计模式学生信息管理系统。

## 功能

- 学生信息列表展示
- 新增学生信息
- 修改学生信息
- 删除学生信息
- Excel 批量导入学生信息

## 技术栈

- **后端**：Java Servlet、JavaBean
- **前端**：JSP、CSS
- **数据库**：MySQL
- **Excel 解析**：Apache POI
- **服务器**：Apache Tomcat 9+

## 项目结构

```
studentmanager/
├── sql/
│   └── student.sql              # 数据库建表脚本
├── src/main/java/com/studentmanager/
│   ├── control/                  # 控制器层（Servlet）
│   │   ├── ListStudentServlet.java       # 列表
│   │   ├── InsertStudentServlet.java     # 新增
│   │   ├── UpStudentServlet.java         # 修改前查询
│   │   ├── DoStudentServlet.java         # 修改后处理
│   │   ├── ShowStudentServlet.java       # 删除前确认
│   │   ├── DeleteStudentServlet.java     # 删除后处理
│   │   └── ImportStudentServlet.java     # Excel批量导入
│   ├── dbutil/
│   │   └── Dbconn.java          # 数据库连接工具类
│   ├── entity/
│   │   └── Student.java         # 学生实体类
│   └── model/
│       └── StudentModel.java    # 业务逻辑层（CRUD）
├── src/main/webapp/
│   ├── jsp/
│   │   ├── studentlist.jsp      # 学生列表视图
│   │   ├── studentinsert.jsp    # 新增学生视图
│   │   ├── studentupdate.jsp    # 修改学生视图
│   │   ├── studentshow.jsp      # 删除确认视图
│   │   └── studentimport.jsp    # Excel导入视图
│   ├── css/
│   │   └── style.css            # 全局样式表
│   └── WEB-INF/
│       └── web.xml              # 部署描述符
└── lib/                         # 依赖JAR包
    ├── mysql-connector-java.jar
    └── poi-ooxml.jar
```

## 部署说明

1. 在 MySQL 中执行 `sql/student.sql` 创建数据库和表
2. 将项目部署到 Tomcat 的 webapps 目录下
3. 确保 `lib/` 目录中包含 MySQL 驱动和 Apache POI 相关 JAR 包
4. 启动 Tomcat 服务器
5. 访问：http://localhost:8080/studentmanager/ListStudentServlet.do

## 环境要求

- JDK 8+
- MySQL 8.0+
- Apache Tomcat 9+
- Apache POI 5.x
