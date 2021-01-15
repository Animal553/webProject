package com.qianfeng.servlet;

import com.alibaba.excel.EasyExcel;
import com.qianfeng.Template.EmployeeTemplate;
import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ExcelEmployeeDownServlet")
public class ExcelEmployeeDownServlet extends HttpServlet {
    EmployeeService employeeService = new EmployeeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应数据的数据格式和响应的编码
        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");
        //设置下载文件的名称
        String fileName = "employee.xlsx";

        //告诉浏览器这是下载的文件
        resp.setHeader("Content-disposition","attachment;filename="+fileName);

        List<EmployeeTemplate> employeeTemplateList = new ArrayList<>();
        List<Employee> employeeList = employeeService.selectEmployeeList();
        for (Employee employee : employeeList) {
            EmployeeTemplate employeeTemplate = new EmployeeTemplate();
            employeeTemplate.setEmpId(employee.getEmpId());
            employeeTemplate.setEmpName(employee.getEmpName());
            employeeTemplate.setAge(employee.getAge());
            employeeTemplate.setDeptName(employee.getDepartment().getDeptName());
            employeeTemplate.setHireDate(employee.getHireDate());
            employeeTemplate.setUpdateTime(employee.getUpdateTime());
            employeeTemplateList.add(employeeTemplate);
        }
        System.out.println(employeeTemplateList);
        //将文件以流的形式写出去
        EasyExcel.write(resp.getOutputStream(),EmployeeTemplate.class).sheet().doWrite(employeeTemplateList);
    }
}
