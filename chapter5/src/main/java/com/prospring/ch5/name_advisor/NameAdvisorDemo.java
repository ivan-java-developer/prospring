package com.prospring.ch5.name_advisor;

import com.prospring.ch5.first_pointcut.SimpleAdvice;
import com.prospring.ch5.first_pointcut.Singer;
import com.prospring.ch5.name_match_method_pointcut.GrammyGuitarist;
import com.prospring.ch5.name_match_method_pointcut.Guitar;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

public class NameAdvisorDemo {
    public static void main(String[] args) {
        GrammyGuitarist bean = new GrammyGuitarist();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.setMappedNames("sing");

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(bean);
        pf.addAdvisor(advisor);
        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}
