package com.prospring.ch5.first_pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before invocation method " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("Done!");
        return retVal;
    }
}
