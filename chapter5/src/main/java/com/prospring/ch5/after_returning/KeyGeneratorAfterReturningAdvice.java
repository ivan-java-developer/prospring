package com.prospring.ch5.after_returning;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.prospring.ch5.after_returning.KeyGenerator.WEAK_KEY;

public class KeyGeneratorAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if (target instanceof KeyGenerator && "getKey".equals(method.getName())) {
            long retVal = (long) returnValue;
            if (retVal == WEAK_KEY) {
                throw new SecurityException("KeyGenerator returns weak key, try generate new one");
            }
        }
    }
}