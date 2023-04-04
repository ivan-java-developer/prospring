package com.prospring.ch5.after_returning;

import com.prospring.ch5.before.Singer;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("Method " + method.getName() + ", after returning");
    }

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new SimpleAfterReturningAdvice());
        proxyFactory.setTarget(new Singer());
        Singer proxy = (Singer) proxyFactory.getProxy();
        proxy.sing("yellow submarine");
    }
}
