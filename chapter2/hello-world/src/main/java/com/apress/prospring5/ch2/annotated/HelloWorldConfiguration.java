package com.apress.prospring5.ch2.annotated;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/spring/app-context-annotation.xml")
public class HelloWorldConfiguration {
}
