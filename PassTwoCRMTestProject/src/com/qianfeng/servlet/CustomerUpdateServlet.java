package com.qianfeng.servlet;

import com.qianfeng.entity.Customer;
import com.qianfeng.service.CustomerService;
import com.qianfeng.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
    CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        Customer customer = new Customer();
        customer.setCustomerId(Integer.valueOf(req.getParameter("customerId")));
        customer.setCustomerName(req.getParameter("customerName"));
        customer.setPhone(req.getParameter("phone"));
        customer.setChargeId(Integer.valueOf(req.getParameter("empId1")));
        customer.setCreateId(Integer.valueOf(req.getParameter("empId2")));
        customer.setSourceId(Integer.valueOf(req.getParameter("sourceId")));
        customer.setIndustryId(Integer.valueOf(req.getParameter("industryId")));
        customer.setLevelId(Integer.valueOf(req.getParameter("levelId")));
        customer.setStatus(Integer.valueOf(req.getParameter("e")));
        String updateTime = req.getParameter("updateTime");
        String nextTime = req.getParameter("nextTime");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date date1 = new Date();
        try {
            date = simpleDateFormat.parse(nextTime);
            date1 = simpleDateFormat.parse(updateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        customer.setUpdateTime(date1);
        customer.setNextTime(date);
        System.out.println(customer);
        customerService.updateCustomer(customer);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/customer_list.jsp");
    }
}
