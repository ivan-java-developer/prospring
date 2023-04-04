package com.prospring.ch10;

import com.prospring.ch10.config.AppConfig;
import com.prospring.ch10.objects.Genre;
import com.prospring.ch10.objects.Singer;
import com.prospring.ch10.services.SingerValidatorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


public class Jsr349CustomDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidatorService singerValidatorService = ctx.getBean(SingerValidatorService.class);
        Singer singer = new Singer();
        singer.setFirstName("Viktor");
        singer.setGenre(Genre.COUNTRY);

        Jsr349ValidationDemo.validateSinger(singer, singerValidatorService);

        ctx.close();
    }
}
