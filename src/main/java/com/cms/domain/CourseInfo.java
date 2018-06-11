package com.cms.domain;

import com.core.dao.model.BaseEntity;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 15:52
 *
 * 课程总表bean
 */
// TODO BaseEntity 课程总表bean
public class CourseInfo extends BaseEntity {

    private Long id;
    private Integer academyId;      // 所属学院ID
    private String name;            // 课程名
    private Integer state;          // 状态 例如 0：正常 1：停用

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
