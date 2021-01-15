package com.qianfeng.service.Impl;

import com.qianfeng.dao.ContractDao;
import com.qianfeng.dao.CustomerDao;
import com.qianfeng.dao.Impl.ContractDaoImpl;
import com.qianfeng.dao.Impl.CustomerDaoImpl;
import com.qianfeng.entity.Contract;
import com.qianfeng.entity.Customer;
import com.qianfeng.service.HomePageInformationService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomePageInformationServiceImpl implements HomePageInformationService {
    CustomerDao customerDao = new CustomerDaoImpl();
    ContractDao contractDao = new ContractDaoImpl();

    @Override
    public double selectIncome() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        date = calendar.getTime();
        Connection connection = JDBCUtils.getConnection();
        List<Customer> customerList = new ArrayList<>();
        List<Contract> contractList = new ArrayList<>();
        double num = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId from customer where create_time > ?";
            customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
            connection.commit();
            String sqlA = "select contract_price as contractPrice from contract where customer_id = ? ";
            for (Customer customer : customerList) {
                contractList = contractDao.getContractList(connection,sqlA,Contract.class,customer.getCustomerId());
                for (Contract contract : contractList) {
                    num+=contract.getContractPrice();
                }
            }
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
        return num;
    }

    @Override
    public double selectMothIncome() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.MONTH,-1);
        date = calendar.getTime();
        Connection connection = JDBCUtils.getConnection();
        List<Customer> customerList = new ArrayList<>();
        List<Contract> contractList = new ArrayList<>();
        double num = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId from customer where create_time > ?";
            customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
            connection.commit();
            String sqlA = "select contract_price as contractPrice from contract where customer_id = ? ";
            for (Customer customer : customerList) {
                contractList = contractDao.getContractList(connection,sqlA,Contract.class,customer.getCustomerId());
                for (Contract contract : contractList) {
                    num+=contract.getContractPrice();
                }
            }
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
        return num;
    }

    @Override
    public int selectUserIncrease() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        date = calendar.getTime();
        Connection connection = JDBCUtils.getConnection();
        List<Customer> customerList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId from customer where create_time > ?";
            customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
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
        return customerList.size();
    }

    @Override
    public int selectNowUserIncrease() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        calendar.set(Calendar.MONTH,-1);
        date = calendar.getTime();
        Connection connection = JDBCUtils.getConnection();
        List<Customer> customerList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select customer_id as customerId from customer where create_time > ?";
            customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
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
        return customerList.size();
    }

    @Override
    public List<Integer> selectRegister() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <=7 ; i++) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            calendar.set(Calendar.HOUR,-168*i);
            date = calendar.getTime();
            Connection connection = JDBCUtils.getConnection();
            List<Customer> customerList = new ArrayList<>();
            try {
                connection.setAutoCommit(false);
                String sql = "select customer_id as customerId from customer where create_time > ?";
                customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
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
            list.add(customerList.size());
        }
        return list;
    }

    @Override
    public List<Double> selectCapital() {
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <=7 ; i++) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            calendar.set(Calendar.HOUR,-168*i);
            date = calendar.getTime();
            Connection connection = JDBCUtils.getConnection();
            List<Customer> customerList = new ArrayList<>();
            List<Contract> contractList = new ArrayList<>();
            double num = 0;
            try {
                connection.setAutoCommit(false);
                String sql = "select customer_id as customerId from customer where create_time > ?";
                customerList = customerDao.getCustomerList(connection,sql, Customer.class,date);
                connection.commit();
                String sqlA = "select contract_price as contractPrice from contract where customer_id = ? ";
                for (Customer customer : customerList) {
                    contractList = contractDao.getContractList(connection,sqlA,Contract.class,customer.getCustomerId());
                    for (Contract contract : contractList) {
                        num+=contract.getContractPrice();
                    }
                }
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
            list.add(num);
        }
        return list;
    }
}
