package com.prospring.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcDemo {
    private static Logger logger = LoggerFactory.getLogger(AmqpRpcDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:amqp-rpcapp-context.xml");
        WeatherService weatherService = ctx.getBean(WeatherService.class);
        logger.info("Forecast for FL: " + weatherService.getForecast("FL"));
        logger.info("Forecast for MA: " + weatherService.getForecast("MA"));
        logger.info("Forecast for CA: " + weatherService.getForecast("CA"));
        ctx.close();
    }
}
