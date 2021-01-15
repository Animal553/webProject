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

@WebServlet("/EmployeeDeleteListServlet")
public class EmployeeDeleteListServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] array = req.getParameterValues("array[]");
        if (array!=null){
            for (String s : array) {
                System.out.println(s);
                employeeService.deleteEmployee(Integer.valueOf(s));
            }
            String json = JSON.toJSONString(1);
            resp.getWriter().write(json);
        }else {
            resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/employee_list.jsp");
        }
    }
}
