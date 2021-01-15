package com.qianfeng.servlet;

import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeeUserToUpdateServlet")
public class EmployeeUserToUpdateServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        Integer empId= Integer.valueOf(req.getParameter("empId"));
        String empName = req.getParameter("nickname");
        String email = req.getParameter("email");
        String info = req.getParameter("remark");
        employeeService.updateEmployeeEmail(empId,empName,email,info);

        req.getSession().setAttribute("userInfo",employeeService.selectEmployee(empId));
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/home.jsp");
    }
}
