package com.prospring.ch4;

import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyDemo {
    public static void main(String[] args) {
        GenericGroovyApplicationContext ctx =
                new GenericGroovyApplicationContext("classpath:beans.groovy");
        System.out.println(ctx.getBean("singer"));
        ctx.registerShutdownHook();
    }
}
