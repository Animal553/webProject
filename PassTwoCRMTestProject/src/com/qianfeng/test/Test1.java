package com.qianfeng.test;
import com.qianfeng.entity.ContractProduct;
import com.qianfeng.service.ContractProductService;
import com.qianfeng.service.Impl.ContractProductServiceImpl;
import com.qianfeng.service.Impl.ProductServiceImpl;
import com.qianfeng.service.ProductService;
import org.junit.jupiter.api.Test;
import com.qianfeng.service.EmployeeService;
import com.qianfeng.service.Impl.EmployeeServiceImpl;
import sun.util.resources.CalendarData;

import javax.servlet.ServletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Test1 {
   private EmployeeService employeeService  = new EmployeeServiceImpl();
    private ServletContext servletContext;
    private ContractProductService contractProductService = new ContractProductServiceImpl();


    @Test
   public void test01(){
       System.out.println(employeeService.selectEmployee(1021).getPassword());
   }




    @Test
   public void test02(){
       Date date = new Date();
       System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(simpleDateFormat.parse(simpleDateFormat.format(date)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test03(){
        List<ContractProduct> contractProductList = contractProductService.selectContractProductList();
        for (ContractProduct contractProduct : contractProductList) {
            System.out.println(contractProduct.getProductId());
        }
    }

    @Test
    public void test04(){
        Date date = new Date();
        //将时间放到日历对象中
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //将时分秒，毫秒设置为0
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        //将设置后的时间，直接赋值给date
        date = calendar.getTime();
        //打印date
        System.out.println(date);
    }
}
