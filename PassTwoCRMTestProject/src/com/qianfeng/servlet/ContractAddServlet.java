package com.qianfeng.servlet;
import com.qianfeng.entity.Contract;
import com.qianfeng.entity.ContractProduct;
import com.qianfeng.service.ContractProductService;
import com.qianfeng.service.ContractService;
import com.qianfeng.service.Impl.ContractProductServiceImpl;
import com.qianfeng.service.Impl.ContractServiceImpl;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/ContractAddServlet")
public class ContractAddServlet extends HttpServlet {
    ContractService contractService = new ContractServiceImpl();
    ContractProductService contractProductService = new ContractProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建一个文件工厂
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        //设置文件的大小
        servletFileUpload.setFileSizeMax(1204*1024*1024);
        servletFileUpload.setSizeMax(1024*1024*1024);

        //对传入的值进行解析
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            Contract contract = new Contract();
            List<ContractProduct> contractProductList = new ArrayList<>();
            for (FileItem fileItem : fileItems) {
                //判断是否是普通字段
                if (fileItem.isFormField()){
                    String fileName = fileItem.getFieldName();
                    String fileValue = fileItem.getString("utf-8");
                    if (fileName.equals("contractName")){
                        contract.setContractName(fileValue);
                    }else if(fileName.equals("customerId")){
                        contract.setCustomerId(Integer.valueOf(fileValue));
                    }else if(fileName.equals("contractPrice")){
                        contract.setContractPrice(Double.parseDouble(fileValue));
                    }else if(fileName.equals("customerSign")){
                        contract.setCustomerSign(fileValue);
                    }else if(fileName.equals("empId")){
                        contract.setCompanySignId(Integer.valueOf(fileValue));
                    }else if(fileName.equals("empId1")){
                        contract.setChargeId(Integer.valueOf(fileValue));
                    }else if(fileName.equals("empId2")){
                        contract.setApproval1Id(Integer.valueOf(fileValue));
                    }else if(fileName.equals("empId3")){
                        contract.setApproval2Id(Integer.valueOf(fileValue));
                    }else if(fileName.equals("startTime")){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(fileValue);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        contract.setStartTime(date);
                    }else if(fileName.equals("endTime")){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = null;
                        try {
                            date = simpleDateFormat.parse(fileValue);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        contract.setEndTime(date);
                    }else if (fileName.equals("productInfo")){

                        //获取前端传来的产品数据，切割成数组的形式，前面的是产品编号，后面的是产品的数量
                        String [] split = fileValue.split("=");
                        ContractProduct contractProduct = new ContractProduct();
                        contractProduct.setProductId(Integer.valueOf(split[0]));
                        contractProduct.setCount(Integer.valueOf(split[1]));
                        contractProductList.add(contractProduct);
                    }
                }else {
                    String realPath = getServletContext().getRealPath("/files");
                    File file = new File(realPath);
                    if (!file.exists()){
                        file.mkdirs();
                    }
                    //获取文件格式名
                    String name = fileItem.getName();
                    String lastStyle = name.substring(name.lastIndexOf("."),name.length());
                    String fileName = System.currentTimeMillis()+lastStyle;
                    try {
                        fileItem.write(new File(file+"/"+fileName));
                        String filePath = "files/"+fileName;
                        contract.setFilePath(filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            contract.setStatus(0);
            int i = contractService.addAndReturn(contract);
            //将合同的主键加到contractProduct的合同字段中
            for (ContractProduct contractProduct : contractProductList) {
                contractProduct.setContractId(i);
                contractProductService.addContractProduct(contractProduct);
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/PassTwoCRMTestProject_war_exploded/contract_list.jsp");
    }
}
