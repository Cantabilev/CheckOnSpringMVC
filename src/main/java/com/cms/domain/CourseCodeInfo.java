package com.cms.domain;

import com.core.dao.model.BaseEntity;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/1 12:29
 */
public class CourseCodeInfo extends BaseEntity {

    private Long courseCode;                    // courseCode 主键 ID
    private String teacherAccount;              // 上课教师工号
    private String teacherName;                 // 上课教师姓名
    private Long courseId;                      // 所属课程ID
    private String courseName;                  // 所属课程ID
    private Long gradeId;                       // 上课年级ID
    private Long specialtyId;                   // 上课专业ID
    private String classIds;                     // 上课班级ID
    private String exports="";                  // 移除这些学生 account
    private String contains="";                 // 外加这些学生 account
    private Long academicYearId;                // 学年ID
    private String academicYearName;            // 学年名
    private String semester;                    // 学期数
    private Integer state;                      // 状态 例如 0：正常 1：停用

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public String getTeacherAccount() {
        return teacherAccount;
    }

    public void setTeacherAccount(String teacherAccount) {
        this.teacherAccount = teacherAccount;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
