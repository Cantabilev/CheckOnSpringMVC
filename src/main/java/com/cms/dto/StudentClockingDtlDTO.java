package com.cms.dto;

import com.core.dao.model.BaseEntity;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 16:30
 *
 * 详细考勤表 bean
 */
public class StudentClockingDtlDTO extends BaseEntity {

    private Long id;
    private Long clockingId;                // 所属的考情表ID
    private Long courseDtlId;               // 所考勤的课程详情ID
    private Long courseCode;                // 所考勤的课程代码
    private Date date;                      // 课程开始时间
    private String place;                   // 课程地点
    private Integer scheduleName;           // 上课节数
    private String courseName;              // 课程名
    private String teacherAccount;
    private String teacherName;

    private String studentName;             // 学生姓名

    private String studentAccount;          // 学生 account
    private Date submitTime;                // 学生提交时间
    private String mac;                     // 学生提交MAC地址
    private Double longitude;               // 学生经度
    private Double latitude;                // 学生纬度
    private Integer state;                  // 状态 学生提交状态 例如 0：正常提交 1：迟到提交 2：未操作

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClockingId() {
        return clockingId;
    }

    public void setClockingId(Long clockingId) {
        this.clockingId = clockingId;
    }

    public Long getCourseDtlId() {
        return courseDtlId;
    }

    public void setCourseDtlId(Long courseDtlId) {
        this.courseDtlId = courseDtlId;
    }

    public Long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Long courseCode) {
        this.courseCode = courseCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(Integer scheduleName) {
        this.scheduleName = scheduleName;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
}
