package com.qianfeng.service;

import com.qianfeng.entity.ContractProduct;

import java.util.List;

public interface ContractProductService {
    List<ContractProduct> selectContractProductList();
    void addContractProduct(ContractProduct contractProduct);
    List<ContractProduct> selectContractProductList(Integer contractId);
}
