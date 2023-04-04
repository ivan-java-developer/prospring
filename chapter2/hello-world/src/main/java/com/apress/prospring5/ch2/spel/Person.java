package com.apress.prospring5.ch2.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("person")
public class Person {
    @Value("#{injectSimpleSpel.name}")
    private String name;

    @Value("#{injectSimpleSpel.age - 10}")
    private int age;

    @Value("#{injectSimpleSpel.isDeveloper}")
    private boolean isDeveloper;

    @Value("#{injectSimpleSpel.height}")
    private Long height;

    @Value("#{injectSimpleSpel.weight}")
    private float weight;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(boolean developer) {
        isDeveloper = developer;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
