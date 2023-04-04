package com.apress.prospring5.ch2.binding;

public class TargetTwo {
    private Foo fooOne;
    private Foo fooTwo;

    public void setFooOne(Foo fooOne) {
        System.out.println("setter fooOne");
        this.fooOne = fooOne;
    }

    public void setFooTwo(Foo fooTwo) {
        System.out.println("setter fooTwo");
        this.fooTwo = fooTwo;
    }
}
