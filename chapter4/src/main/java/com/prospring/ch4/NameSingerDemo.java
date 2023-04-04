package com.prospring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NameSingerDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext("classpath:spring/app-context-03.xml");
        System.out.println(ctx.getBean("namedSinger"));
        ctx.registerShutdownHook();
    }
}
