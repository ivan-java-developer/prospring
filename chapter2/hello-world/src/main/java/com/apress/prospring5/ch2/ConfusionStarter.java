package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.decoupled.ConstructorConfusion;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfusionStarter {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:spring/confusion.xml");
        ConstructorConfusion bean = ctx.getBean("confusion", ConstructorConfusion.class);
        System.out.println(bean.getValue());
    }
}
