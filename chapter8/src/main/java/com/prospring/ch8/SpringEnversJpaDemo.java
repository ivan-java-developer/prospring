package com.prospring.ch8;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.SingerAudit;
import com.prospring.ch8.service.SingerAuditService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class SpringEnversJpaDemo {
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

        singer.setFirstName("Makar");
        singerAuditService.save(singer);
        singer = singerAuditService.findById(1).get();
        singer.setFirstName("Igor");
        singerAuditService.save(singer);
        listAllSingers(singerAuditService.findAll());

        singer = singerAuditService.findByRevisionId(1, 1);
        System.out.println("Revision one:");
        System.out.println(singer);
        singer = singerAuditService.findByRevisionId(1, 2);
        System.out.println("Revision two");
        System.out.println(singer);

        ctx.close();
    }

    private static void listAllSingers(List<SingerAudit> allSingers) {
        allSingers.forEach(System.out::println);
        System.out.println("----------------------------");
    }
}
