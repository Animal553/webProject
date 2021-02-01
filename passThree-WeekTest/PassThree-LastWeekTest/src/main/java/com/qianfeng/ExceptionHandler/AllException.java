package com.qianfeng.ExceptionHandler;

import com.qianfeng.myException.MyselfException;
import com.qianfeng.page.Page$;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllException {

    @ResponseBody
    @ExceptionHandler(value = {MyselfException.class})
    public Page$ resultExcept(MyselfException e){
        e.printStackTrace();
        Page$ page$ = new Page$();

        page$.setCode(e.getCode());
        page$.setMsg(e.getMsg());
        return page$;
    }
}
