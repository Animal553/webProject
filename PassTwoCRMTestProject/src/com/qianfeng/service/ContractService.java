package com.qianfeng.service;

import com.qianfeng.dto.Page;
import com.qianfeng.entity.Contract;
import com.qianfeng.entity.Customer;

import java.util.List;

public interface ContractService {
    void addContract(Contract contract);
    void deleteContract(Integer contractId);
    Page getContractPage(Integer pageNo, Integer pageSize);
    Contract selectContract(Integer contractId);
    int addAndReturn(Contract contract);
    List<Contract> selectContractAsApproval(Integer contractId);
    void updateContractStatus( Integer contractId ,Integer status);
}
