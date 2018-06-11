-- -------------------------------------
-- Table structure for `admin` 系统用户表
-- -------------------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` INT AUTO_INCREMENT COMMENT '主键',
  `account` varchar(16) NOT NULL COMMENT '系统用户的登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '系统用户的登录密码',
  `type` SMALLINT DEFAULT 1 COMMENT '系统用户的类别',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_account(`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='pc后台账号表 || 系统用户表';
INSERT INTO t_admin(`account`, `password`, `type`) VALUE ('admin', '4QrcOUm6Wau+VuBX8g+IPg==', 1);

-- -------------------------------------
-- Table structure for `t_schedule` 时间表
-- -------------------------------------
DROP TABLE IF EXISTS `t_schedule`;
CREATE TABLE `t_schedule` (
  `name` SMALLINT NOT NULL COMMENT '主键 时间名 例：1，2',
  `season` VARCHAR(10) NOT NULL COMMENT '主键 季节，例 春、秋',
  `start_time` TIME NOT NULL COMMENT '开始时间',
  `end_time` TIME NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`name`, `season`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_schedule VALUE (1, '春', '08:20', '10:00');
INSERT INTO t_schedule VALUE (2, '春', '10:20', '12:00');
INSERT INTO t_schedule VALUE (3, '春', '14:00', '15:40');
INSERT INTO t_schedule VALUE (4, '春', '16:00', '17:40');

-- -------------------------------------
-- Table structure for `t_academic_year` 学年表
-- -------------------------------------
DROP TABLE IF EXISTS `t_academic_year`;
CREATE TABLE `t_academic_year` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 学年ID自增',
  `name` VARCHAR(15) DEFAULT NULL COMMENT '学年名称，例 2015-2016',
  `semester_one_start` VARCHAR(15) NOT NULL COMMENT '一学期开学时间，例 2017-9-10',
  `semester_one_end` VARCHAR(15) NOT NULL COMMENT '一学期结束时间，例 2018-2-10',
  `semester_two_start` VARCHAR(15) NOT NULL COMMENT '二学期开学时间，例 2018-3-10',
  `semester_two_end` VARCHAR(15) NOT NULL COMMENT '二学期结束时间，例 2018-7-10',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# 自增从2010开始
ALTER TABLE t_academic_year AUTO_INCREMENT=2010;
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2010-2011', '2010-09-10', '2011-2-10', '2011-03-10', '2011-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2011-2012', '2011-09-10', '2012-2-10', '2012-03-10', '2012-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2012-2013', '2012-09-10', '2013-2-10', '2013-03-10', '2013-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2013-2014', '2013-09-10', '2014-2-10', '2014-03-10', '2014-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2014-2015', '2014-09-10', '2015-2-10', '2015-03-10', '2015-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2015-2016', '2015-09-10', '2016-2-10', '2016-03-10', '2016-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2016-2017', '2016-09-10', '2017-2-10', '2017-03-10', '2017-07-10');
INSERT INTO t_academic_year(name, semester_one_start, semester_one_end, semester_two_start, semester_two_end)
  VALUE ('2017-2018', '2017-09-10', '2018-2-10', '2018-03-10', '2018-07-10');

-- -------------------------------------
-- Table structure for `t_grade` 年级表
-- -------------------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 年级ID自增',
  `name` VARCHAR(4) DEFAULT NULL COMMENT '年级名称，例 2015',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# 自增从2010开始
ALTER TABLE t_grade AUTO_INCREMENT=2010;
INSERT INTO t_grade(name) VALUE ('2010');
INSERT INTO t_grade(name) VALUE ('2011');
INSERT INTO t_grade(name) VALUE ('2012');
INSERT INTO t_grade(name) VALUE ('2013');
INSERT INTO t_grade(name) VALUE ('2014');
INSERT INTO t_grade(name) VALUE ('2015');
INSERT INTO t_grade(name) VALUE ('2016');
INSERT INTO t_grade(name) VALUE ('2017');

-- --------------------------------------
-- Table structure for `t_academy` 学院表
-- --------------------------------------
DROP TABLE IF EXISTS `t_academy`;
CREATE TABLE `t_academy` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 学院ID自增',
  `name` varchar(32) DEFAULT NULL COMMENT '学院名称，例 计算机科学与技术学院',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_academy(id, name) VALUE (1, '计算机科学与工程学院');
INSERT INTO t_academy(id, name) VALUE (2, '管理学院');
INSERT INTO t_academy(id, name) VALUE (3, '药生学院');
INSERT INTO t_academy(id, name) VALUE (4, '机械学院');
INSERT INTO t_academy(id, name) VALUE (5, '数学与统计学院');

-- --------------------------------------
-- Table structure for `t_specialty` 专业表
-- --------------------------------------
DROP TABLE IF EXISTS `t_specialty`;
CREATE TABLE `t_specialty` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 专业ID自增',
  `academy_id` INT NOT NULL COMMENT '外键 所属学院ID',
  `name` varchar(32) DEFAULT NULL COMMENT '专业名称，例 物联网工程',
  `specialty_code` varchar(32) DEFAULT NULL COMMENT '专业代码，例 0802',
  PRIMARY KEY (`id`),
  FOREIGN KEY (academy_id) REFERENCES t_academy(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_specialty(id, academy_id, name, specialty_code) VALUE (1, 1, '物联网工程', '0301');
INSERT INTO t_specialty(id, academy_id, name, specialty_code) VALUE (2, 2, '旅游管理', '0802');
INSERT INTO t_specialty(id, academy_id, name, specialty_code) VALUE (3, 5, '信息与计算科学', '0101');

-- --------------------------------------
-- Table structure for `t_class` 班级表
-- --------------------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 班级ID自增',
  `grade_id` INT NOT NULL COMMENT '外键 所属年级ID',
  `specialty_id` INT NOT NULL COMMENT '外键 所属专业ID',
  `name` varchar(32) NOT NULL COMMENT '班级名称，例 一班',
  `class_code` varchar(6) NOT NULL COMMENT '班级代码，例 01',
  PRIMARY KEY (`id`),
  FOREIGN KEY (grade_id) REFERENCES t_grade(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (specialty_id) REFERENCES t_specialty(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (1, 2015, 1, '一班', '030101');-- 物联网工程
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (2, 2015, 1, '二班', '030102');-- 物联网工程
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (3, 2015, 2, '一班', '080201');-- 旅游管理
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (4, 2015, 2, '二班', '080202');-- 旅游管理
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (5, 2015, 3, '一班', '010101');-- 信息与计算科学
INSERT INTO t_class(id, grade_id, specialty_id, name, class_code) VALUE (6, 2015, 3, '二班', '010102');-- 信息与计算科学

-- --------------------------------------
-- Table structure for `t_student` 学生表
-- --------------------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` INT AUTO_INCREMENT COMMENT '主键',
  `account` varchar(16) NOT NULL COMMENT '学生用户 登录名',
  `password` varchar(255) NOT NULL COMMENT '密码 插入时需加密 例MD5加密',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` SMALLINT DEFAULT 2 COMMENT '性别 0-男 1-女 2-未知',
  `grade_id` INT NOT NULL COMMENT '外键 所属年级ID',
  `academy_id` INT NOT NULL COMMENT '外键 所属学院ID',
  `specialty_id` INT NOT NULL COMMENT '外键 所属专业ID',
  `class_id` INT NOT NULL COMMENT '外键 所属班级ID',
  `state` SMALLINT DEFAULT 0 COMMENT '状态 例如 0：正常 1：封禁',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_account(`account`),
  FOREIGN KEY (grade_id) REFERENCES t_grade(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (academy_id) REFERENCES t_academy(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (specialty_id) REFERENCES t_specialty(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (class_id) REFERENCES t_class(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_student(id, account, password, name, gender, grade_id, academy_id, specialty_id, class_id, state)
  VALUE
  (1, '11503010223', '4QrcOUm6Wau+VuBX8g+IPg==', '小李', 0, 2015, 1, 1, 2, 0);
INSERT INTO t_student(id, account, password, name, gender, grade_id, academy_id, specialty_id, class_id, state)
  VALUE
  (2, '11501010123', '4QrcOUm6Wau+VuBX8g+IPg==', '小明', 1, 2015, 5, 3, 5, 0);
INSERT INTO t_student(id, account, password, name, gender, grade_id, academy_id, specialty_id, class_id, state)
  VALUE
  (3, '11508020123', '4QrcOUm6Wau+VuBX8g+IPg==', '小王', 2, 2015, 2, 2, 3, 0);

-- --------------------------------------
-- Table structure for `t_teacher` 教师表
-- --------------------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` INT AUTO_INCREMENT COMMENT '主键',
  `account` varchar(16) NOT NULL COMMENT '工号 登录名',
  `password` varchar(255) NOT NULL COMMENT '密码 插入时需加密 例MD5加密',
  `name` varchar(10) NOT NULL COMMENT '姓名',
  `gender` SMALLINT DEFAULT 2 COMMENT '性别 0-男 1-女 2-未知',
  `academy_id` INT NOT NULL COMMENT '外键 所属学院ID',
  `state` SMALLINT DEFAULT 0 COMMENT '状态 例如 0：正常 1：封禁',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_account(`account`),
  FOREIGN KEY (academy_id) REFERENCES t_academy(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_teacher(id, account, password, name, gender, academy_id, state)
  VALUE
  (1, '20151001', '4QrcOUm6Wau+VuBX8g+IPg==', '甲老师', 0, 1, 0);
INSERT INTO t_teacher(id, account, password, name, gender, academy_id, state)
  VALUE
  (2, '20151002', '4QrcOUm6Wau+VuBX8g+IPg==', '乙老师', 1, 2, 0);
INSERT INTO t_teacher(id, account, password, name, gender, academy_id, state)
  VALUE
  (3, '20151003', '4QrcOUm6Wau+VuBX8g+IPg==', '丙老师', 0, 3, 0);
INSERT INTO t_teacher(id, account, password, name, gender, academy_id, state)
  VALUE
  (4, '20151004', '4QrcOUm6Wau+VuBX8g+IPg==', '丁老师', 1, 4, 0);
INSERT INTO t_teacher(id, account, password, name, gender, academy_id, state)
  VALUE
  (5, '20151005', '4QrcOUm6Wau+VuBX8g+IPg==', '戊老师', 0, 5, 0);

-- --------------------------------------
-- Table structure for `t_course` 课程总表
-- --------------------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 课程ID自增',
  `academy_id` INT NOT NULL COMMENT '外键 所属学院ID',
  `name` varchar(30) NOT NULL COMMENT '课程名',
  `state` SMALLINT DEFAULT 0 COMMENT '状态 例如 0：正常 1：停用',
  PRIMARY KEY (`id`),
  FOREIGN KEY (academy_id) REFERENCES t_academy(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_course(id, academy_id, name, state) VALUE (1, 1, '计算机基础', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (2, 2, '管理学', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (3, 3, '药学基础', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (4, 4, '机械制图', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (5, 5, '数学理论分析', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (6, 1, 'C语言基础', 0);
INSERT INTO t_course(id, academy_id, name, state) VALUE (7, 1, 'Java程序设计基础', 0);

-- --------------------------------------
-- Table structure for `t_course_code` 课程代码总表
-- --------------------------------------
DROP TABLE IF EXISTS `t_course_code`;
CREATE TABLE `t_course_code` (
  `course_code` INT NOT NULL AUTO_INCREMENT COMMENT '课程代码',
  `teacher_account` VARCHAR(16) NOT NULL  COMMENT '外键 上课教师工号',
  `teacher_name` VARCHAR(10) NOT NULL COMMENT '教师姓名',
  `course_id` INT NOT NULL COMMENT '外键 所属课程ID',
  `course_name` VARCHAR(30) NOT NULL COMMENT '所属课程名称',
  `grade_id` INT NOT NULL  COMMENT '外键 所属年级ID',
  `specialty_id` INT NOT NULL  COMMENT '外键 所属专业ID',
  `class_id` VARCHAR(255)  NOT NULL COMMENT '上课班级ID 例：1,2,3',
  `exports` VARCHAR(255) DEFAULT NULL COMMENT '移除这些学生 account',
  `contains` VARCHAR(255) DEFAULT NULL COMMENT '外加这些学生 account',
  `academic_year_id` INT NOT NULL COMMENT '外键 学年ID',
  `academic_year_name` VARCHAR(15) NOT NULL COMMENT '外键 学年名称',
  `semester` VARCHAR(4) DEFAULT NULL COMMENT '学期数 例：一、二、三',
  `state` SMALLINT DEFAULT 0 COMMENT '状态 例如 0：正常进行 1：正常停用 2：超时停用',
  PRIMARY KEY (`course_code`),
  FOREIGN KEY (teacher_account) REFERENCES t_teacher(account) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_id) REFERENCES t_course(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (grade_id) REFERENCES t_grade(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (specialty_id) REFERENCES t_specialty(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (academic_year_id) REFERENCES t_academic_year(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE t_course_code AUTO_INCREMENT=10001;
INSERT INTO t_course_code(course_code, teacher_account, teacher_name, course_id, course_name, grade_id, specialty_id, class_id, academic_year_id, academic_year_name, semester, state)
  VALUE
  (10001, 20151001, '甲老师', 1, '计算机基础', 2015, 1, '1,', 2017, '2017-2018', '二', 0);
INSERT INTO t_course_code(course_code, teacher_account, teacher_name, course_id, course_name, grade_id, specialty_id, class_id, academic_year_id, academic_year_name, semester, state)
  VALUE
  (10002, 20151002, '乙老师', 2, '管理学', 2015, 2, '3,', 2017, '2017-2018', '二', 0);


-- --------------------------------------
-- Table structure for `t_course_dtl` 课程详细总表
-- --------------------------------------
DROP TABLE IF EXISTS `t_course_dtl`;
CREATE TABLE `t_course_dtl` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 课程详细ID自增',
  `teacher_account` varchar(16) NOT NULL  COMMENT '上课教师工号',
  `teacher_name` VARCHAR(10) NOT NULL COMMENT '外键 教师名称',
  `course_id` INT NOT NULL COMMENT '外键 所属课程ID',
  `course_name` VARCHAR(30) NOT NULL COMMENT '外键 所属课程名称',
  `course_code` INT NOT NULL COMMENT '外键 课程代码，标识某老师的某一门课',
  `place` varchar(32) DEFAULT NULL COMMENT '上课地点',
  `date` DATE NOT NULL COMMENT '上课时间 单位（天） 例：2017-10-10',
  `academic_year_id` INT NOT NULL COMMENT '外键 学年ID',
  `academic_year_name` VARCHAR(15) NOT NULL COMMENT '外键 学年名称',
  `semester` VARCHAR(4) DEFAULT NULL COMMENT '学期数 例：一、二、三',
  `schedule_name` SMALLINT NOT NULL COMMENT '外键 上课时间 单位（节）',
  `state` SMALLINT DEFAULT 0 COMMENT '状态 例如 0：正常进行 1：正常停用 2：超时停用',
  PRIMARY KEY (`id`),
  FOREIGN KEY (teacher_account) REFERENCES t_teacher(account) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_id) REFERENCES t_course(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_code) REFERENCES t_course_code(course_code) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (academic_year_id) REFERENCES t_academic_year(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (schedule_name) REFERENCES t_schedule(name) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO t_course_dtl(id, teacher_account, teacher_name, course_id, course_name, course_code, place, date, academic_year_id, academic_year_name, semester, schedule_name, state)
  VALUE
  (1, 20151001, '甲老师', 1, '计算机基础', 10001, '第一教学楼C211', '2018-04-30', 2017, '2017-2018', '二', 1, 0);
INSERT INTO t_course_dtl(id, teacher_account, teacher_name, course_id, course_name, course_code, place, date, academic_year_id, academic_year_name, semester, schedule_name, state)
  VALUE
  (2, 20151002, '乙老师', 2, '管理学', 10002, '第一教学楼C211', '2018-04-30', 2017, '2017-2018', '二', 2, 0);
INSERT INTO t_course_dtl(id, teacher_account, teacher_name, course_id, course_name, course_code, place, date, academic_year_id, academic_year_name, semester, schedule_name, state)
  VALUE
  (3, 20151001, '甲老师', 1, '计算机基础', 10001, '第一教学楼C211', '2018-05-01', 2017, '2017-2018', '二', 1, 0);
INSERT INTO t_course_dtl(id, teacher_account, teacher_name, course_id, course_name, course_code, place, date, academic_year_id, academic_year_name, semester, schedule_name, state)
  VALUE
  (4, 20151002, '乙老师', 2, '管理学', 10002, '第一教学楼C211', '2018-05-01', 2017, '2017-2018', '二', 2, 0);

-- --------------------------------------
-- Table structure for `t_clocking` 考勤表
-- --------------------------------------
DROP TABLE IF EXISTS `t_clocking`;
CREATE TABLE `t_clocking`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 考勤表ID自增',
  `teacher_account` VARCHAR(16) NOT NULL COMMENT '外键 所属教师用户',
  `course_dtl_id` INT NOT NULL COMMENT '外键 所考勤的课程详情ID',
  `course_code` INT NOT NULL COMMENT '外键 课程代码，标识某一门课',
  `submit_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '教师提交时间',
  `longitude` DOUBLE NOT NULL COMMENT '教师经度',
  `latitude` DOUBLE NOT NULL COMMENT '教师纬度',
  PRIMARY KEY (`id`),
  FOREIGN KEY (teacher_account) REFERENCES t_teacher(account) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_dtl_id) REFERENCES t_course_dtl(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_code) REFERENCES t_course_code(course_code) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------
-- Table structure for `t_clocking_dtl` 详细考勤表
-- --------------------------------------
DROP TABLE IF EXISTS `t_clocking_dtl`;
CREATE TABLE `t_clocking_dtl`(
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '主键 考勤记录表ID自增',
  `clocking_id` INT NOT NULL COMMENT '外键 所属的考情表ID',
  `course_dtl_id` INT NOT NULL COMMENT '外键 所考勤的课程详情ID',
  `course_code` INT NOT NULL COMMENT '外键 课程代码，标识某一门课',
  `student_account` VARCHAR(16) NOT NULL COMMENT '外键 学生 account',
  `submit_time` DATETIME DEFAULT NULL COMMENT '学生提交时间',
  `mac` VARCHAR(17) DEFAULT NULL COMMENT '学生提交MAC地址',
  `longitude` DOUBLE DEFAULT NULL COMMENT '学生经度',
  `latitude` DOUBLE DEFAULT NULL COMMENT '学生纬度',
  `state` SMALLINT DEFAULT 2 COMMENT '状态 学生提交状态 例如 0：正常提交 1：迟到提交 2：未操作',
  PRIMARY KEY (`id`),
  FOREIGN KEY (clocking_id) REFERENCES t_clocking(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_dtl_id) REFERENCES t_course_dtl(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (course_code) REFERENCES t_course_code(course_code) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (student_account) REFERENCES t_student(account) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;