package com.prospring.ch5.aspectj_configuration;

public class MessageWriter {
    public void writeMessage() {
        System.out.println("writeMessage()");
    }

    public void foo() {
        System.out.println("foo()");
    }
}
