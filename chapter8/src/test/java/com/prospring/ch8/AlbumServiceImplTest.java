package com.prospring.ch8;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.service.AlbumService;
import com.prospring.ch8.service.SingerDataService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlbumServiceImplTest {
    private GenericApplicationContext ctx;
    private AlbumService albumService;
    private static final Log logger = LogFactory.getLog(AlbumServiceImplTest.class);

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
//        ctx = new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        assertNotNull(ctx);
        albumService = ctx.getBean(AlbumService.class);
        assertNotNull(albumService);
        logger.info("Spring context was initialized ------------------------");
    }

    @Test
    public void testFindAll() {
        List<Album> all = albumService.findAll();
        assertEquals(3, all.size());
        all.forEach(System.out::println);
    }

    @Test
    public void testFindByFirstName() {
        List<Album> all = albumService.findByTitle("The");
        assertEquals(2, all.size());
        all.forEach(album -> {
            Singer singer = album.getSinger();
            System.out.println(singer);
            System.out.println(album);
        });
    }

    @AfterEach
    public void theEnd() {
        ctx.close();
    }
}
