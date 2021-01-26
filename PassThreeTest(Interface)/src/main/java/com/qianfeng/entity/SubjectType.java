package com.qianfeng.entity;

public class SubjectType {
     private Integer subjectTypeId;
     private String subjectTypeName;

    public SubjectType() {
    }

    public SubjectType(Integer subjectTypeId, String subjectTypeName) {
        this.subjectTypeId = subjectTypeId;
        this.subjectTypeName = subjectTypeName;
    }

    public Integer getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(Integer subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    @Override
    public String toString() {
        return "SubjectType{" +
                "subjectTypeId=" + subjectTypeId +
                ", subjectTypeName='" + subjectTypeName + '\'' +
                '}';
    }
}
