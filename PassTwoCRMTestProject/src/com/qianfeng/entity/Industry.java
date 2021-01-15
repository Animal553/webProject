package com.qianfeng.entity;

/**
 * 客户行业表
 */
public class Industry {

    private Integer industryId;
    private String industryName;

    public Industry() {
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    @Override
    public String toString() {
        return "Industry{" +
                "industryId=" + industryId +
                ", industryName='" + industryName + '\'' +
                '}';
    }
}
