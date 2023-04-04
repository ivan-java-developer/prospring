package com.prospring.ch4;

import com.prospring.ch4.hello.MessageRenderer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Jsr330Demo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:spring/jsr330-app-context.xml");
        MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
        renderer.render();
        ctx.close();
    }
}
