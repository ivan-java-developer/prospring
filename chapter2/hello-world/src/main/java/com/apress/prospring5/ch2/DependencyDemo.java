package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.dependency.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DependencyDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/dependency-app-context.xml");
        Singer singer = ctx.getBean("singer", Singer.class);
        System.out.println(singer.sing());
        ctx.close();
    }
}
