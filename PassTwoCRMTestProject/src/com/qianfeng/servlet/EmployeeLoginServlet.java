package com.qianfeng.servlet;

import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Integer rem = Integer.valueOf(req.getParameter("rem"));
        Employee employee = null;
        employee = employeeService.selectEmail(email);
        if (employee!=null){
            if (email.equals(employee.getEmail())&&password.equals(employee.getPassword())){
                if (rem.equals(1)){
                    req.getSession().setAttribute("login",true);
                    req.getSession().setAttribute("userInfo",employee);
                    Cookie cookie = new Cookie("login",employee.getPassword());
                    cookie.setMaxAge(7*24*60);
//                  cookie.setPath("/PassTwoCRMProject_war_exploded");
                    resp.addCookie(cookie);
                }else {
                    req.getSession().setAttribute("userInfo",employee);
                    Cookie cookie = new Cookie("login",employee.getPassword());
                    cookie.setMaxAge(0);
//                  cookie.setPath("/PassTwoCRMProject_war_exploded");
                    resp.addCookie(cookie);
                }
                System.out.println("登录成功");
                req.getRequestDispatcher("/HomeJsonListServlet?empId="+employee.getEmpId()).forward(req,resp);
            }else {
                resp.sendRedirect("index.jsp");
            }
        }else {
            resp.sendRedirect("index.jsp");
        }
    }
}
