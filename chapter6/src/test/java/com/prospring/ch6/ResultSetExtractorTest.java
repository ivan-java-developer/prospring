package com.prospring.ch6;

import com.prospring.ch6.config.EmbeddedSingerJdbcConfig;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResultSetExtractorTest {
    @Test
    public void findAllWithDetailsTest() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);

        List<Singer> singers = singerDao.findAllWithDetail();
        assertEquals(3, singers.size());

        singers.forEach(singer -> {
            System.out.println(singer);
            List<Album> albums = singer.getAlbums();
            albums.forEach(album -> {
                System.out.println("---> " + album);
            });
        });

        ctx.close();
    }

    @Test
    public void findByFirstNameTest() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);

        List<Singer> singers = singerDao.findByFirstName("John");
        assertEquals(2, singers.size());

        singers.forEach(System.out::println);

        ctx.close();
    }
}
