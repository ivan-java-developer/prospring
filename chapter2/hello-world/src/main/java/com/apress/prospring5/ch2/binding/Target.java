package com.apress.prospring5.ch2.binding;

public class Target {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target() {
        System.out.println("default constructor");
    }

    public Target(Foo foo) {
        System.out.println("constructor foo");
        this.fooTwo = foo;
    }

    public void setFooOne(Foo fooOne) {
        System.out.println("setter fooOne");
        this.fooOne = fooOne;
    }

    public void setFooTwo(Foo fooTwo) {
        System.out.println("setter fooTwo");
        this.fooTwo = fooTwo;
    }

    public void setBar(Bar bar) {
        System.out.println("setter bar");
        this.bar = bar;
    }


}
