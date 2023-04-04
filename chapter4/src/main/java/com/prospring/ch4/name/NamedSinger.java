package com.prospring.ch4.name;

import org.springframework.beans.factory.BeanNameAware;

public class NamedSinger implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NamedSinger{" +
                "name='" + name + '\'' +
                '}';
    }
}
