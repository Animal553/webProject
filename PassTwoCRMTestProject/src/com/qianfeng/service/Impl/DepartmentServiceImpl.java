package com.qianfeng.service.Impl;

import com.qianfeng.dao.DepartmentDao;
import com.qianfeng.dao.Impl.DepartmentDaoImpl;
import com.qianfeng.dto.Page;
import com.qianfeng.entity.Department;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.DepartmentService;
import com.qianfeng.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    DepartmentDao departmentDao = new DepartmentDaoImpl();
    @Override
    public Department selectDepartment(Integer deptId) {
        Connection connection = JDBCUtils.getConnection();
        Department department = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select dept_id as deptId , dept_name as deptName ,create_time as createTime from department where dept_id = ?";
            department = departmentDao.getDepartment(connection,sql,Department.class,deptId);
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
        return department;
    }

    @Override
    public List<Department> selectDepartmentList() {
        Connection connection = JDBCUtils.getConnection();
        List<Department> departmentList = null;
        try {
            connection.setAutoCommit(false);
            String sql = "select dept_id as deptId , dept_name as deptName ,create_time as createTime from department";
            departmentList = departmentDao.getDepartmentList(connection,sql,Department.class);
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
        return departmentList;
    }

    @Override
    public Page getDepartmentPage(Integer pageNo, Integer pageSize) {
        Page page = new Page();
        Connection connection = null;
        List<Department> departments = null;
        connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "select dept_id as deptId , dept_name as deptName ,create_time as createTime from department limit ?,?";
            departments = departmentDao.getDepartmentList(connection,sql,Department.class,(pageNo-1)*pageSize,pageSize);
            String sqlC = "select count(*) as count from department";
            Integer count = departmentDao.GetCount(connection,sqlC);
            page.setPageMessage(departments);
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
    public void addDepartment(Department department) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "insert into department(dept_id,dept_name,create_time) values(default,?,?)";
            departmentDao.addDepartment(connection,sql,department.getDeptName(),department.getCreateTime());
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
    public void deleteDepartment(Integer deptId) {
        Connection connection = JDBCUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "delete from department where dept_id = ?";
            departmentDao.deleteDepartment(connection,sql,deptId);
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
}
