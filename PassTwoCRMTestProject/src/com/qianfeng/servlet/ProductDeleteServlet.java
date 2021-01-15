package com.qianfeng.servlet;

import com.qianfeng.service.Impl.ProductServiceImpl;
import com.qianfeng.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer productId = Integer.valueOf(req.getParameter("productId"));
        productService.deleteProduct(productId);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/product_list.jsp");
    }
}
