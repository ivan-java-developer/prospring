package com.prospring.ch12.services;

import com.prospring.ch12.entities.Singer;
import com.prospring.ch12.repositories.SingerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class DBInitializer {
    private static Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    private SingerRepository singerRepository;

    @PostConstruct
    public void init() {
        logger.info("Starting database initialization.");
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(
                new Date(new GregorianCalendar(1977, Calendar.OCTOBER, 16).getTime().getTime()));
        singerRepository.save(singer);

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(
                new Date(new GregorianCalendar(1975, Calendar.APRIL, 1).getTime().getTime()));
        singerRepository.save(singer);

        singer = new Singer();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(
                new Date(new GregorianCalendar(1945, Calendar.MARCH, 30).getTime().getTime()));
        singerRepository.save(singer);
        logger.info("Database initialized successfully");
    }
}
