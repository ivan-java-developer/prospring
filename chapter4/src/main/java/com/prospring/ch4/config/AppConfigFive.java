package com.prospring.ch4.config;

import com.prospring.ch4.hello.ConfigurableMessageProvider;
import com.prospring.ch4.hello.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:message.properties")
public class AppConfigFive {
    @Autowired
    private Environment env;

    @Bean
    public MessageProvider provider() {
        return new ConfigurableMessageProvider(env.getProperty("hello"));
    }
}
