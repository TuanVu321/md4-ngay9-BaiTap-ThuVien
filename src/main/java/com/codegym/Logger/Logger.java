package com.codegym.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Arrays;

@Aspect
public class Logger {
    @AfterReturning(pointcut = "within(com.codegym.serviece.*.*)",returning = "result")
    public void log(JoinPoint joinPoint,Object result){
       String className = joinPoint.getTarget().getClass().getSimpleName();
       String method = joinPoint.getSignature().getName();
       String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("dang thuc hien class: "+ className+ " method: "+ method );
        System.out.println("chuoi thuc hien: "+args);
    }

}
