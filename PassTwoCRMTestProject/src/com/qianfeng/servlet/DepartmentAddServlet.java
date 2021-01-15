package com.qianfeng.servlet;

import com.qianfeng.entity.Department;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.DepartmentService;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.DepartmentServiceImpl;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/DepartmentAddServlet")
public class DepartmentAddServlet extends HttpServlet {
    DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Department department = new Department();
        department.setDeptName(req.getParameter("deptName"));
        String createDate = req.getParameter("createTime");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(createDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        department.setCreateTime(date);
        departmentService.addDepartment(department);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/department_list.jsp");
    }
}
