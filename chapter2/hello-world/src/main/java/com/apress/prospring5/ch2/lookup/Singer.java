package com.apress.prospring5.ch2.lookup;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("singer")
@Scope("prototype")
public class Singer {
    private String lyric = "I played a quick game of chess with salt and paper shaker";

    public void sing() {
//        System.out.println(lyric);
    }
}
