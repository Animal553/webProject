package com.qianfeng.myException;

import com.qianfeng.enums.WeekTestEnums;

public class MyselfException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyselfException(WeekTestEnums weekTestEnums) {
        this.code = weekTestEnums.getCode();
        this.msg = weekTestEnums.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyselfException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
