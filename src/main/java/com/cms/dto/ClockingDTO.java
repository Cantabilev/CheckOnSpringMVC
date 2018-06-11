package com.cms.dto;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/14 15:41
 */
public class ClockingDTO {

    private Long id;                        // 考情表ID
    private Long courseDtlId;               // 所考勤的课程详情ID
    private Long courseCode;                // 所考勤的课程代码
    private Date date;
    private String place;
    private Integer scheduleName;

    private String teacherAccount;          // 教师用户
    private String teacherName;             // 教师姓名
    private Long courseId;
    private String courseName;

    private Date submitTime;                // 教师提交时间

    private String dateStr;
    private String submitTimeStr;

    private Integer stateNormalCount;
    private Integer stateLateCount;
    private Integer stateDefaultCount;

    private boolean ing;                    // 是否正在进行

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(Integer scheduleName) {
        this.scheduleName = scheduleName;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getStateNormalCount() {
        return stateNormalCount;
    }

    public void setStateNormalCount(Integer stateNormalCount) {
        this.stateNormalCount = stateNormalCount;
    }

    public Integer getStateLateCount() {
        return stateLateCount;
    }

    public void setStateLateCount(Integer stateLateCount) {
        this.stateLateCount = stateLateCount;
    }

    public Integer getStateDefaultCount() {
        return stateDefaultCount;
    }

    public void setStateDefaultCount(Integer stateDefaultCount) {
        this.stateDefaultCount = stateDefaultCount;
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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getSubmitTimeStr() {
        return submitTimeStr;
    }

    public void setSubmitTimeStr(String submitTimeStr) {
        this.submitTimeStr = submitTimeStr;
    }

    public boolean isIng() {
        return ing;
    }

    public void setIng(boolean ing) {
        this.ing = ing;
    }
}
