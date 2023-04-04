package com.apress.prospring5.ch2.decoupled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("confusion")
public class ConstructorConfusion {
    private String value;

    public ConstructorConfusion(String value) {
        this.value = value;
    }

    @Autowired
    public ConstructorConfusion(@Value("80") int value) {
        this.value = "Number: " + value;
    }

    public String getValue() {
        return value;
    }
}
