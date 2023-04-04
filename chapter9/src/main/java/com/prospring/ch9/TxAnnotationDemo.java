package com.prospring.ch9;

import com.prospring.ch9.config.DataJpaConfig;
import com.prospring.ch9.config.ServicesConfig;
import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxAnnotationDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataJpaConfig.class, ServicesConfig.class);
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
