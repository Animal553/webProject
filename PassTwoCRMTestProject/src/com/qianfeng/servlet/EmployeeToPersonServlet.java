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

@WebServlet("/EmployeeToPersonServlet")
public class EmployeeToPersonServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer empId = Integer.valueOf(req.getParameter("empId"));
        Employee employee = new Employee();
        employee = employeeService.selectEmployee(empId);
        req.setAttribute("employee",employee);
        req.getRequestDispatcher("/user_info.jsp").forward(req,resp);
    }
}
