package com.core.util.enums;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/13 14:48
 */
public enum StateEnum {
    OK(0, "OK"),
    FROZEN(1, "FROZEN"),



    STUDENT_SUBMIT_NORMAL(0, "正常提交"),
    STUDENT_SUBMIT_LATE(1, "迟到提交"),
    STUDENT_SUBMIT_DEFAULT(2, "未操作"),

    COURSE_CLOCKING_STATE_NORMAL(0, "正常提交"),
    COURSE_CLOCKING_STATE_LATE(1, "迟到提交"),
    COURSE_CLOCKING_STATE_DEFAULT(2, "未操作"),
    COURSE_CLOCKING_STATE_NOTHING(3, "未开启考勤"),

    COURSE_NORMAL(0, "正常使用"),
    COURSE_FROZEN(1, "正常停用"),

    ;

    private int code;

    private String msg;

    private StateEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StateEnum customContent(String customMsg){
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

}
