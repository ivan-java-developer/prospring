package com.prospring.ch5;

import com.prospring.ch5.complex_aop_xml_config.DocumentaristNew;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        DocumentaristNew documentarist = ctx.getBean("documentarist", DocumentaristNew.class);
        documentarist.execute();
        ctx.close();
    }
}
