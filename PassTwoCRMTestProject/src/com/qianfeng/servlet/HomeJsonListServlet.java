package com.qianfeng.servlet;

import com.qianfeng.entity.Contract;
import com.qianfeng.entity.HomePageInformation;
import com.qianfeng.service.ContractService;
import com.qianfeng.service.HomePageInformationService;
import com.qianfeng.service.Impl.ContractServiceImpl;
import com.qianfeng.service.Impl.HomePageInformationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HomeJsonListServlet")
public class HomeJsonListServlet extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    HomePageInformationService homePageInformationService = new HomePageInformationServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //bootstrap的数据
        HomePageInformation homePageInformation = new HomePageInformation();
        homePageInformation.setIncome(homePageInformationService.selectIncome());
        homePageInformation.setMothIncome(homePageInformationService.selectMothIncome());
        homePageInformation.setNowUserIncrease(homePageInformationService.selectNowUserIncrease());
        homePageInformation.setCapital(homePageInformationService.selectCapital());
        homePageInformation.setUserIncrease(homePageInformationService.selectUserIncrease());
        homePageInformation.setRegister(homePageInformationService.selectRegister());
        req.getSession().setAttribute("homePageInformation",homePageInformation);

        //任务列表的数据
        List<Contract> contractList = new ArrayList<>();
        Integer empId = Integer.valueOf(req.getParameter("empId"));
        contractList = contractService.selectContractAsApproval(empId);
        req.getSession().setAttribute("contractList1",contractList);
        req.getRequestDispatcher("/HomeServlet").forward(req,resp);
    }
}
