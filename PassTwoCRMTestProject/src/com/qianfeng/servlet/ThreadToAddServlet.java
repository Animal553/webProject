package com.qianfeng.servlet;

import com.qianfeng.entity.Employee;
import com.qianfeng.entity.Industry;
import com.qianfeng.entity.Level;
import com.qianfeng.entity.Source;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;
import com.qianfeng.service.Impl.IndustryServiceImpl;
import com.qianfeng.service.Impl.LevelServiceImpl;
import com.qianfeng.service.Impl.SourceServletImpl;
import com.qianfeng.service.IndustryService;
import com.qianfeng.service.LevelService;
import com.qianfeng.service.SourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ThreadToAddServlet")
public class ThreadToAddServlet extends HttpServlet {
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
        req.getRequestDispatcher("/thread_add.jsp").forward(req,resp);
    }
}
