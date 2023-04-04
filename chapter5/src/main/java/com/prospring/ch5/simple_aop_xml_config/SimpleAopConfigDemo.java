package com.prospring.ch5.simple_aop_xml_config;

import com.prospring.ch5.proxy_factory_bean.Documentarist;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleAopConfigDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/simple-aop-config.xml");
        Documentarist documentarist = ctx.getBean("documentarist", Documentarist.class);
        documentarist.execute();
        ctx.registerShutdownHook();
    }
}
