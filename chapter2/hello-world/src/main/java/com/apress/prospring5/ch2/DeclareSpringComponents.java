package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.annotated.HelloWorldConfiguration;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeclareSpringComponents {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        StandardOutMessageRenderer renderer = ctx.getBean("renderer", StandardOutMessageRenderer.class);
        renderer.render();
    }
}
