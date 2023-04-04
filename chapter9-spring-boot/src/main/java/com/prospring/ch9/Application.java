package com.prospring.ch9;

import com.prospring.ch9.entities.Singer;
import com.prospring.ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        System.in.read();
        ctx.close();
    }

    @Autowired
    SingerService singerService;

    @Override
    public void run(String... args) throws Exception {
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(LocalDate.of(1977, 9, 16));
        singerService.save(singer);

        long count = singerService.count();
        if (count == 1) {
            logger.info("---> Singer saved successfully.");
        } else {
            logger.error("---> Singer was not saves, check the configuration!");
        }

        try {
            singerService.save(null);
        } catch (RuntimeException e) {
            logger.error(e.getMessage() + " Final count: " + singerService.count());
        }
    }
}
