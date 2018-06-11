package com.core.util.enums;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/3/22 16:15
 */
public enum  ConstantEnum{

    OK(0, "OK"),
    FAIL(1, "FAIL"),

    SYS_ERROR(1001, "系统错误"),

    PARAM_FAIL(2001, "参数错误"),
    PARAM_NULL(2002, "空参数"),
    TARGET_NULL(2003, "目标不存在"),
    OPERATE_NOT_MATCHING(2004, "操作不匹配"),

    LOGIN_FAIL_PASSWORD(3001, "密码错误"),
    LOGIN_FAIL_ACCOUNT(3002, "用户不存在"),
    LOGIN_FAIL_TYPE(3003, "无效类型"),
    ACCOUNT_FROZEN(3004, "账号不可用"),

    COURSE_NULL(4001, "课程不存在"),
    STUDENT_REPEAT_ADD(4002, "重复添加该学生"),
    STUDENT_REPEAT_DELETE(4003, "重复删除该学生"),
    STUDENT_NOT_IN_COURSE(4004, "学生不在课程中"),
    STUDENT_NULL(4005, "学生不存在"),
    TEACHER_NULL(4006, "教师不存在"),
    PARAM_LACK(4007, "缺少必要参数"),
    COURSE_CODE_NULL(4008, "课程代码不存在"),
    CLOCKING_LACK_STUDENT(4009, "没有学生，请求考勤失败"),
    CLOCKING_DTL_NULL(4010, "签到目标不存在，签到失败"),
    CLOCKING_DISTANCE_FAR(4011, "距离过远，签到失败"),
    CLOCKING_TIME_FAR(4012, "时间已超过，签到失败"),
    CLOCKING_MAC_MULTIPLE(4013, "同一MAC多次签到，签到失败"),
    CLOCKING_NORMAL(0, "签到成功"),
    CLOCKING_LATE(0, "签到成功 - 迟到签到"),
    CLOCKING_MODIFY_STATE(4014, "修改失败-无效状态"),
    CLOCKING_MODIFY_STATE_SAME(4015, "状态重复修改"),
    CLOCKING_QUERY(4016, "查询失败，没有相关课程，或相关课程没有开启考勤"),
    CLOCKING_REPEAT(4017, "失败， 该课程已经考勤过"),





    OUT_OF_MAX_INDEX(9001, "最大容量超限"),
    GRADE_ROLE(9002, "年级不合规则超限"),
    ;

    private int code;

    private String msg;

    private ConstantEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ConstantEnum customContent(String customMsg){
//		this.code = code;
        this.msg = customMsg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    class Constants{

        public static final String PASSWORD_LEN_MIN = "6";
        public static final String PASSWORD_LEN_MAX = "8";
        public static final String CURRENT_OPENID = "currentOpenid";

    }
}
