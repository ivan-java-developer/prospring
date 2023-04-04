package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.naming.Singer;
import com.apress.prospring5.ch2.replacer.ReplacementTarget;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class BeanNamingTest {

    @Configuration
    @ComponentScan(basePackages = "com.apress.prospring5.ch2.naming")
    public static class Config {
        @Bean
        public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(Config.class);

        ctx.getBeansOfType(Singer.class).forEach((key, value) -> {
            System.out.println("key: " + key + ", aliases: " + Arrays.toString(ctx.getAliases(key)));
        });

        ctx.close();
    }
}
