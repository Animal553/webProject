package com.qianfeng.servlet;

import com.qianfeng.entity.Product;
import com.qianfeng.service.Impl.ProductServiceImpl;
import com.qianfeng.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ProductAddServlet")
public class ProductAddServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿前端页面数据
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Product product = new Product();
        product.setProductName(req.getParameter("productName"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setCreateId(Integer.valueOf(req.getParameter("createId")));
        product.setCategoryId(Integer.valueOf(req.getParameter("categoryId")));
        String createTime = req.getParameter("createTime");
        product.setStatus(Integer.valueOf(req.getParameter("status")));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        try {
           date =  simpleDateFormat.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setCreateTime(date);
        productService.addProduct(product);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/product_list.jsp");
    }
}
