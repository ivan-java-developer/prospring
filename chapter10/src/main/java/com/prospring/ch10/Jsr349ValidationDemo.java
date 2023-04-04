package com.prospring.ch10;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.objects.Singer;
import com.prospring.ch10.services.SingerValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class Jsr349ValidationDemo {

    private static Logger logger = LoggerFactory.getLogger(Jsr349ValidationDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidatorService singerValidatorService = ctx.getBean(SingerValidatorService.class);
        Singer singer = new Singer();
        singer.setFirstName("a");
        singer.setLastName("Ivanov");
        Set<ConstraintViolation<Singer>> violations = singerValidatorService.validateSinger(singer);

        validateSinger(singer, singerValidatorService);

        ctx.close();
    }

    public static void validateSinger(Singer singer, SingerValidatorService singerValidatorService) {
        Set<ConstraintViolation<Singer>> violations = singerValidatorService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations) {
        logger.info("Count of violations - " + violations.size());
        for (ConstraintViolation<Singer> violation : violations) {
            logger.info("Violation error for property " + violation.getPropertyPath()
                    + " invalid value is " + violation.getInvalidValue()
                    + " with error message " + violation.getMessage());
        }
    }
}
