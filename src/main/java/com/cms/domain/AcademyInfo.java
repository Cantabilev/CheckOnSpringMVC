package com.cms.domain;

import com.core.dao.model.BaseEntity;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/7 15:26
 *
 * 学院表bean
 */
public class AcademyInfo extends BaseEntity{
    private Integer id;
    private String name;        // 学院名称，例 计算机科学与技术学院

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
