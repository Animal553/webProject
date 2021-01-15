package com.qianfeng.servlet;

import com.qianfeng.service.CustomerService;
import com.qianfeng.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer customerId= Integer.valueOf(req.getParameter("customerId"));
        customerService.deleteCustomer(customerId);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/customer_list.jsp");
    }
}
