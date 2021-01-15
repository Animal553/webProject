package com.qianfeng.servlet;

import com.qianfeng.entity.Category;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.CategoryService;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.CategoryServiceImpl;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ProductToAddServlet")
public class ProductToAddServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();

        employeeList = employeeService.selectEmployeeList();
        categoryList = categoryService.selectCategoryList();
        req.setAttribute("createPeople",employeeList);
        req.setAttribute("categoryList",categoryList);

        req.getRequestDispatcher("/product_add.jsp").forward(req,resp);
    }
}
