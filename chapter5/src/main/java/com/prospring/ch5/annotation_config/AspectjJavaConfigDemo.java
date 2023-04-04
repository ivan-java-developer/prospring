package com.prospring.ch5.annotation_config;

import com.prospring.ch5.complex_aop_xml_config.DocumentaristNew;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectjJavaConfigDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DocumentaristNew documentarist = ctx.getBean("documentarist", DocumentaristNew.class);
        documentarist.execute();
        ctx.close();
    }
}
