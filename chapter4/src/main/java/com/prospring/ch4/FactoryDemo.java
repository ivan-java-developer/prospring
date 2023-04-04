package com.prospring.ch4;

import com.prospring.ch4.factory.MessageDigester;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FactoryDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/app-context-06.xml");
        MessageDigester messageDigester = (MessageDigester) ctx.getBean("messageDigester");
        messageDigester.digest("I like Spring!");
        ctx.registerShutdownHook();
    }
}
