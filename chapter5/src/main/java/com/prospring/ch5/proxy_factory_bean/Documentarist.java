package com.prospring.ch5.proxy_factory_bean;

import com.prospring.ch5.name_match_method_pointcut.GrammyGuitarist;
import com.prospring.ch5.name_match_method_pointcut.Guitar;

public class Documentarist {
    private GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.sing(new Guitar());
        guitarist.talk();
    }

    public void setDep(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
