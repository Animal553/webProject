package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.qianfeng.service.DepartmentService;
import com.qianfeng.service.Impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DepartmentDeleteListServlet")
public class DepartmentDeleteListServlet extends HttpServlet {
    DepartmentService departmentService = new DepartmentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] array = req.getParameterValues("array[]");
        if (array!=null){
            for (String s : array) {
                System.out.println(s);
                departmentService.deleteDepartment(Integer.valueOf(s));
            }
            String json = JSON.toJSONString(1);
            resp.getWriter().write(json);
        }else {
            resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/department_list.jsp");
        }
    }
}
