package com.prospring.ch5.name_match_method_pointcut;

import com.prospring.ch5.first_pointcut.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutDemo {
    public static void main(String[] args) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new GrammyGuitarist());
        pf.addAdvisor(new DefaultPointcutAdvisor(pointcut, new SimpleAdvice()));
        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}
