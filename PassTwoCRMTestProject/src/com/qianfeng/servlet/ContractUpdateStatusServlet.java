package com.qianfeng.servlet;

import com.qianfeng.service.CategoryService;
import com.qianfeng.service.ContractService;
import com.qianfeng.service.Impl.CategoryServiceImpl;
import com.qianfeng.service.Impl.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ContractUpdateStatusServlet")
public class ContractUpdateStatusServlet extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empId= req.getParameter("empId");
        Integer contractId = Integer.valueOf(req.getParameter("contractId"));
        Integer status = Integer.valueOf(req.getParameter("status"));
        contractService.updateContractStatus(contractId,status);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/HomeJsonListServlet?empId="+empId);
    }
}
