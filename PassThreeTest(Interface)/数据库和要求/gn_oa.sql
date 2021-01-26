/*
 Navicat Premium Data Transfer

 Source Server         : abc
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : gn_oa

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 11/08/2020 08:57:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `attendance_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考勤表主键',
  `emp_id` int(11) NOT NULL COMMENT '员工ID,关联员工表的主键',
  `work_type_id` int(11) NOT NULL COMMENT '任务类型ID,关联任务类型主键',
  `start_time` date NOT NULL COMMENT '起始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  `days` int(11) NOT NULL DEFAULT 1 COMMENT '请假天数',
  `resultStatus` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务状态',
  PRIMARY KEY (`attendance_id`) USING BTREE,
  INDEX `emp_id`(`emp_id`) USING BTREE,
  INDEX `work_type_id`(`work_type_id`) USING BTREE,
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`work_type_id`) REFERENCES `work_type` (`work_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 2, 3, '2020-05-01', '2020-05-08', 3, '进行中');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级主键',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `subject_id` int(11) NOT NULL COMMENT '学科ID，关联学科表主键',
  `class_count` int(11) DEFAULT 0 COMMENT '班级人数',
  `class_life` int(11) DEFAULT 0 COMMENT '班级周期',
  `class_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级地址',
  `create_time` date NOT NULL COMMENT '班级创建时间',
  PRIMARY KEY (`class_id`) USING BTREE,
  INDEX `subject_id`(`subject_id`) USING BTREE,
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (2, 'NZ20011', 3, 100, 22, '哈尔滨市', '2020-05-02');
INSERT INTO `class` VALUES (3, 'NZ20011333', 2, 100, 22, '哈尔滨市', '2020-05-07');
INSERT INTO `class` VALUES (4, 'NZ2001222', 3, 1000, 220, '哈尔滨市', '2020-05-03');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门主键',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `create_time` date NOT NULL COMMENT '部门创建时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '人事部1', '2020-05-28');
INSERT INTO `department` VALUES (3, '产品研发部', '2017-01-01');
INSERT INTO `department` VALUES (4, '行政部', '2020-05-16');

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education`  (
  `education_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学历主键',
  `education_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学历名称',
  PRIMARY KEY (`education_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学历表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of education
-- ----------------------------
INSERT INTO `education` VALUES (1, '中专');
INSERT INTO `education` VALUES (2, '大专');
INSERT INTO `education` VALUES (3, '高中');
INSERT INTO `education` VALUES (4, '初中');
INSERT INTO `education` VALUES (5, '小学');
INSERT INTO `education` VALUES (6, '本科');
INSERT INTO `education` VALUES (7, '研究生');
INSERT INTO `education` VALUES (8, '博士');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工主键',
  `emp_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工名',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID，关联department表的主键',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `hire_time` date NOT NULL COMMENT '入职时间',
  `emp_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工密码',
  `role_id` int(11) NOT NULL COMMENT '角色ID关联角色表主键',
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `dept_id`(`dept_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '李雷', 3, '男', '12354865897', '152658', 'lilei@163.com', '2020-05-16', '111111', 10000);
INSERT INTO `employee` VALUES (2, '韩梅梅', 1, '女', '12354865897', '152658', 'hmm@163.com', '2020-05-12', '111111', 10000);
INSERT INTO `employee` VALUES (3, 'Tom', 3, '男', '18071976684', '1232122', '1@1.com', '2020-05-01', '123456', 10002);
INSERT INTO `employee` VALUES (5, '李四', 1, '男', '14256589758', '4552552', '123@123.com', '2012-12-12', '12535545', 10000);
INSERT INTO `employee` VALUES (6, '李四2', 1, '男', '14256589759', '4552553', '123@124.com', '2012-12-13', '12535546', 10000);
INSERT INTO `employee` VALUES (7, '李四3', 1, '男', '14256589760', '4552554', '123@125.com', '2012-12-14', '12535547', 10000);
INSERT INTO `employee` VALUES (8, '李四4', 1, '男', '14256589761', '4552555', '123@126.com', '2012-12-15', '12535548', 10000);
INSERT INTO `employee` VALUES (9, '李四5', 1, '男', '14256589762', '4552556', '123@127.com', '2012-12-16', '12535549', 10000);
INSERT INTO `employee` VALUES (10, '李四6', 1, '男', '14256589763', '4552557', '123@128.com', '2012-12-17', '12535550', 10000);
INSERT INTO `employee` VALUES (11, '李四7', 1, '男', '14256589764', '4552558', '123@129.com', '2012-12-18', '12535551', 10000);
INSERT INTO `employee` VALUES (12, '李四8', 1, '男', '14256589765', '4552559', '123@130.com', '2012-12-19', '12535552', 10000);
INSERT INTO `employee` VALUES (13, '李四', 1, '男', '14256589758', '4552552', '123@123.com', '2012-12-12', '12535545', 10000);
INSERT INTO `employee` VALUES (14, '李四2', 1, '男', '14256589759', '4552553', '123@124.com', '2012-12-13', '12535546', 10000);
INSERT INTO `employee` VALUES (15, '李四3', 1, '男', '14256589760', '4552554', '123@125.com', '2012-12-14', '12535547', 10000);
INSERT INTO `employee` VALUES (16, '李四4', 1, '男', '14256589761', '4552555', '123@126.com', '2012-12-15', '12535548', 10000);
INSERT INTO `employee` VALUES (17, '李四5', 1, '男', '14256589762', '4552556', '123@127.com', '2012-12-16', '12535549', 10000);
INSERT INTO `employee` VALUES (18, '李四6', 1, '男', '14256589763', '4552557', '123@128.com', '2012-12-17', '12535550', 10000);
INSERT INTO `employee` VALUES (19, '李四7', 1, '男', '14256589764', '4552558', '123@129.com', '2012-12-18', '12535551', 10000);
INSERT INTO `employee` VALUES (20, '李四8', 1, '男', '14256589765', '4552559', '123@130.com', '2012-12-19', '12535552', 10006);
INSERT INTO `employee` VALUES (21, '招生老师01', 3, '女', '12345698548', '126354', 'zhaosheng@163.com', '2020-05-01', '123456', 10006);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态: 有效/无效',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (10000, '平台管理员1', '有效');
INSERT INTO `role` VALUES (10002, '技术经理', '有效');
INSERT INTO `role` VALUES (10005, 'Java技术总监(正)', '有效');
INSERT INTO `role` VALUES (10006, '招生教师', '有效');
INSERT INTO `role` VALUES (10007, '平台管理员', '有效');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学员主键',
  `student_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学员名称',
  `class_id` int(11) NOT NULL COMMENT '班级ID关联班级表的主键',
  `stu_gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别 男:女',
  `stu_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `stu_qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'QQ号',
  `stu_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `graduate_school` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '毕业院校',
  `education_id` int(11) NOT NULL COMMENT '学历ID,关联学历表主键',
  `create_time` date DEFAULT NULL COMMENT '出生日期',
  `in_time` date NOT NULL COMMENT '入学时间',
  `emp_id` int(11) NOT NULL COMMENT '教师ID关联员工表主键',
  `card_num` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `education_id`(`education_id`) USING BTREE,
  INDEX `class_id`(`class_id`) USING BTREE,
  INDEX `emp_id`(`emp_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`education_id`) REFERENCES `education` (`education_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2, '李刚2', 4, '女', '52458622958485', '856954', '123@122223.com', '西安交通大学', 4, '2006-05-29', '2020-05-15', 21, '664545613216521');
INSERT INTO `student` VALUES (3, '李刚21312', 4, '女', '524586958485', '856954', '123@122223.com', '西安交通大学', 7, '2020-05-02', '2020-05-01', 21, '2323252564545621516121561');
INSERT INTO `student` VALUES (4, '李刚543534', 4, '男', '524586958485', '856954', '123@123.com', '西安交通大学', 4, '2020-05-14', '2020-05-03', 20, '2323252564545621516121561');
INSERT INTO `student` VALUES (5, '赵四1', 3, '男', '1556854985', '22325525', '12345@123.com', '浙江大学1', 4, '1999-10-01', '2020-10-01', 20, '5645656465456456');
INSERT INTO `student` VALUES (6, '赵四2', 3, '男', '1556854986', '22325526', '12345@124.com', '浙江大学2', 4, '1999-10-02', '2020-10-02', 20, '5645656465456456');
INSERT INTO `student` VALUES (7, '赵四3', 3, '男', '1556854987', '22325527', '12345@125.com', '浙江大学3', 4, '1999-10-03', '2020-10-03', 20, '5645656465456456');
INSERT INTO `student` VALUES (8, '赵四4', 3, '男', '1556854988', '22325528', '12345@126.com', '浙江大学4', 4, '1999-10-04', '2020-10-04', 20, '5645656465456456');
INSERT INTO `student` VALUES (9, '赵四5', 3, '男', '1556854989', '22325529', '12345@127.com', '浙江大学5', 4, '1999-10-05', '2020-10-05', 20, '5645656465456456');
INSERT INTO `student` VALUES (10, '赵四6', 3, '男', '1556854990', '22325530', '12345@128.com', '浙江大学6', 4, '1999-10-06', '2020-10-06', 20, '5645656465456456');
INSERT INTO `student` VALUES (11, '赵四7', 3, '男', '1556854991', '22325531', '12345@129.com', '浙江大学7', 4, '1999-10-07', '2020-10-07', 20, '5645656465456456');
INSERT INTO `student` VALUES (12, '赵四8', 3, '男', '1556854992', '22325532', '12345@130.com', '浙江大学8', 4, '1999-10-08', '2020-10-08', 20, '5645656465456456');
INSERT INTO `student` VALUES (13, '赵四9', 3, '男', '1556854993', '22325533', '12345@131.com', '浙江大学9', 4, '1999-10-09', '2020-10-09', 20, '5645656465456456');
INSERT INTO `student` VALUES (14, '赵四10', 3, '男', '1556854994', '22325534', '12345@132.com', '浙江大学10', 4, '1999-10-10', '2020-10-10', 20, '5645656465456456');
INSERT INTO `student` VALUES (15, '赵四11', 3, '男', '1556854995', '22325535', '12345@133.com', '浙江大学11', 4, '1999-10-11', '2020-10-11', 20, '5645656465456456');
INSERT INTO `student` VALUES (16, '赵四12', 3, '男', '1556854996', '22325536', '12345@134.com', '浙江大学12', 4, '1999-10-12', '2020-10-12', 20, '5645656465456456');
INSERT INTO `student` VALUES (17, '赵四13', 3, '男', '1556854997', '22325537', '12345@135.com', '浙江大学13', 4, '1999-10-13', '2020-10-13', 20, '5645656465456456');
INSERT INTO `student` VALUES (18, '赵四14', 3, '男', '1556854998', '22325538', '12345@136.com', '浙江大学14', 4, '1999-10-14', '2020-10-14', 20, '5645656465456456');
INSERT INTO `student` VALUES (19, '赵四15', 3, '男', '1556854999', '22325539', '12345@137.com', '浙江大学15', 4, '1999-10-15', '2020-10-15', 20, '5645656465456456');
INSERT INTO `student` VALUES (20, '赵四16', 3, '男', '1556855000', '22325540', '12345@138.com', '浙江大学16', 4, '1999-10-16', '2020-10-16', 20, '5645656465456456');
INSERT INTO `student` VALUES (21, '赵四17', 3, '男', '1556855001', '22325541', '12345@139.com', '浙江大学17', 4, '1999-10-17', '2020-10-17', 20, '5645656465456456');
INSERT INTO `student` VALUES (22, '赵四18', 3, '男', '1556855002', '22325542', '12345@140.com', '浙江大学18', 4, '1999-10-18', '2020-10-18', 20, '5645656465456456');
INSERT INTO `student` VALUES (23, '赵四19', 3, '男', '1556855003', '22325543', '12345@141.com', '浙江大学19', 4, '1999-10-19', '2020-10-19', 20, '5645656465456456');
INSERT INTO `student` VALUES (24, '赵四20', 3, '男', '1556855004', '22325544', '12345@142.com', '浙江大学20', 4, '1999-10-20', '2020-10-20', 20, '5645656465456456');
INSERT INTO `student` VALUES (25, '赵四21', 3, '男', '1556855005', '22325545', '12345@143.com', '浙江大学21', 4, '1999-10-21', '2020-10-21', 20, '5645656465456456');
INSERT INTO `student` VALUES (26, '赵四22', 3, '男', '1556855006', '22325546', '12345@144.com', '浙江大学22', 4, '1999-10-22', '2020-10-22', 20, '5645656465456456');
INSERT INTO `student` VALUES (27, '赵四23', 3, '男', '1556855007', '22325547', '12345@145.com', '浙江大学23', 4, '1999-10-23', '2020-10-23', 20, '5645656465456456');
INSERT INTO `student` VALUES (28, '赵四24', 3, '男', '1556855008', '22325548', '12345@146.com', '浙江大学24', 4, '1999-10-24', '2020-10-24', 20, '5645656465456456');
INSERT INTO `student` VALUES (30, '赵四26', 3, '男', '1556855010', '22325550', '12345@148.com', '浙江大学26', 4, '1999-10-26', '2020-10-26', 20, '5645656465456456');
INSERT INTO `student` VALUES (31, '赵四27', 3, '男', '1556855011', '22325551', '12345@149.com', '浙江大学27', 4, '1999-10-27', '2020-10-27', 20, '5645656465456456');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学科主键',
  `subject_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学科名称',
  `subject_life` int(11) DEFAULT 0 COMMENT '学科周期',
  `create_time` date NOT NULL COMMENT '学科创建时间',
  `subject_type_id` int(11) NOT NULL COMMENT '学科类型ID，关联学科表主键',
  PRIMARY KEY (`subject_id`) USING BTREE,
  INDEX `subject_type_id`(`subject_type_id`) USING BTREE,
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`subject_type_id`) REFERENCES `subject_type` (`subject_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学科表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (2, 'python', 22, '2020-05-02', 1);
INSERT INTO `subject` VALUES (3, 'H5', 22, '2020-05-06', 4);
INSERT INTO `subject` VALUES (4, 'python', 22, '2020-05-15', 2);
INSERT INTO `subject` VALUES (5, 'Java', 22, '2020-05-16', 2);

-- ----------------------------
-- Table structure for subject_type
-- ----------------------------
DROP TABLE IF EXISTS `subject_type`;
CREATE TABLE `subject_type`  (
  `subject_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学科类型主键',
  `subject_type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学科名称',
  PRIMARY KEY (`subject_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学科类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject_type
-- ----------------------------
INSERT INTO `subject_type` VALUES (1, '周末班');
INSERT INTO `subject_type` VALUES (2, '精品班');
INSERT INTO `subject_type` VALUES (3, '正式班');
INSERT INTO `subject_type` VALUES (4, '补课班');

-- ----------------------------
-- Table structure for work_type
-- ----------------------------
DROP TABLE IF EXISTS `work_type`;
CREATE TABLE `work_type`  (
  `work_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务类型主键',
  `work_type_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`work_type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_type
-- ----------------------------
INSERT INTO `work_type` VALUES (1, '产假');
INSERT INTO `work_type` VALUES (2, '调休');
INSERT INTO `work_type` VALUES (3, '病假');
INSERT INTO `work_type` VALUES (4, '婚假');

SET FOREIGN_KEY_CHECKS = 1;
