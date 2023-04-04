package com.prospring.ch4.init;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

@Configuration
public class Singer implements InitializingBean {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()");
    }

    @PostConstruct
    private void initialize() {
        System.out.println("initialize()");
    }

    private void init() {
        System.out.println("Initialize singer");
        if (name == null) {
            System.out.println("setting default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            System.out.println("age doesn't set");
            throw new IllegalArgumentException("For all singer bean must set age");
        }
    }

    @Lazy
    @Bean(initMethod="init")
    public Singer singerOne() {
        Singer singer = new Singer();
        singer.setName("John");
        singer.setAge(38);
        return singer;
    }

    @Lazy
    @Bean(initMethod="init")
    public Singer singerTwo() {
        Singer singer = new Singer();
        singer.setAge(38);
        return singer;
    }

    @Lazy
    @Bean(initMethod="init")
    public Singer singerThree() {
        Singer singer = new Singer();
        singer.setName("John");
        return singer;
    }

    public static void main(String[] args) {
//        GenericXmlApplicationContext ctx =
//                new GenericXmlApplicationContext("classpath:spring/app-context-01.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Singer.class);
        getBean(ctx, "singerOne");
        getBean(ctx, "singerTwo");
        getBean(ctx, "singerThree");
        ctx.close();
    }

    private static void getBean(ApplicationContext ctx, String beanName) {
        try {
            System.out.println(ctx.getBean(beanName));
        } catch (BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }
}
