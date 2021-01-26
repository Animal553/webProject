package com.qianfeng.entity;

public class WorkType {

    private Integer workTypeId;
    private String workTypeName;

    public WorkType() {
    }

    public WorkType(Integer workTypeId, String workTypeName) {
        this.workTypeId = workTypeId;
        this.workTypeName = workTypeName;
    }

    public Integer getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(Integer workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName) {
        this.workTypeName = workTypeName;
    }

    @Override
    public String toString() {
        return "WorkType{" +
                "workTypeId=" + workTypeId +
                ", workTypeName='" + workTypeName + '\'' +
                '}';
    }
}
