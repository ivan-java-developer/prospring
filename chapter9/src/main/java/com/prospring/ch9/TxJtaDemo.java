package com.prospring.ch9;

import com.prospring.ch9.config.ServicesConfig;
import com.prospring.ch9.config.XAJpaConfig;
import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class TxJtaDemo {
    private static Logger logger = LoggerFactory.getLogger(TxJtaDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(XAJpaConfig.class, ServicesConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        Singer singer = new Singer();
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(LocalDate.of(1977, 3, 12));
        singerService.save(singer);
        if (singer.getId() != null) {
            System.out.println("singer saved successfully");
        } else {
            System.out.println("singer was not saved, check the configuration");
        }
        List<Singer> allSingers = singerService.findAll();
        if (allSingers.size() != 2) {
            logger.error("Something went wrong");
        } else {
            logger.info("Singers from both DBs: " + allSingers);
        }
        ctx.close();
    }
}
