package com.prospring.ch4.context_aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ContextAware implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public TestBean getTestBean() {
        System.out.println("getTestBean()");
        return applicationContext.getBean("testBean", TestBean.class);
    }
}
