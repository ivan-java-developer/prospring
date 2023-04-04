package com.prospring.ch8;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.SingerDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingerDataServiceImplTest {
    private GenericApplicationContext ctx;
    private SingerDataService singerDataService;
    private static final Log logger = LogFactory.getLog(SingerDataServiceImplTest.class);

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
//        ctx = new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        assertNotNull(ctx);
        singerDataService = ctx.getBean(SingerDataService.class);
        assertNotNull(singerDataService);
    }

    @Test
    public void testFindAll() {
        List<Singer> allSingers = singerDataService.findAll();
        assertEquals(3, allSingers.size());
        listSingers(allSingers);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> allSingers = singerDataService.findByFirstName("John");
        assertEquals(2, allSingers.size());
        listSingers(allSingers);
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        List<Singer> allSingers = singerDataService.findByFirstNameAndLastName("John", "Mayer");
        assertEquals(1, allSingers.size());
        listSingers(allSingers);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(logger::info);
    }

    private void listSingerWithDetails(List<Singer> singers) {
        singers.forEach(singer -> {
            logger.info(singer);
            Set<Album> albums = singer.getAlbums();
            if (albums != null) {
                albums.forEach(logger::info);
            }
            Set<Instrument> instruments = singer.getInstruments();
            if (instruments != null) {
                instruments.forEach(logger::info);
            }
        });
    }

    @AfterEach
    public void theEnd() {
        ctx.close();
    }
}
