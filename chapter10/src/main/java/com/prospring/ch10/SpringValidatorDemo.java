package com.prospring.ch10;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.objects.Singer;
import com.prospring.ch10.validators.SingerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

public class SpringValidatorDemo {
    private static Logger logger = LoggerFactory.getLogger(SpringValidatorDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer singer = new Singer();
        singer.setLastName("Ivanov");

        Validator singerValidator = ctx.getBean("singerValidator", SingerValidator.class);

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");
        ValidationUtils.invokeValidator(singerValidator, singer, result);

        List<ObjectError> allErrors = result.getAllErrors();
        logger.info("Count of errors: " + allErrors.size());
        allErrors.forEach(e -> logger.info(e.getCode()));

        ctx.close();
    }
}
