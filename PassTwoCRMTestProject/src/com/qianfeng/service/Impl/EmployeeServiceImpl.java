package com.qianfeng.service.Impl;
import com.qianfeng.dao.DepartmentDao;
import com.qianfeng.dao.EmployeeDao;
import com.qianfeng.dao.Impl.DepartmentDaoImpl;
import com.qianfeng.dao.Impl.EmployeeDaoImpl;
import com.qianfeng.dto.Page;
import com.qianfeng.entity.Customer;
import com.qianfeng.entity.Department;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao = new EmployeeDaoImpl();
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    @Override
    public void addEmployee(Employee employee) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into employee(emp_id,emp_name,age,dept_id,hire_date,update_time,email, password,portrait,info) values(default,?,?,?,?,default,?,?,default,default)";
            employeeDao.addEmployee(connection,sql,employee.getEmpName(),employee.getAge(),employee.getDeptId(),employee.getHireDate(),"null","null");
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
    public void deleteEmployee(Integer empId) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "delete from employee where emp_id = ?";
            employeeDao.deleteEmployee(connection,sql,empId);
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
    public void updateEmployee(Employee employee) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update employee set emp_id = ?,emp_name = ?,age=?,create_id=?,dept_id=?,hire_date=?,update_time=?,email=? , password=?, portrait=?,info=? where emp_id = ? ";
            employeeDao.updateEmployee(connection,sql,employee.getEmpId(),employee.getEmpName(),employee.getAge(),employee.getDeptId(),employee.getHireDate(),employee.getUpdateTime(),employee.getEmail(),employee.getPassword(),employee.getPortrait(),employee.getInfo(),employee.getEmpId());
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
    public Employee selectEmployee(Integer empId) {
        Connection connection = JDBCUtils.getConnection();
        Employee employee = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select emp_id as empId , emp_name as empName , age , dept_id as deptId , hire_date as hireDate ,update_time as updateTime , email, password, portrait,info from employee where emp_id = ?";
            employee = employeeDao.getEmployee(connection,sql,Employee.class,empId);
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
        return employee;
    }

    @Override
    public Employee selectEmail(String email) {
        Connection connection = JDBCUtils.getConnection();
        Employee employee = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select emp_id as empId , emp_name as empName , age , dept_id as deptId , hire_date as hireDate ,update_time as updateTime , email, password, portrait,info from employee where email = ?";
            employee = employeeDao.getEmployee(connection,sql,Employee.class,email);
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
        return employee;
    }

    @Override
    public List<Employee> selectEmployeeList() {
        List<Employee> employeeList = null;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select emp_id as empId , emp_name as empName , age , dept_id as deptId , hire_date as hireDate, update_time as updateTime , email, password, portrait,info from employee";
            employeeList = employeeDao.getEmployeeList(connection,sql,Employee.class);

            String sqlD = "select dept_id as deptId , dept_name as deptName ,create_time as createTime  from department where dept_id = ?";
            for (Employee employee : employeeList) {
                Department department = departmentDao.getDepartment(connection,sqlD,Department.class,employee.getDeptId());
                employee.setDepartment(department);
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
        return employeeList;
    }

    @Override
    public Integer employeeCount() {
        Integer count = 0;
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select count(*) as count from employee";
            int i =employeeDao.GetCount(connection,sql);
            connection.commit();
            if (i>0){
                count = i;
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
        return count;
    }
    @Override
    public Page getEmployeePage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Employee> employees = null;
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select emp_id as empId , emp_name as empName , age , dept_id as deptId , hire_date as hireDate, update_time as updateTime , email, password, portrait,info from employee limit ?,?";
            employees = employeeDao.getEmployeeList(connection,sql,Employee.class,(pageNo-1)*pageSize,pageSize);
            String sqlD = "select dept_id as deptId ,dept_name as deptName ,create_time as createTime from department where dept_id = ?";
            for (Employee employee : employees) {
                Department department = departmentDao.getDepartment(connection,sqlD,Department.class,employee.getDeptId());
                employee.setDepartment(department);
            }

            String sqlC = "select count(*) as count from employee";
            Integer count = employeeDao.GetCount(connection,sqlC);
            page.setPageMessage(employees);
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
    public List<Employee> selectEmployeeList(String name) {
        Connection connection = JDBCUtils.getConnection();
        List<Employee> employeeList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = "select emp_id as empId , emp_name as empName , age , dept_id as deptId , hire_date as hireDate, update_time as updateTime , email, password, portrait,info from employee where emp_name like ? ";
            employeeList = employeeDao.getEmployeeList(connection,sql,Employee.class,name);

            String sqlD = "select dept_id as deptId ,dept_name as deptName ,create_time as createTime from department where dept_id = ?";
            for (Employee employee : employeeList) {
                Department department = departmentDao.getDepartment(connection,sqlD,Department.class,employee.getDeptId());
                employee.setDepartment(department);
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
        return employeeList;
    }

    @Override
    public void updateEmployeeEmail(Integer empId , String empName , String email , String info) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update employee set emp_name = ?,email=?,info=? where emp_id = ? ";
            employeeDao.updateEmployee(connection,sql,empName,email,info,empId);
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
    public void updateEmployeePassword(Integer empId, String password) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update employee set password = ? where emp_id = ? ";
            employeeDao.updateEmployee(connection,sql,password,empId);
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
    public void updateEmployeeHead(Integer empId, String portrait) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "update employee set portrait = ? where emp_id = ? ";
            employeeDao.updateEmployee(connection,sql,portrait,empId);
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
