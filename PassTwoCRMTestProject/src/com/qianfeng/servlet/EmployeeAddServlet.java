package com.qianfeng.servlet;

import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
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

@WebServlet("/EmployeeAddServlet")
public class EmployeeAddServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Employee employee = new Employee();
        employee.setEmpName(req.getParameter("empName"));
        employee.setAge(Integer.valueOf(req.getParameter("age")));
        employee.setDeptId(Integer.valueOf(req.getParameter("deptId")));
        String hireDate = req.getParameter("hireDate");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = simpleDateFormat.parse(hireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setHireDate(date);
        employeeService.addEmployee(employee);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/employee_list.jsp");
    }
}
