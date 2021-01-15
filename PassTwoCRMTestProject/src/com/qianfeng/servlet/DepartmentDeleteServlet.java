package com.qianfeng.servlet;

import com.qianfeng.service.DepartmentService;
import com.qianfeng.service.Impl.DepartmentServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DepartmentDeleteServlet")
public class DepartmentDeleteServlet extends HttpServlet {
    DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer deptId= Integer.valueOf(req.getParameter("deptId"));
        departmentService.deleteDepartment(deptId);
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/department_list.jsp");
    }
}
