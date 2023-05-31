package com.prospring.ch14;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.IOException;

public class RuleEngineDemo {
    private static Logger logger = LoggerFactory.getLogger(RuleEngineDemo.class);

    public static void main(String[] args) throws IOException {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/app-context.xml");

        SingerService singerService = ctx.getBean("singerService", SingerService.class);

        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new DateTime(1977, 10, 16, 0, 0, 0, 0));

        singerService.applyRule(singer);
        logger.info("Singer: " + singer);

        System.in.read();

        singerService.applyRule(singer);
        logger.info("Singer: " + singer);

        ctx.close();
    }
}
