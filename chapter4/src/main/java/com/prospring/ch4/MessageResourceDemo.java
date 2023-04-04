package com.prospring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class MessageResourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/app-context-09.xml");
        String helloMsgRu = ctx.getMessage("hello", null, new Locale("ru", "RU"));
        String helloMsgEn = ctx.getMessage("hello", null, Locale.ENGLISH);
        String byeMsgEn = ctx.getMessage("bye", new Object[] {"John"}, Locale.ENGLISH);
        String byeMsgRu = ctx.getMessage("bye", new Object[] {"John"}, new Locale("ru", "RU"));
        System.out.println(helloMsgRu);
        System.out.println(helloMsgEn);
        System.out.println(byeMsgRu);
        System.out.println(byeMsgEn);
        ctx.registerShutdownHook();
    }
}
