package com.alejandro.validation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class ClassAspect {
	
    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}
    
    @Around("publicMethod() && @annotation(com.alejandro.validation.annotation.Utils)")
    public Object LogExecutionTimeByMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println( " -> ");
        return joinPoint.proceed();
    }

    @Around("publicMethod() && @within(com.alejandro.validation.annotation.Utils)")
    public Object LogExecutionTimeByClass(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println( " -> " );
        return joinPoint.proceed();
    }
    
}
