package com.qianfeng.exception;

import com.qianfeng.enums.MyEnums;

public class MyException extends RuntimeException {

    private Integer code;
    private String msg;

    public MyException(MyEnums myEnums) {
        this.code = myEnums.getCode();
        this.msg = myEnums.getMsg();
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
        return "MyException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
