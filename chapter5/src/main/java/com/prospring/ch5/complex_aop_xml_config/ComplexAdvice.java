package com.prospring.ch5.complex_aop_xml_config;

import com.prospring.ch5.name_match_method_pointcut.Guitar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class ComplexAdvice {
    public void beforeMethod(JoinPoint joinPoint, Guitar masterGuitar) {
        if ("Very cool brand".equals(masterGuitar.getBrand())) {
            System.out.println("before " + joinPoint.getSignature().getDeclaringTypeName()
                    + "." + joinPoint.getSignature().getName());
        }
    }

    public Object aroundAdvice(ProceedingJoinPoint pjp, Guitar masterGuitar) throws Throwable {
        System.out.println("Around before " + pjp.getSignature().getDeclaringTypeName()
                + "." + pjp.getSignature().getName() + ", brand - " + masterGuitar.getBrand());
        Object retVal = pjp.proceed();
        System.out.println("Around after " + pjp.getSignature().getDeclaringTypeName()
                + "." + pjp.getSignature().getName() + ", brand - " + masterGuitar.getBrand());
        return retVal;
    }
}
