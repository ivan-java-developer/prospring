package com.apress.prospring5.ch2.dependency;

import org.springframework.stereotype.Component;

@Component
public class Guitar {
    public String sing() {
        return "guitar plays";
    }
}
