package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.inheritance.Student;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InheritanceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext("classpath:spring/inheritance-app-context.xml");
        Student jack = ctx.getBean("jack", Student.class);
        Student youngJack = ctx.getBean("youngJack", Student.class);
        System.out.println(jack);
        System.out.println(youngJack);
        ctx.close();
    }
}
