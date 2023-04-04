package com.apress.prospring5.ch2.spel;

import org.springframework.stereotype.Component;

@Component("injectSimpleSpel")
public class InjectSimpleSpel {
    private String name = "John";
    private int age = 38;
    private boolean isDeveloper = true;
    private Long height = 176L;
    private float weight = 84.2f;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isDeveloper() {
        return isDeveloper;
    }

    public Long getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
