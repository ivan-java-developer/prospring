package com.prospring.ch5.first_pointcut;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class SimplePointcutDemo {
    public static void main(String[] args) {
        Singer one = new GoodGuitarist();
        Singer two = new GreatGuitarist();

        Singer proxyOne;
        Singer proxyTwo;

        ProxyFactory pf = new ProxyFactory();
        Pointcut pointcut = new SimplePointcut();
        Advice advice = new SimpleAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        pf.setTarget(one);
        pf.addAdvisor(advisor);
        proxyOne = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.setTarget(two);
        pf.addAdvisor(advisor);
        proxyTwo = (Singer) pf.getProxy();

        proxyOne.sing();
        proxyTwo.sing();
    }
}
