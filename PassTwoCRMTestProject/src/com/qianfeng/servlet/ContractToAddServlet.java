package com.qianfeng.servlet;

import com.qianfeng.entity.ContractProduct;
import com.qianfeng.entity.Customer;
import com.qianfeng.entity.Employee;
import com.qianfeng.entity.Product;
import com.qianfeng.service.ContractProductService;
import com.qianfeng.service.CustomerService;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.ContractProductServiceImpl;
import com.qianfeng.service.Impl.CustomerServiceImpl;
import com.qianfeng.service.Impl.EmployeeServiceImpl;
import com.qianfeng.service.Impl.ProductServiceImpl;
import com.qianfeng.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ContractToAddServlet")
public class ContractToAddServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();
    ContractProductService contractProductService = new ContractProductServiceImpl();
    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList4 = employeeService.selectEmployeeList();
        req.setAttribute("employeeList4",employeeList4);

        List<Customer> customerList4 = customerService.selectCustomerList();
        req.setAttribute("customerList4",customerList4);

        List<Product> productList4 = productService.selectProductList();
        req.setAttribute("productList4",productList4);
        req.getRequestDispatcher("/contract_add.jsp").forward(req,resp);
    }
}
