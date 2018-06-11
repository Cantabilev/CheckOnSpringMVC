package com.cms.domain;

import com.core.dao.model.BaseEntity;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 16:04
 *
 * 考勤表bean
 */
// TODO BaseEntity 考勤表bean
public class ClockingInfo extends BaseEntity {

    private Long id;
    private String teacherAccount;          // 所属教师用户
    private Long courseDtlId;               // 所考勤的课程详情ID
    private Long courseCode;                // 所考勤的课程代码
    private Date submitTime;                // 教师提交时间
    private Double longitude;               // 教师经度
    private Double latitude;                // 教师纬度

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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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
}
