package com.prospring.ch10;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.objects.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(ConvFormatServiceDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer viktor = ctx.getBean("viktor", Singer.class);
        ConversionService appConversionService = ctx.getBean("appConversionService", ConversionService.class);
        logger.info("Viktor birthday is " + appConversionService.convert(viktor.getBirthDate(), String.class));
        ctx.close();
    }
}
