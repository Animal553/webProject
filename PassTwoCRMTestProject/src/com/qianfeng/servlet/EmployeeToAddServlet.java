package com.qianfeng.servlet;

import com.qianfeng.entity.Department;
import com.qianfeng.service.DepartmentService;
import com.qianfeng.service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/EmployeeToAddServlet")
public class EmployeeToAddServlet extends HttpServlet {
    DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departmentList = departmentService.selectDepartmentList();
        req.setAttribute("departmentList",departmentList);
        System.out.println(departmentList);
        req.getRequestDispatcher("/employee_add.jsp").forward(req,resp);
    }
}
