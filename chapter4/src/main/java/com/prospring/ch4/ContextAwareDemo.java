package com.prospring.ch4;

import com.prospring.ch4.context_aware.ContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ContextAwareDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/app-context-04.xml");
        ContextAware contextAware = ctx.getBean("contextAware", ContextAware.class);
        System.out.println(contextAware.getTestBean());
        ctx.close();
    }
}
