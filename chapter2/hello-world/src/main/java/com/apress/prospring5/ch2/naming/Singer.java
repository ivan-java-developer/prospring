package com.apress.prospring5.ch2.naming;

import org.springframework.stereotype.Component;

@Component
public class Singer {
    private String lyric = "some song";

    public String sing() {
        return lyric;
    }
}
