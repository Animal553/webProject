package com.qianfeng.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qianfeng.dto.Page;
import com.qianfeng.service.ContractService;
import com.qianfeng.service.Impl.ContractServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ContractJsonListServlet")
public class ContractJsonListServlet extends HttpServlet {
    private ContractService contractService =new ContractServiceImpl();
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

        //判断页面上的数据有没有
        //如果没有就直接讲pn和ps赋一个默认值
        if (pn==null||pn==""||pn.length()<=0){
            pn = "1";
        }
        if (ps==null||ps==""||ps.length()<=0){
            ps="2";
        }
        //调用service里面的页面的service来设置前端页面的数据
        Page page = contractService.getContractPage(Integer.parseInt(pn),Integer.parseInt(ps));
        //以json的形式发送给前端页面
        String json = JSON.toJSONStringWithDateFormat(page,"yyyy-MM-dd", SerializerFeature.WriteDateUseDateFormat);
        resp.getWriter().write(json);
    }
}
