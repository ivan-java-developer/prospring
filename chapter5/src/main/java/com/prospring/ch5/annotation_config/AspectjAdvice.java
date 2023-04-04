package com.prospring.ch5.annotation_config;

import com.prospring.ch5.name_match_method_pointcut.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class AspectjAdvice {
    @Pointcut("execution(* *sing(com.prospring.ch5.name_match_method_pointcut.Guitar)) && args(guitar)")
    public void singable(Guitar guitar) {
    }

    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    @Before("singable(guitar) && isJohn()")
    public void beforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        System.out.println("Before advice " + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());
    }

    @Around("singable(guitar) && isJohn()")
    public Object aroundAdvice(ProceedingJoinPoint pjp, Guitar guitar) throws Throwable {
        System.out.println("Around before " + pjp.getSignature().getDeclaringTypeName()
                + "." + pjp.getSignature().getName() + ", brand - " + guitar.getBrand());
        Object retVal = pjp.proceed();
        System.out.println("Around after " + pjp.getSignature().getDeclaringTypeName()
                + "." + pjp.getSignature().getName() + ", brand - " + guitar.getBrand());
        return retVal;
    }
}
