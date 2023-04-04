package com.prospring.ch5.annotation_config;

import com.prospring.ch5.complex_aop_xml_config.DocumentaristNew;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectjAnnotationConfigDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/aspectj-annotation-config.xml");
        DocumentaristNew documentarist = ctx.getBean("documentarist", DocumentaristNew.class);
        documentarist.execute();
        ctx.registerShutdownHook();
    }
}
