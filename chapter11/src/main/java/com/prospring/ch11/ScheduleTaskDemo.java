package com.prospring.ch11;

import com.prospring.ch11.config.AppConfig;
import com.prospring.ch11.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskDemo {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskDemo.class);

    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CarService carService = ctx.getBean("carService", CarService.class);
        while(!carService.isDone()) {
            logger.info("Waiting for scheduled job to end...");
            Thread.sleep(250);
        }
        ctx.close();
    }
}
