package com.prospring.ch16.init;

import com.prospring.ch16.config.DataServiceConfig;
import com.prospring.ch16.config.SecurityConfig;
import com.prospring.ch16.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                SecurityConfig.class, DataServiceConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                WebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        return new Filter[] {methodFilter, encodingFilter};
    }
}
