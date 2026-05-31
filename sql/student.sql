-- ============================================================
-- 学生信息管理系统 - 数据库建表脚本
-- 数据库：students
-- ============================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS students DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 使用数据库
USE students;

-- 创建学生信息表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID',
  `studentNo` VARCHAR(20) NOT NULL COMMENT '学号',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `gender` VARCHAR(4) DEFAULT '男' COMMENT '性别',
  `age` INT COMMENT '年龄',
  `major` VARCHAR(100) COMMENT '专业',
  `className` VARCHAR(50) COMMENT '班级'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

-- 插入测试数据
INSERT INTO `student` (`studentNo`, `name`, `gender`, `age`, `major`, `className`) VALUES
  ('202331607101', '张三', '男', 20, '计算机科学与技术', '23级卓越班'),
  ('202331607102', '李四', '女', 21, '软件工程', '23级1班'),
  ('202331607103', '王五', '男', 19, '计算机科学与技术', '23级卓越班'),
  ('202331607104', '赵六', '女', 20, '数据科学与大数据技术', '23级2班'),
  ('202331607105', '孙七', '男', 22, '人工智能', '23级卓越班');
