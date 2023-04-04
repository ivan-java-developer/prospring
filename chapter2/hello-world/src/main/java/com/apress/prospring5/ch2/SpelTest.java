package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.spel.Person;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpelTest {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/spel-app-context.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        Person person = ctx.getBean("person", Person.class);
        System.out.println(person);
        ctx.close();
    }
}
