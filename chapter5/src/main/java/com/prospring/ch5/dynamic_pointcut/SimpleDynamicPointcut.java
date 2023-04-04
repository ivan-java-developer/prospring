package com.prospring.ch5.dynamic_pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Dynamic check " + method.getName());
        int n = ((Integer) args[0]);
        return n != 100;
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> {
            System.out.println("Static check class " + cls.getName());
            return cls == SampleBean.class;
        };
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for method " + method.getName());
        return "foo".equals(method.getName());
    }
}
