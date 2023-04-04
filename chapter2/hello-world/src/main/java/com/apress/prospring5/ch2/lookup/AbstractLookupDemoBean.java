package com.apress.prospring5.ch2.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public abstract class AbstractLookupDemoBean implements DemoBean {
    @Override
    public void doSomething() {
        getMySinger().sing();
    }

    @Lookup("singer")
    @Override
    public abstract Singer getMySinger();
}
