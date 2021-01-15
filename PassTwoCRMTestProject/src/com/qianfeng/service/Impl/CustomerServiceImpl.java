package com.qianfeng.service.Impl;

import com.qianfeng.dao.*;
import com.qianfeng.dao.Impl.*;
import com.qianfeng.dto.Page;
import com.qianfeng.entity.*;
import com.qianfeng.service.CustomerService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    SourceDao sourceDao = new SourceDaoImpl();
    IndustryDao industryDao = new IndustryDaoImpl();
    LevelDao levelDao = new LevelDaoImpl();
    @Override
    public void addCustomer(Customer customer) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into customer(customer_id,customer_name,phone,status,charge_id,create_id,source_id,industry_id,level_id,create_time,next_time) values(default,?,?,?,?,?,?,?,?,?,?)";
            customerDao.addCustomer(connection,sql,customer.getCustomerName(),customer.getPhone(),customer.getStatus(),customer.getChargeId(),customer.getCreateId(),customer.getSourceId(),customer.getIndustryId(),customer.getLevelId(),customer.getCreateTime(),customer.getNextTime());
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
    public void deleteCustomer(Integer customerId) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "delete from customer where customer_id = ?";
            customerDao.deleteCustomer(connection,sql,customerId);
            connection.commit();
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
    public void updateCustomer(Customer customer) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update customer set customer_name = ?,phone=?,charge_id=?,create_id=?,source_id=?,industry_id=?,level_id=?,update_time=?,next_time=?,status=? where customer_id = ?";
            employeeDao.updateEmployee(connection,sql,customer.getCustomerName(),customer.getPhone(),customer.getChargeId(),customer.getCreateId(),customer.getSourceId(),customer.getIndustryId(),customer.getLevelId(),customer.getUpdateTime(),customer.getNextTime(),customer.getStatus(),customer.getCustomerId());
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
    public Page getCustomerPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Customer> customers = null;
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId ,customer_name as customerName,phone,status,charge_id as chargeId,create_id as createId,source_id as sourceId,industry_id as industryId,level_id as levelId,create_time as createTime,update_time as updateTime,next_time as nextTime from customer limit ?,?";
            customers = customerDao.getCustomerList(connection,sql,Customer.class,(pageNo-1)*pageSize,pageSize);
            for (Customer customer : customers) {
                if (customer.getStatus()==1){
                    customer.setStatusName("已签约");
                }else {
                    customer.setStatusName("未签约");
                }
            }
            String sqlCharge = "select emp_name as empName from employee where emp_id = ?";
            String sqlSource = "select source_name as sourceName from source where source_id = ?";
            String sqlIndustry = "select industry_name as industryName from industry where industry_id = ?";
            String sqlLevel = "select level_name as levelName from level where level_id = ?";
            for (Customer customer : customers) {
                Employee employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getChargeId());
                customer.setChargeEmpName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getCreateId());
                customer.setCreateEmpName(employee.getEmpName());

                Source source = sourceDao.getSource(connection,sqlSource,Source.class,customer.getSourceId());
                customer.setSourceName(source.getSourceName());

                Industry industry = industryDao.getIndustry(connection,sqlIndustry,Industry.class,customer.getIndustryId());
                customer.setIndustryName(industry.getIndustryName());

                Level level = levelDao.getLevel(connection,sqlLevel,Level.class,customer.getLevelId());
                customer.setLevelName(level.getLevelName());
            }

            String sqlC = "select count(*) as count from customer";
            Integer count = customerDao.GetCount(connection,sqlC);
            page.setPageMessage(customers);
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
    public Page getThreadPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Customer> customers = null;
        List<Customer> customers1 = new ArrayList<>();
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId ,customer_name as customerName,phone,status,charge_id as chargeId,create_id as createId,source_id as sourceId,industry_id as industryId,level_id as levelId,create_time as createTime,update_time as updateTime,next_time as nextTime from customer where status=0 limit ?,?";
            customers = customerDao.getCustomerList(connection,sql,Customer.class,(pageNo-1)*pageSize,pageSize);
            String sqlCharge = "select emp_name as empName from employee where emp_id = ?";
            String sqlSource = "select source_name as sourceName from source where source_id = ?";
            String sqlIndustry = "select industry_name as industryName from industry where industry_id = ?";
            String sqlLevel = "select level_name as levelName from level where level_id = ?";
            for (Customer customer : customers) {
                Employee employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getChargeId());
                customer.setChargeEmpName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getCreateId());
                customer.setCreateEmpName(employee.getEmpName());

                Source source = sourceDao.getSource(connection,sqlSource,Source.class,customer.getSourceId());
                customer.setSourceName(source.getSourceName());

                Industry industry = industryDao.getIndustry(connection,sqlIndustry,Industry.class,customer.getIndustryId());
                customer.setIndustryName(industry.getIndustryName());

                Level level = levelDao.getLevel(connection,sqlLevel,Level.class,customer.getLevelId());
                customer.setLevelName(level.getLevelName());
            }
            Integer count =customers.size();
            page.setPageMessage(customers);
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
    public Page getInternationalPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Customer> customers = null;
        List<Customer> customers1 = new ArrayList<>();
        Date date = new Date();
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId ,customer_name as customerName,phone,status,charge_id as chargeId,create_id as createId,source_id as sourceId,industry_id as industryId,level_id as levelId,create_time as createTime,update_time as updateTime,next_time as nextTime from customer where status=1 and next_time<? limit ?,?";
            customers = customerDao.getCustomerList(connection,sql,Customer.class,date,(pageNo-1)*pageSize,pageSize);
            for (Customer customer : customers) {
                if (customer.getStatus()==1){
                    customer.setStatusName("已签约");
                }else {
                    customer.setStatusName("未签约");
                }
            }
            String sqlCharge = "select emp_name as empName from employee where emp_id = ?";
            String sqlSource = "select source_name as sourceName from source where source_id = ?";
            String sqlIndustry = "select industry_name as industryName from industry where industry_id = ?";
            String sqlLevel = "select level_name as levelName from level where level_id = ?";
            for (Customer customer : customers) {
                Employee employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getChargeId());
                customer.setChargeEmpName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getCreateId());
                customer.setCreateEmpName(employee.getEmpName());

                Source source = sourceDao.getSource(connection,sqlSource,Source.class,customer.getSourceId());
                customer.setSourceName(source.getSourceName());

                Industry industry = industryDao.getIndustry(connection,sqlIndustry,Industry.class,customer.getIndustryId());
                customer.setIndustryName(industry.getIndustryName());

                Level level = levelDao.getLevel(connection,sqlLevel,Level.class,customer.getLevelId());
                customer.setLevelName(level.getLevelName());
            }
            Integer count =customers.size();
            page.setPageMessage(customers);
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
    public List<Customer> selectCustomerList() {
        List<Customer> customerList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId ,customer_name as customerName,phone,status,charge_id as chargeId,create_id as createId,source_id as sourceId,industry_id as industryId,level_id as levelId,create_time as createTime,update_time as updateTime,next_time as nextTime from customer";
            customerList = customerDao.getCustomerList(connection,sql,Customer.class);
            for (Customer customer : customerList) {
                if (customer.getStatus()==1){
                    customer.setStatusName("已签约");
                }else {
                    customer.setStatusName("未签约");
                }
            }
            String sqlCharge = "select emp_name as empName from employee where emp_id = ?";
            String sqlSource = "select source_name as sourceName from source where source_id = ?";
            String sqlIndustry = "select industry_name as industryName from industry where industry_id = ?";
            String sqlLevel = "select level_name as levelName from level where level_id = ?";
            for (Customer customer : customerList) {
                Employee employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getChargeId());
                customer.setChargeEmpName(employee.getEmpName());

                employee = employeeDao.getEmployee(connection,sqlCharge,Employee.class,customer.getCreateId());
                customer.setCreateEmpName(employee.getEmpName());

                Source source = sourceDao.getSource(connection,sqlSource,Source.class,customer.getSourceId());
                customer.setSourceName(source.getSourceName());

                Industry industry = industryDao.getIndustry(connection,sqlIndustry,Industry.class,customer.getIndustryId());
                customer.setIndustryName(industry.getIndustryName());

                Level level = levelDao.getLevel(connection,sqlLevel,Level.class,customer.getLevelId());
                customer.setLevelName(level.getLevelName());
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
        return customerList;
    }

    @Override
    public Customer selectCustomer(Integer customerId) {
        Connection connection = JDBCUtils.getConnection();
        Customer customer = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId ,customer_name as customerName,phone,status,charge_id as chargeId,create_id as createId,source_id as sourceId,industry_id as industryId,level_id as levelId,create_time as createTime,update_time as updateTime,next_time as nextTime from customer where customer_id = ?";
            customer = customerDao.getCustomer(connection,sql,Customer.class,customerId);
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
        return customer;
    }
}

