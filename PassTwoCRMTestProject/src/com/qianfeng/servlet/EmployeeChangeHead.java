package com.qianfeng.servlet;

import com.qianfeng.entity.Employee;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmployeeChangeHead")
public class EmployeeChangeHead extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        EmployeeService employeeService = new EmployeeServiceImpl();

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setFileSizeMax(1024*1024*1024);
        servletFileUpload.setSizeMax(1024*1024*1024);
        try {
            List<FileItem> fileItem= servletFileUpload.parseRequest(req);
            for (FileItem item : fileItem) {

                //普通字段
                if (item.isFormField()){
                    //获取文件名，或者数据名
                    String fileName = item.getFieldName();
                    String fileValue = item.getString("utf-8");
                    if (fileName.equals("empId")){
                        System.out.println("."+fileValue+".");
                        employee = employeeService.selectEmployee(Integer.valueOf(fileValue));
                    }
                }else {
                    //文件
                    String realPath = getServletContext().getRealPath("/files");
                    System.out.println(realPath);
                    File file = new File(realPath);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    //获取文件格式
                    String name = item.getName();
                    String lastStyle = name.substring(name.lastIndexOf("."),name.length());
                    String fileName = System.currentTimeMillis()+lastStyle;
                    item.write(new File(file+"/"+fileName));
                    String portrait = "files/"+fileName;
                    System.out.println(portrait);
                    employee.setPortrait(portrait);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        employeeService.updateEmployeeHead(employee.getEmpId(),employee.getPortrait());
        req.getSession().setAttribute("userInfo",employeeService.selectEmployee(employee.getEmpId()));
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/home.jsp");
    }
}
