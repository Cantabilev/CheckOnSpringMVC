package com.cms.vo;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/16 15:59
 *
 * view_course_dtl
 */
public class CourseDtlVO {
    private Long id;
    private String teacherAccount;              // 上课教师工号
    private Integer courseId;                   // 所属课程ID
    private String courseName;                  // 所属课程名称
    private Long courseCode;                 // 课程代码
    private String place;                       // 上课地点
    private Date date;                          // 上课时间 单位（天） 例：2017-10-10
    private Long academicYearId;                // 学年ID
    private String academicYearName;            // 学年名
    private String semester;                    // 学期
    private Integer scheduleName;               // 上课时间 单位（节）
    private Integer state;                      // 状态 例如 0：正常 1：停用
    private String teacherName;                 // 教师姓名
    private String classIds;                    // 上课班级ID
    private String exports;                     // 除开
    private String contains;                    // 额外
    private Integer gradeId;                    // 上课年级ID
    private Integer specialtyId;                // 上课专业ID
    private String gradeName;                   // 年级名称
    private String specialtyName;               // 专业名称
    private String specialtyCode;               // 专业代码
    private Integer academyId;                  // 上课学院ID
    private String academyName;                 // 学院名称

    private String dateStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Long academicYearId) {
        this.academicYearId = academicYearId;
    }

    public String getAcademicYearName() {
        return academicYearName;
    }

    public void setAcademicYearName(String academicYearName) {
        this.academicYearName = academicYearName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(Integer scheduleName) {
        this.scheduleName = scheduleName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public String getExports() {
        return exports;
    }

    public void setExports(String exports) {
        this.exports = exports;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
