package com.cms.domain;

import com.core.dao.model.BaseEntity;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 15:30
 *
 * 专业表bean
 */
// TODO BaseEntity 专业表bean
public class SpecialtyInfo extends BaseEntity {

    private Long id;
    private Integer academyId;      // 所属学院ID
    private String name;            // 专业名称，例 物联网工程
    private String specialtyCode;   // 专业代码，例 0802

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

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }
}
