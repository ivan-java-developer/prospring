package com.prospring.ch4.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Named("provider")
public class ConfigurableMessageProvider implements MessageProvider{
    private String message = "default message";

    @Inject
    @Named("message")
    public ConfigurableMessageProvider(String message) {
        System.out.println("Constructor ConfigurableMessageProvider, message = " + message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        System.out.println("ConfigurableMessageProvider setMessage()");
        this.message = message;
    }
}
