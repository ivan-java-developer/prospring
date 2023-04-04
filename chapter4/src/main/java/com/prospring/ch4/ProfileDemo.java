package com.prospring.ch4;

import com.prospring.ch4.config.HighSchoolProfileConfig;
import com.prospring.ch4.config.KindergartenProfileConfig;
import com.prospring.ch4.profile.FoodProviderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("highschool");
        ctx.register(KindergartenProfileConfig.class, HighSchoolProfileConfig.class);
        ctx.refresh();
        FoodProviderService foodProvider = ctx.getBean("foodProvider", FoodProviderService.class);
        foodProvider.provideLunchSet().forEach(System.out::println);
        ctx.registerShutdownHook();
    }
}
