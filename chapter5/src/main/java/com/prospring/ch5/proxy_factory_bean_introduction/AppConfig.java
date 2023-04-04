package com.prospring.ch5.proxy_factory_bean_introduction;

import com.prospring.ch5.introduction.Contact;
import com.prospring.ch5.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AppConfig {
    @Bean
    public Contact realContact() {
        Contact contact = new Contact();
        contact.setName("John");
        return contact;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean contact() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(realContact());
        proxyFactoryBean.addAdvisor(advisor());
        proxyFactoryBean.setProxyTargetClass(true);
        return proxyFactoryBean;
    }
}
