package com.prospring.ch4.listener;

import org.springframework.context.ApplicationListener;

public class MessageListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent event) {
        System.out.println(event.getMessage());
    }
}
