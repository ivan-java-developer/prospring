package com.prospring.ch5.dynamic_pointcut;

public class SampleBean {
    public void foo(int n) {
        System.out.println("foo(" + n + ")");
    }

    public void bar() {
        System.out.println("bar()");
    }
}
