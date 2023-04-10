package com.prospring.ch12.config;

import com.prospring.ch12.services.SingerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class RmiClientConfig {

    @Bean
    public SingerService singerService() {
        HttpInvokerProxyFactoryBean invokerFactory = new HttpInvokerProxyFactoryBean();
        invokerFactory.setServiceInterface(SingerService.class);
        invokerFactory.setServiceUrl("http://localhost:8080/invoker/httpInvoker/singerService");
        invokerFactory.afterPropertiesSet();
        return (SingerService) invokerFactory.getObject();
    }
}
