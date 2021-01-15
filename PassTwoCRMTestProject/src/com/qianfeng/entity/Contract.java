package com.qianfeng.entity;

import java.util.Date;

/**
 * 合同表
 */
public class Contract {

    private Integer contractId;
    private String contractName;
    private Integer customerId;
    private double contractPrice;
    private Date startTime;
    private Date endTime;
    private String customerSign;//客户签约人
    private Integer companySignId;//公司签约人
    private Integer chargeId;//合同负责人
    private Integer approval1Id;//审批人1关联员工主键
    private Integer approval2Id;//审批人2关联员工主键
    private Integer status;//合同状态0: 已创建,待审核 1: 审批人1通过 2:审批人1未通过 3:审批人2通过 4: 审批人2未通过
    private String statusName;
    private String customerName;
    private String companySignName;
    private String chargeName;
    private String approval1Name;
    private String approval2Name;
    private String filePath;
    private Object contractProduct;

    public Object getContractProduct() {
        return contractProduct;
    }

    public void setContractProduct(Object contractProduct) {
        this.contractProduct = contractProduct;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanySignName() {
        return companySignName;
    }

    public void setCompanySignName(String companySignName) {
        this.companySignName = companySignName;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getApproval1Name() {
        return approval1Name;
    }

    public void setApproval1Name(String approval1Name) {
        this.approval1Name = approval1Name;
    }

    public String getApproval2Name() {
        return approval2Name;
    }

    public void setApproval2Name(String approval2Name) {
        this.approval2Name = approval2Name;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Contract() {
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public double getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(double contractPrice) {
        this.contractPrice = contractPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    public Integer getCompanySignId() {
        return companySignId;
    }

    public void setCompanySignId(Integer companySignId) {
        this.companySignId = companySignId;
    }

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public Integer getApproval1Id() {
        return approval1Id;
    }

    public void setApproval1Id(Integer approval1Id) {
        this.approval1Id = approval1Id;
    }

    public Integer getApproval2Id() {
        return approval2Id;
    }

    public void setApproval2Id(Integer approval2Id) {
        this.approval2Id = approval2Id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", contractName='" + contractName + '\'' +
                ", customerId=" + customerId +
                ", contractPrice=" + contractPrice +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", customerSign='" + customerSign + '\'' +
                ", companySignId=" + companySignId +
                ", chargeId=" + chargeId +
                ", approval1Id=" + approval1Id +
                ", approval2Id=" + approval2Id +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", companySignName='" + companySignName + '\'' +
                ", chargeName='" + chargeName + '\'' +
                ", approval1Name='" + approval1Name + '\'' +
                ", approval2Name='" + approval2Name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", contractProduct=" + contractProduct +
                '}';
    }
}
