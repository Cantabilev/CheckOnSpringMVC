package com.cms.vo;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/9 18:16
 */
public class StudentVO {
    private Long id;
    private String account;         // 学生用户 登录名
    private String password;        // 密码 插入时需加密 例MD5加密
    private String name;            // 姓名
    private Integer gender;         // 性别 0-男 1-女 2-未知
    private Long gradeId;           // 所属年级ID
    private Long academyId;         // 所属学院ID
    private Long specialtyId;       // 所属专业ID
    private Long classId;           // 所属班级ID
    private Integer state;          // 状态 例如 0：正常 1：封禁
    private String gradeName;       // 所属年级名称
    private String academyName;     // 所属学院名称
    private String specialtyName;   // 所属专业名称
    private String specialtyCode;   // 所属专业代码
    private String className;       // 所属班级名称
    private String classCode;       // 所属班级代码

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

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
