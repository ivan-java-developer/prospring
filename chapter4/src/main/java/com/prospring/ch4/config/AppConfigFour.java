package com.prospring.ch4.config;

import com.prospring.ch4.hello.MessageProvider;
import com.prospring.ch4.hello.MessageRenderer;
import com.prospring.ch4.hello.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring/app-context-11.xml")
public class AppConfigFour {
    @Autowired
    MessageProvider provider;

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(provider);
        return messageRenderer;
    }
}
