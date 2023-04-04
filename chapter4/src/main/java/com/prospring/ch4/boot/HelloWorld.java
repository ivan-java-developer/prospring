package com.prospring.ch4.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    @RequestMapping("/")
    public String sayHi() {
        return "Hello World!";
    }
}
