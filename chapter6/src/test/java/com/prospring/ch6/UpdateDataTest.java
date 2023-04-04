package com.prospring.ch6;

import com.prospring.ch6.config.EmbeddedSingerJdbcConfig;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateDataTest {
    @Test
    public void insertUpdateDeleteTest() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);

        Singer singer = ctx.getBean(Singer.class);
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(Date.valueOf("1995-05-25"));

        assertEquals(3, singerDao.findAll().size());
        singerDao.insert(singer);
        assertEquals(4, singerDao.findAll().size());
        assertEquals("Ivan", singerDao.findFirstNameById(singer.getId()));
        singer.setFirstName("Oleg");
        singerDao.update(singer);
        assertEquals("Oleg", singerDao.findFirstNameById(singer.getId()));

        singerDao.delete(singer);
        assertEquals(3, singerDao.findAll().size());

        ctx.close();
    }

    @Test
    public void insertWithDetailTest() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        assertEquals(3, singerDao.findAll().size());

        Singer singer = ctx.getBean(Singer.class);
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(Date.valueOf("1995-05-25"));
        Album albumOne = ctx.getBean(Album.class);
        albumOne.setTitle("First");
        albumOne.setReleaseDate(Date.valueOf("2001-01-01"));
        Album albumTwo = ctx.getBean(Album.class);
        albumTwo.setTitle("Second");
        albumTwo.setReleaseDate(Date.valueOf("2003-03-08"));
        singer.addAlbum(albumOne);
        singer.addAlbum(albumTwo);

        singerDao.insertWithDetail(singer);
        List<Singer> singers = singerDao.findAllWithDetail();

        assertEquals(4, singers.size());

        singers.forEach(artist -> {
            System.out.println(artist);
            artist.getAlbums().forEach(album -> {
                System.out.println("---> " + album);
            });
        });

        ctx.close();
    }
}
