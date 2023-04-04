package com.prospring.ch4.config;

import com.prospring.ch4.hello.MessageProvider;
import com.prospring.ch4.hello.MessageRenderer;
import com.prospring.ch4.hello.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.prospring.ch4.config.AppConfigTwo.class)
public class AppConfigThree {
    @Autowired
    MessageProvider provider;

    @Bean
    public MessageRenderer renderer() {
        StandardOutMessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }
}
