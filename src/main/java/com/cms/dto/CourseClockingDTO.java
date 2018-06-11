package com.cms.dto;


import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/13 22:34
 */
public class CourseClockingDTO {
    private Long id;                            // courseDtlId
    private String teacherAccount;              // 上课教师工号
    private String teacherName;                 // 上课教师姓名
    private Long courseId;                      // 所属课程ID
    private String courseName;                  // 所属课程名称
    private Long courseCode;                    // 课程代码

    private String place;                       // 上课地点
    private Date date;                          // 上课时间
    private Integer scheduleName;               // 上课节数

    private Integer clockingState;              // 状态 例如 0：正常签到 1：迟到签到 2：未操作 3：未开启考勤

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

    public Integer getClockingState() {
        return clockingState;
    }

    public void setClockingState(Integer clockingState) {
        this.clockingState = clockingState;
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

    public Integer getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(Integer scheduleName) {
        this.scheduleName = scheduleName;
    }
}
