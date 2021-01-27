package com.qianfeng.exception;

import com.qianfeng.Page.Result;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.log4j.Logger;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class AllException {

    private final Logger logger = Logger.getLogger(Exception.class);

    @ResponseBody
    @ExceptionHandler(value = {MyException.class})
    public Result resultExcept(MyException e){
        logger.error("注意：产生异常"+e.getMessage(),e);

        Result result = new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Result resultExcept(Exception e){
        logger.error("注意：产生异常"+e.getMessage(),e);
        Result result = new Result();
        if (e instanceof UnauthenticatedException){
            result.setCode(501);
            result.setMsg("未认证异常");
            return result;
        } else if (e instanceof UnauthorizedException){
            result.setCode(502);
            result.setMsg("权限异常");
            return result;
        }
        result.setCode(208);
        result.setMsg("其他异常");
        return result;
    }
}
