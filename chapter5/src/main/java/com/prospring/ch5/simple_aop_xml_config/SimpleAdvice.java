package com.prospring.ch5.simple_aop_xml_config;

import org.aspectj.lang.JoinPoint;

public class SimpleAdvice {
    public void before(JoinPoint joinPoint) {
        System.out.println("Before method " + joinPoint.getSignature().getDeclaringTypeName()
            + "." + joinPoint.getSignature().getName());
    }
}
