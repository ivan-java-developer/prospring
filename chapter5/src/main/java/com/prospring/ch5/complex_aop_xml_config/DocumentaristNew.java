package com.prospring.ch5.complex_aop_xml_config;

import com.prospring.ch5.name_match_method_pointcut.GrammyGuitarist;
import com.prospring.ch5.name_match_method_pointcut.Guitar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class DocumentaristNew {
    private GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Very cool brand");
        guitarist.sing(guitar);
        guitarist.sing(new Guitar());
        guitarist.talk();
    }

    @Autowired
    public void setGuitarist(GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
