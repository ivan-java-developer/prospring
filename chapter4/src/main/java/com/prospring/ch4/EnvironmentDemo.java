package com.prospring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/env-app-config.xml");
        ConfigurableEnvironment environment = ctx.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");
        propertySources.addFirst(new MapPropertySource("test", appMap));

        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("XAUTHORITY: " + System.getenv("XAUTHORITY"));
        System.out.println("user.home: " + environment.getProperty("user.home"));
        System.out.println("XAUTHORITY: " + environment.getProperty("XAUTHORITY"));
        System.out.println("application.home: " + environment.getProperty("application.home"));
        ctx.close();
    }
}
