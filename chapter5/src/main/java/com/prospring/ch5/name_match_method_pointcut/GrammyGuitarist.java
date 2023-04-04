package com.prospring.ch5.name_match_method_pointcut;

import com.prospring.ch5.first_pointcut.Singer;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("Вместо тепла - зелень стекла \nВместо огня - дым");
    }

    public void sing(Guitar guitar) {
        System.out.println("guitar play " + guitar.play());
    }

    public void rest() {
        System.out.println("Zzz");
    }

    public void talk() {
        System.out.println("Hello!");
    }
}
