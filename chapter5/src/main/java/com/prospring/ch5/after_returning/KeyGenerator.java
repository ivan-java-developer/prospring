package com.prospring.ch5.after_returning;

import java.util.Random;

public class KeyGenerator {
    public static final long WEAK_KEY = 0xFFFFFFFF00000000L;
    public static final long STRONG_KEY = 0x1231FA2B24CAL;

    public long getKey() {
        Random random = new Random();
        int i = random.nextInt(3);
        if (i == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
