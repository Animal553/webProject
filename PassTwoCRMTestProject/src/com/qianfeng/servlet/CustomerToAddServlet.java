package com.qianfeng.servlet;

import com.qianfeng.entity.*;
import com.qianfeng.service.*;
import com.qianfeng.service.Impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CustomerToAddServlet")
public class CustomerToAddServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    LevelService levelService = new LevelServiceImpl();
    IndustryService industryService = new IndustryServiceImpl();
    SourceService sourceService = new SourceServletImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList3 = employeeService.selectEmployeeList();
        req.setAttribute("employeeList3",employeeList3);

        List<Level> levelList = levelService.getLevelList();
        req.setAttribute("levelList",levelList);

        List<Industry> industryList = industryService.selectIndustryList();
        req.setAttribute("industryList",industryList);

        List<Source> sourceList = sourceService.selectSourceList();
        req.setAttribute("sourceList",sourceList);
        req.getRequestDispatcher("/customer_add.jsp").forward(req,resp);
    }
}
