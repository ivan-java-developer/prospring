package com.prospring.ch12.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("listOfSingers")
public class Singers implements Serializable {

    private List<Singer> singers;

    public Singers() {
    }

    public Singers(List<Singer> singeers) {
        this.singers = singeers;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }
}
