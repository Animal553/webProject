package com.qianfeng.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qianfeng.entity.User;
import com.qianfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Producer defaultKaptcha;
    @RequestMapping(value = "/")
    public String toIndex(){
        return "index";
    }

//    发送验证码
    @RequestMapping(value = "/getVc",method = RequestMethod.GET)
    public void getVc(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //禁用浏览器和jsp页面的缓存
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setContentType("image/jpeg");

        String kaptchaText = defaultKaptcha.createText();

        System.out.println(kaptchaText);
//        将验证码加入到session域中
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,kaptchaText);
        BufferedImage kaptchaImage = defaultKaptcha.createImage(kaptchaText);
        ServletOutputStream outputStream = response.getOutputStream();

        ImageIO.write(kaptchaImage,"jpg",outputStream);
    }

    @RequestMapping(value = "/login")
    public String login(String userName,String password , String code, HttpServletRequest request){
        User user = new User(userName, password);
        if (code.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)))
        {
            User selectUser = userService.selectUser(user);

            if (userName==null||password==null||userName==""||password==null){
                request.setAttribute("msg","对不起用户名或者密码不正确");
                return "index";
            }
            if (userName.equals(selectUser.getUserName())&&password.equals(selectUser.getPassword())){
                return "loginSuccess";
            }
        }else {
            request.setAttribute("msg","对不起用户名或者密码不正确");
            return "index";
        }
        return "index";
    }
}
