package com.cms.domain;

import com.core.dao.model.BaseEntity;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/30 16:16
 */
public class TeacherInfo extends BaseEntity {

    private Long id;
    private String account;             // 教室用户 登录名
    private String password;            // 密码 插入时需加密 例MD5加密
    private String name;                // 姓名
    private Integer gender;             // 性别 0-男 1-女 2-未知
    private Integer academyId;          // 所属学院ID
    private Integer state;              // 状态 例如 0：正常 1：封禁

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
