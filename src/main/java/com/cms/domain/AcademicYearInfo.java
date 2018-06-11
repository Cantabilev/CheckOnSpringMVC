package com.cms.domain;

import com.core.dao.model.BaseEntity;

import java.util.Date;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/20 14:55
 */
public class AcademicYearInfo extends BaseEntity {
    private Long id;
    private String name;
    private Date semesterOneStart;
    private Date semesterOneEnd;
    private Date semesterTwoStart;
    private Date semesterTwoEnd;


    private String semesterOneStartStr;
    private String semesterOneEndStr;
    private String semesterTwoStartStr;
    private String semesterTwoEndStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSemesterOneStart() {
        return semesterOneStart;
    }

    public void setSemesterOneStart(Date semesterOneStart) {
        this.semesterOneStart = semesterOneStart;
    }

    public Date getSemesterOneEnd() {
        return semesterOneEnd;
    }

    public void setSemesterOneEnd(Date semesterOneEnd) {
        this.semesterOneEnd = semesterOneEnd;
    }

    public Date getSemesterTwoStart() {
        return semesterTwoStart;
    }

    public void setSemesterTwoStart(Date semesterTwoStart) {
        this.semesterTwoStart = semesterTwoStart;
    }

    public Date getSemesterTwoEnd() {
        return semesterTwoEnd;
    }

    public void setSemesterTwoEnd(Date semesterTwoEnd) {
        this.semesterTwoEnd = semesterTwoEnd;
    }


    public String getSemesterOneStartStr() {
        return semesterOneStartStr;
    }

    public void setSemesterOneStartStr(String semesterOneStartStr) {
        this.semesterOneStartStr = semesterOneStartStr;
    }

    public String getSemesterOneEndStr() {
        return semesterOneEndStr;
    }

    public void setSemesterOneEndStr(String semesterOneEndStr) {
        this.semesterOneEndStr = semesterOneEndStr;
    }

    public String getSemesterTwoStartStr() {
        return semesterTwoStartStr;
    }

    public void setSemesterTwoStartStr(String semesterTwoStartStr) {
        this.semesterTwoStartStr = semesterTwoStartStr;
    }

    public String getSemesterTwoEndStr() {
        return semesterTwoEndStr;
    }

    public void setSemesterTwoEndStr(String semesterTwoEndStr) {
        this.semesterTwoEndStr = semesterTwoEndStr;
    }
}
