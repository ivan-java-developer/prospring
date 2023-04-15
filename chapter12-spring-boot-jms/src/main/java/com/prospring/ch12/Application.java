package com.prospring.ch12;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @JmsListener(destination = "prospring", containerFactory = "containerFactory")
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            logger.info("<<< Received message: " + textMessage.getText());
        } catch(JMSException e) {
            logger.error("JMS error", e);
        }
    }

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
        for (int i = 1; i <= 5; i++) {
            String message = "Test message #" + i;
            logger.info(">>> Sending: " + message);
            jmsTemplate.convertAndSend("prospring", message);
        }
        System.in.read();
        ctx.close();
    }
}
