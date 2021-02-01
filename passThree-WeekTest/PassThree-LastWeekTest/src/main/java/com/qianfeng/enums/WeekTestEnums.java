package com.qianfeng.enums;

public enum WeekTestEnums {
    //200是成功的状态码
    ADD_ERROR(201,"添加失败"),
    DELETE_ERROR(202,"删除失败"),
    UPDATE_ERROR(203,"修改失败"),
    SELECT_ERROR(204,"查询失败")
    ;

    private Integer code;
    private String msg;

    WeekTestEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
        return "WeekTestEnums{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
