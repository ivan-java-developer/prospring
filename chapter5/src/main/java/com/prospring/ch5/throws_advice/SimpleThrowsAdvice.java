package com.prospring.ch5.throws_advice;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleThrowsAdvice());
        proxyFactory.setTarget(new ErrorBean());
        ErrorBean errorBean = (ErrorBean) proxyFactory.getProxy();

        try {
            errorBean.errorProneMethod();
        } catch (Exception ignored) {

        }

        try {
            errorBean.otherErrorProneMethod();
        } catch (IllegalArgumentException ignored) {

        }
    }

    public void afterThrowing(Exception ex) {
        System.out.println("*****");
        System.out.println("Generic exception");
        System.out.println("Throwing exception " + ex.getClass().getName());
        System.out.println("message " + ex.getMessage());
        System.out.println("*****");
    }

//    public void afterThrowing(IllegalArgumentException ex) {
//        System.out.println("*****");
//        System.out.println("Concrete exception");
//        System.out.println("Throwing exception " + ex.getClass().getName());
//        System.out.println("message " + ex.getMessage());
//        System.out.println("*****");
//    }

    public void afterThrowing(Method method, Object[] args, Object target, IllegalArgumentException ex) {
        System.out.println("*****");
        System.out.println("Throwing exception " + ex.getClass().getName());
        System.out.println("method " + method.getName());
        System.out.println("message " + ex.getMessage());
        System.out.println("*****");
    }
}
