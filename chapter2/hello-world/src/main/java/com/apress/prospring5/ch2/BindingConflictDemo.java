package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.binding2.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConflictDemo {

    @Bean
    public Bar bar() {
        return new Bar();
    }

    @Bean
    public Foo fooOneImpl() {
        return new FooOneImpl();
    }

    @Bean
    public Foo fooTwoImpl() {
        return new FooTwoImpl();
    }

    @Bean
    public TrickyTarget trickyTarget() {
        return new TrickyTarget();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(BindingConflictDemo.class);
        ctx.getBean("trickyTarget");
        ctx.close();
    }
}
