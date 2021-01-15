package com.qianfeng.entity;

import java.util.Date;

public class Customer {
    private Integer customerId;
    private String customerName;
    private String phone;
    private Integer status;
    private Integer chargeId;
    private Integer createId;
    private Integer sourceId;
    private Integer industryId;
    private Integer levelId;
    private Date createTime;
    private Date updateTime;
    private Date nextTime;
    private String statusName;
    private String chargeEmpName;
    private String createEmpName;
    private String sourceName;
    private String industryName;
    private String levelName;


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getChargeEmpName() {
        return chargeEmpName;
    }

    public void setChargeEmpName(String chargeEmpName) {
        this.chargeEmpName = chargeEmpName;
    }

    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", chargeId=" + chargeId +
                ", createId=" + createId +
                ", sourceId=" + sourceId +
                ", industryId=" + industryId +
                ", levelId=" + levelId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", nextTime=" + nextTime +
                ", statusName='" + statusName + '\'' +
                ", chargeEmpName='" + chargeEmpName + '\'' +
                ", createEmpName='" + createEmpName + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", industryName='" + industryName + '\'' +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
