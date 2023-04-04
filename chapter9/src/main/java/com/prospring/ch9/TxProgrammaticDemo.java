package com.prospring.ch9;

import com.prospring.ch9.config.DataJpaConfig;
import com.prospring.ch9.config.ServicesConfig;
import com.prospring.ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        Long count = singerService.countAll();
        System.out.println(count);
        ctx.close();
    }
}
