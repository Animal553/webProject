package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeePasswordToUpdateServlet")
public class EmployeePasswordToUpdateServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer empId = Integer.valueOf(req.getParameter("empId"));
        String oldpwd = req.getParameter("oldpwd");
        String newpwd = req.getParameter("newpwd");
        String confirmpwd = req.getParameter("confirmpwd");


        if (oldpwd.equals(employeeService.selectEmployee(empId).getPassword())) {
            if (newpwd.equals(confirmpwd)) {
                employeeService.updateEmployeePassword(empId,confirmpwd);
                String str = "密码修改成功";
                req.setAttribute("newPassword",str);
                req.getRequestDispatcher("user_pwd.jsp").forward(req,resp);
            }else {
                String str = "对不起密码两次密码不一样";
                req.setAttribute("newPassword",str);
                req.getRequestDispatcher("user_pwd.jsp").forward(req,resp);
            }
        }else {
            String str = "对不起原密码不正确";
            req.setAttribute("newPassword",str);
            req.getRequestDispatcher("user_pwd.jsp").forward(req,resp);
        }
    }
}
