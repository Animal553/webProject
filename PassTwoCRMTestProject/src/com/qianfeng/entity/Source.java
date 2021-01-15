package com.qianfeng.entity;

/**
 * 客户来源表
 */
public class Source {

    private Integer sourceId;
    private String sourceName;

    public Source() {
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return "Source{" +
                "sourceId=" + sourceId +
                ", sourceName='" + sourceName + '\'' +
                '}';
    }
}
