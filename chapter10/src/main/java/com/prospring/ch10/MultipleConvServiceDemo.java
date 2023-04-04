package com.prospring.ch10;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.objects.AnotherSinger;
import com.prospring.ch10.objects.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MultipleConvServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(MultipleConvServiceDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Singer singer = ctx.getBean("john", Singer.class);
        logger.info("Singer: " + singer);

        ConversionService conversionService = ctx.getBean(ConversionService.class);
        AnotherSinger anotherSinger = conversionService.convert(singer, AnotherSinger.class);
        logger.info("Another singer: " + anotherSinger);

        String[] stringArray = conversionService.convert("a, b, c", String[].class);
        logger.info("String array: " + Arrays.toString(stringArray));

        Set<String> stringSet = conversionService.convert(List.of("a", "b", "c"), Set.class);
        logger.info("Set of strings: " + stringSet);

        ctx.close();
    }
}
