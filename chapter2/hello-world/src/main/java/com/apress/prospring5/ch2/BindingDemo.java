package com.apress.prospring5.ch2;

import com.apress.prospring5.ch2.binding.Target;
import com.apress.prospring5.ch2.binding.TargetTwo;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BindingDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx
                = new GenericXmlApplicationContext("classpath:spring/binding-app-context.xml");
        TargetTwo target = ctx.getBean("targetTwo", TargetTwo.class);
        ctx.close();
    }
}
