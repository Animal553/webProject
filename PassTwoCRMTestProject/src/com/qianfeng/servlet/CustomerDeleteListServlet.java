package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.qianfeng.service.CustomerService;
import com.qianfeng.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CustomerDeleteListServlet")
public class CustomerDeleteListServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
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
                customerService.deleteCustomer(Integer.valueOf(s));
            }
            String json = JSON.toJSONString(1);
            resp.getWriter().write(json);
        }else {
            resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/customer_list.jsp");
        }
    }
}
