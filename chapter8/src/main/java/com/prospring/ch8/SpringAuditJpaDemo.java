package com.prospring.ch8;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.SingerAudit;
import com.prospring.ch8.service.SingerAuditService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SpringAuditJpaDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
//        GenericApplicationContext ctx =
//                new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);
        listAllSingers(singerAuditService.findAll());

        SingerAudit singer = new SingerAudit();
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(LocalDate.of(1995, 5, 25));
        singerAuditService.save(singer);
        listAllSingers(singerAuditService.findAll());

        singer = singerAuditService.findById(singer.getId()).orElseThrow();
        singer.setFirstName("Makar");
        singerAuditService.save(singer);
        listAllSingers(singerAuditService.findAll());

        ctx.close();
    }

    private static void listAllSingers(List<SingerAudit> allSingers) {
        allSingers.forEach(System.out::println);
        System.out.println("----------------------------");
    }
}
