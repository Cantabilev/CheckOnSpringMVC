package com.cms.domain;

import com.core.dao.model.BaseEntity;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 15:57
 *
 * 课程详细总表bean
 */
public class CourseDtlInfo extends BaseEntity {

    private Long id;
    private String teacherAccount;              // 上课教师工号
    private String teacherName;                 // 上课教师姓名
    private Long courseId;                      // 所属课程ID
    private String courseName;                  // 所属课程名称
    private Long courseCode;                    // 课程代码
    private String place;                       // 上课地点
    private Date date;                          // 上课时间 单位（天） 例：2017-10-10
    private Long academicYearId;                // 学年ID
    private String academicYearName;            // 学年名
    private String semester;                    // 学期数
    private Integer scheduleName;                // 上课时间 单位（节）
    private Integer state;                      // 状态 例如 0：正常 1：停用

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
}
