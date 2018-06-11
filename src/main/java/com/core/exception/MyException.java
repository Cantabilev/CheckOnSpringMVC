package com.core.exception;

import com.core.util.enums.ConstantEnum;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/4/18 15:52
 */
public class MyException extends Exception {

    private int code;
    private String msg;
    private String errorMsg;
    private ConstantEnum constantEnum = ConstantEnum.FAIL;

    public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public MyException(ConstantEnum constantEnum) {
        this.code = constantEnum.getCode();
        this.msg = constantEnum.getMsg();
        this.constantEnum = constantEnum;
    }

    public MyException(ConstantEnum constantEnum, String errorMsg) {
        this.code = constantEnum.getCode();
        this.msg = constantEnum.getMsg();
        this.errorMsg = errorMsg;
        this.constantEnum = constantEnum;
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ConstantEnum getConstantEnum() {
        return constantEnum;
    }

    public void setConstantEnum(ConstantEnum constantEnum) {
        this.constantEnum = constantEnum;
    }
}
