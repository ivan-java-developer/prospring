package com.apress.prospring5.ch2.lookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("standardLookupBean")
public class StandardLookupDemoBean implements DemoBean {
    private Singer singer;

    @Autowired
    @Qualifier("singer")
    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getMySinger() {
        return singer;
    }

    @Override
    public void doSomething() {
        singer.sing();
    }
}
