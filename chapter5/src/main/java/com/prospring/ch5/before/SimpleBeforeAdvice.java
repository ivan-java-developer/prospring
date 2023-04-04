package com.prospring.ch5.before;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("You try to invoke method " + method.getName());
        System.out.println("Method args:");
        Arrays.stream(args).forEach(System.out::println);
        System.out.println(target);
        args[0] = "Hack method argument";
    }

    public static void main(String[] args) {
        Singer singer = new Singer();
        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.setTarget(singer);
        Singer proxySinger = (Singer) pf.getProxy();
        proxySinger.sing("Happy New Year, Happy New Year");
    }
}
