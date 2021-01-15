package com.qianfeng.service.Impl;

import com.qianfeng.dao.*;
import com.qianfeng.dao.Impl.*;
import com.qianfeng.dto.Page;
import com.qianfeng.entity.*;
import com.qianfeng.service.ContractService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractServiceImpl implements ContractService {
    ContractDao contractDao = new ContractDaoImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    CustomerDao  customerDao = new CustomerDaoImpl();
    ContractProductDao contractProductDao = new ContractProductDaoImpl();
    ProductDao productDao = new ProductDaoImpl();
    @Override
    public void addContract(Contract contract) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into contract(contract_id,contract_name,contract_price,customer_sign,conmpany_sign_id,customer_id,charge_id,approval1_id,approval2_id,start_time,end_time,status,file_path) values(default,?,?,?,?,?,?,?,?,?,?,?,?)";
            contractDao.addContract(connection,sql,contract.getContractName(),contract.getContractPrice(),contract.getCustomerSign(),contract.getCompanySignId(),contract.getCustomerId(),contract.getChargeId(),contract.getApproval1Id(),contract.getApproval2Id(),contract.getStartTime(),contract.getEndTime(),contract.getStatus(),contract.getFilePath());
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
    }

    @Override
    public void deleteContract(Integer contractId) {
        Connection connection = JDBCUtils.getConnection();
        try {
            //关闭外键约束
            String sqlK = "SET FOREIGN_KEY_CHECKS=0";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlK);
            preparedStatement.execute();
            connection.setAutoCommit(false);
            String sql = "delete from contract where contract_id = ?";
            contractDao.deleteContract(connection,sql,contractId);
            connection.commit();
            //打开外键约束
            String sqlG = "SET FOREIGN_KEY_CHECKS=1";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sqlG);
            preparedStatement1.execute();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
    }

    @Override
    public Page getContractPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Contract> contracts = new ArrayList<>();
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select contract_id as contractId ,contract_name as contractName,customer_id as customerId,contract_price as contractPrice,start_time as startTime,end_time as endTime,customer_sign as customerSign,conmpany_sign_id as companySignId,charge_id as chargeId,approval1_id as approval1Id,approval2_id as approval2Id,status, file_path as filePath from contract limit ?,?";
            contracts = contractDao.getContractList(connection,sql,Contract.class,(pageNo-1)*pageSize,pageSize);
            for (Contract contract : contracts) {
                if (contract.getStatus()==0){
                    contract.setStatusName("已提交");
                }else {
                    contract.setStatusName("已提交");
                }
            }
            String sqlCharge = "select emp_name as empName from employee where emp_id = ?";
            String sqlCustomer = "select customer_name as customerName from customer where customer_id =?";
            for (Contract contract : contracts) {
                Employee employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,contract.getCompanySignId());
                contract.setCompanySignName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,contract.getChargeId());
                contract.setChargeName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,contract.getApproval1Id());
                contract.setApproval1Name(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,contract.getApproval2Id());
                contract.setApproval2Name(employee.getEmpName());

                Customer customer = customerDao.getCustomer(connection,sqlCustomer,Customer.class,contract.getCustomerId());
                contract.setCustomerName(customer.getCustomerName());
            }

            String sqlC = "select count(*) as count from contract";
            Integer count = contractDao.GetCount(connection,sqlC);
            page.setPageMessage(contracts);
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setPageCount(count);
            int pageCount = (count%pageSize==0)?(count/pageSize):(count/pageSize)+1;
            page.setPageCount(pageCount);

            if (pageNo<=1){
                page.setHasPer(false);
            }else {
                page.setHasPer(true);
            }

            if (pageNo>=pageCount){
                page.setHasNext(false);
            }else {
                page.setHasNext(true);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return page;
    }

    @Override
    public Contract selectContract(Integer contractId) {
        Connection connection = JDBCUtils.getConnection();
        Contract contract = new Contract();
        List<ContractProduct> contractProductList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select contract_id as contractId ,contract_name as contractName,customer_id as customerId,contract_price as contractPrice,start_time as startTime,end_time as endTime,customer_sign as customerSign,conmpany_sign_id as companySignId,charge_id as chargeId,approval1_id as approval1Id,approval2_id as approval2Id,status, file_path as filePath from contract where contract_id = ?";
            contract = contractDao.getContract(connection,sql,Contract.class,contractId);

            String sqlA = "select product_id as productId , count from contract_product where contract_id = ?";
            contractProductList = contractProductDao.getContractProductList(connection,sqlA,ContractProduct.class,contractId);

            String sqlB = "select product_name as productName,price from product where product_id = ?";
            for (ContractProduct contractProduct : contractProductList) {
                Product product = productDao.getProduct(connection,sqlB,Product.class,contractProduct.getProductId());
                contractProduct.setProductName(product.getProductName());
                contractProduct.setPrice(product.getPrice());
            }
            contract.setContractProduct(contractProductList);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return contract;
    }

    @Override
    public int addAndReturn(Contract contract) {

        Connection connection = JDBCUtils.getConnection();
        int i = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "insert into contract(contract_id,contract_name,contract_price,customer_sign,conmpany_sign_id,customer_id,charge_id,approval1_id,approval2_id,start_time,end_time,status,file_path) values(default,?,?,?,?,?,?,?,?,?,?,?,?)";
            i =contractDao.addAndReturnContract(connection,sql,contract.getContractName(),contract.getContractPrice(),contract.getCustomerSign(),contract.getCompanySignId(),contract.getCustomerId(),contract.getChargeId(),contract.getApproval1Id(),contract.getApproval2Id(),contract.getStartTime(),contract.getEndTime(),contract.getStatus(),contract.getFilePath());
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return i;
    }

    @Override
    public List<Contract> selectContractAsApproval(Integer contractId) {
        Connection connection = JDBCUtils.getConnection();
        List<Contract> contractList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select contract_id as contractId ,contract_name as contractName,customer_id as customerId,contract_price as contractPrice,start_time as startTime,end_time as endTime,customer_sign as customerSign,conmpany_sign_id as companySignId,charge_id as chargeId,approval1_id as approval1Id,approval2_id as approval2Id,status, file_path as filePath from contract where approval1_id = ? or approval2_id = ?";
            contractList = contractDao.getContractList(connection,sql,Contract.class,contractId,contractId);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
        return contractList;
    }

    @Override
    public void updateContractStatus(Integer contractId, Integer status) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update contract set status = ? where contract_id = ?";
            contractDao.addContract(connection,sql,status,contractId);
            connection.commit();
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            JDBCUtils.close();
        }
    }
}
