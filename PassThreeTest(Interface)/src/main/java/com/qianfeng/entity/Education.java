package com.qianfeng.entity;

public class Education {

    private Integer educationId;
    private String educationName;

    public Education() {
    }

    public Education(Integer educationId, String educationName) {
        this.educationId = educationId;
        this.educationName = educationName;
    }

    public Integer getEducationId() {
        return educationId;
    }

    public void setEducationId(Integer educationId) {
        this.educationId = educationId;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    @Override
    public String toString() {
        return "Education{" +
                "educationId=" + educationId +
                ", educationName='" + educationName + '\'' +
                '}';
    }
}
