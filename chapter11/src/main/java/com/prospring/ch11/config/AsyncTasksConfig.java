package com.prospring.ch11.config;

import com.prospring.ch11.services.AsyncService;
import com.prospring.ch11.services.AsyncServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncTasksConfig {

    @Bean("asyncService")
    public AsyncService asyncService() {
        return new AsyncServiceImpl();
    }
}
