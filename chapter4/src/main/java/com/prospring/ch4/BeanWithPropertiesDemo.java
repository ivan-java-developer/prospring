package com.prospring.ch4;

import com.prospring.ch4.environment.AppProperties;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanWithPropertiesDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx =
                new ClassPathXmlApplicationContext("classpath:spring/env-app-config.xml");
        AppProperties appProps = ctx.getBean("appProps", AppProperties.class);
        System.out.println(appProps.getUserHome());
        System.out.println(appProps.getApplicationHome());
        ctx.close();
    }
}
