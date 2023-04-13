package com.prospring.ch12;

import com.prospring.ch12.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class JmsHornetQSample {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);

        for (int i = 1; i <= 5; i++) {
            messageSender.sendMessage("Message #" + i);
        }
        System.in.read();
        ctx.close();
    }
}
