package com.prospring.ch4.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootDemo {
    private static Logger logger = LoggerFactory.getLogger(SpringBootDemo.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringBootDemo.class);
        assert (ctx != null);
//        logger.info("The bean were you looking for:");
//        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
//        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
//        helloWorld.sayHi();
        System.in.read();
        ctx.close();
    }
}
