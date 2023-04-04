package com.prospring.ch5.complex_aop_xml_config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComplexAopConfigDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:spring/complex-aop-config.xml");
        DocumentaristNew documentarist = ctx.getBean("documentarist", DocumentaristNew.class);
        documentarist.execute();
        ctx.registerShutdownHook();
    }
}
