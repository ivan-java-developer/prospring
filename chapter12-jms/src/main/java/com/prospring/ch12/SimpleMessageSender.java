package com.prospring.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component("messageSender")
public class SimpleMessageSender implements MessageSender {

    private static Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.setDeliveryDelay(5000);
        jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage(message);
            logger.info(">>> Sending text message: " + message);
            return textMessage;
        });
    }
}
