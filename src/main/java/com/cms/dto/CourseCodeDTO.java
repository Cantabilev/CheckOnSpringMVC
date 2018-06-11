package com.cms.dto;

import com.cms.domain.StudentInfo;

import java.util.List;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/4 18:05
 */
public class CourseCodeDTO {

    private Long courseCode;                    // courseCode 主键 ID
    private String teacherAccount;              // 上课教师工号
    private String teacherName;                 // 上课教师姓名
    private Long courseId;                      // 所属课程ID
    private String courseName;                  // 所属课程ID
    private Long gradeId;                       // 上课年级ID
    private List<Long> classIdList;             // 上课班级ID list 查询时使用
    private List<StudentInfo> studentInfoList;  // 上课学生 List 查询时 使用
    //private String classId;                     // 上课班级ID
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

    public List<Long> getClassIdList() {
        return classIdList;
    }

    public void setClassIdList(List<Long> classIdList) {
        this.classIdList = classIdList;
    }

    public List<StudentInfo> getStudentInfoList() {
        return studentInfoList;
    }

    public void setStudentInfoList(List<StudentInfo> studentInfoList) {
        this.studentInfoList = studentInfoList;
    }
}
