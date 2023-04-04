package com.apress.prospring5.ch2.binding2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class TrickyTarget {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public TrickyTarget() {
        System.out.println("default constructor");
    }

    public TrickyTarget(Foo fooOne) {
        System.out.println("constructor fooOne");
        this.fooOne = fooOne;
    }

    public TrickyTarget(Foo fooOne, Bar bar) {
        System.out.println("constructor fooOne, bar");
        this.fooOne = fooOne;
        this.bar = bar;
    }

    @Autowired
    @Qualifier("fooOneImpl")
    public void setFooOne(Foo fooOne) {
        System.out.println("set fooOne");
        this.fooOne = fooOne;
    }

    @Autowired
    @Qualifier("fooOneImpl")
    public void setFooTwo(Foo fooTwo) {
        System.out.println("set fooTwo");
        this.fooTwo = fooTwo;
    }

    @Autowired
    public void setBar(Bar bar) {
        System.out.println("set bar");
        this.bar = bar;
    }
}
