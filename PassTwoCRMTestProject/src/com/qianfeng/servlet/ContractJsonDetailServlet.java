package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qianfeng.entity.Contract;
import com.qianfeng.entity.ContractProduct;
import com.qianfeng.service.ContractProductService;
import com.qianfeng.service.ContractService;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.ContractProductServiceImpl;
import com.qianfeng.service.Impl.ContractServiceImpl;
import com.qianfeng.service.Impl.EmployeeServiceImpl;
import com.qianfeng.service.Impl.ProductServiceImpl;
import com.qianfeng.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/ContractJsonDetailServlet")
public class ContractJsonDetailServlet extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //这时候就是将要查的合同的信息展示出来，还有合同所包含的产品信息
        //查询到当前的合同信息
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        Contract contract = new Contract();
        Integer contractId = Integer.valueOf(req.getParameter("contractId"));
        contract = contractService.selectContract(contractId);
        contract.setChargeName(employeeService.selectEmployee(contract.getChargeId()).getEmpName());
        contract.setApproval1Name(employeeService.selectEmployee(contract.getApproval1Id()).getEmpName());
        contract.setApproval2Name(employeeService.selectEmployee(contract.getApproval2Id()).getEmpName());

        String json = JSON.toJSONStringWithDateFormat(contract,"yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        resp.getWriter().write(json);
    }
}
