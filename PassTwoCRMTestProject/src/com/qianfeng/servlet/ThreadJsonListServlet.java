package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qianfeng.dto.Page;
import com.qianfeng.service.CustomerService;
import com.qianfeng.service.Impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ThreadJsonListServlet")
public class ThreadJsonListServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        //在页面上拿到当前page是第几页和page的最大容量
        String pn  = req.getParameter("pageNo");
        String ps = req.getParameter("pageSize");
        if (pn==null||pn==""||pn.length()<=0){
            pn = "1";
        }
        if (ps==null||ps==""||ps.length()<=0){
            ps="2";
        }
        Page page = customerService.getThreadPage(Integer.parseInt(pn),Integer.parseInt(ps));
        String json = JSON.toJSONStringWithDateFormat(page,"yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        resp.getWriter().write(json);
    }
}
