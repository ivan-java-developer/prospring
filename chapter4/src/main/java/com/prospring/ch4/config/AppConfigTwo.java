package com.prospring.ch4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages={"com.prospring.ch4.hello"})
@PropertySource("classpath:message.properties")
public class AppConfigTwo {
}
