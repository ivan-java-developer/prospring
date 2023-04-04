package com.prospring.ch5.aspectj_configuration;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectjDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/aspectj-config.xml");
//        MessageWriter messageWriter = ctx.getBean("messageWriter", MessageWriter.class);
        MessageWriter messageWriter = new MessageWriter();
        messageWriter.writeMessage();
        messageWriter.foo();
        ctx.close();
    }
}
