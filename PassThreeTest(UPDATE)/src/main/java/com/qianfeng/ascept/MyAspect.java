package com.qianfeng.ascept;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    private final Logger logger = Logger.getLogger(MyAspect.class);

    @Around("execution(public * com.qianfeng..*.*(..))")
    public Object methodAround(ProceedingJoinPoint joinPoint){
        Object r =0;
        System.out.println("---------");
        logger.info(joinPoint.getSignature().getName()+"被调用");
        try {
            r=joinPoint.proceed();
            logger.info(joinPoint.getSignature().getName()+"返回数据。");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.info(joinPoint.getSignature().getName()+"异常",throwable);
        }finally {
            logger.info(joinPoint.getSignature().getName()+"调用完成。");
        }
        return r;
    }
}
