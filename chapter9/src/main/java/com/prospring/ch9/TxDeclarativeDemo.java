package com.prospring.ch9;

import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.services.SingerService;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxDeclarativeDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/tx-declarative-app-context.xml");
        SingerService singerService = ctx.getBean(SingerService.class);
        singerService.findAll().forEach(System.out::println);
        Singer singer = singerService.findById(1);
        singer.setFirstName("Ivan");
        singerService.save(singer);
        System.out.println("Singer was updated " + singer);
        System.out.println("We have " + singerService.countAll() + " singers");
        ctx.close();
    }
}
