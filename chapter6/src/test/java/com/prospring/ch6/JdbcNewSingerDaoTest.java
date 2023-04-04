package com.prospring.ch6;

import com.prospring.ch6.config.NewSingerDaoConfig;
import com.prospring.ch6.dao.NewSingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JdbcNewSingerDaoTest {
    private static GenericApplicationContext ctx;
    private static NewSingerDao singerDao;

    @BeforeAll
    public static void setUp() {
        ctx = new AnnotationConfigApplicationContext(NewSingerDaoConfig.class);
        singerDao = ctx.getBean(NewSingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        List<Singer> all = singerDao.findAll();
        assertEquals(3, all.size());
        displaySingers(all);
    }

    @Test
    public void testFindByFirstName() {
        List<Singer> all = singerDao.findByFirstName("John");
        assertEquals(2, all.size());
        displaySingers(all);
    }

    @Test
    public void testUpdate() {
        List<Singer> allSingerWithFirstNameEric = singerDao.findByFirstName("Eric");
        assertEquals(1, allSingerWithFirstNameEric.size());
        Singer singer = allSingerWithFirstNameEric.get(0);
        singer.setFirstName("Marc");

        singerDao.update(singer);
        assertEquals(0, singerDao.findByFirstName("Eric").size());
        assertEquals(1, singerDao.findByFirstName("Marc").size());

        singer.setFirstName("Eric");
        singerDao.update(singer);
    }

    @Test
    public void testInsertAndDelete() {
        Singer singer = new Singer();
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(Date.valueOf("1984-02-05"));
        assertEquals(3, singerDao.findAll().size());
        singerDao.insert(singer);
        assertEquals(4, singerDao.findAll().size());
        singerDao.delete(singer);
        assertEquals(3, singerDao.findAll().size());
    }

    @Test
    public void testInsertWithAlbums() {
        Singer singer = new Singer();
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(Date.valueOf("1995-05-25"));
        Album albumOne = new Album();
        albumOne.setTitle("First");
        albumOne.setReleaseDate(Date.valueOf("2001-01-01"));
        Album albumTwo = new Album();
        albumTwo.setTitle("Second");
        albumTwo.setReleaseDate(Date.valueOf("2003-03-08"));
        singer.addAlbum(albumOne);
        singer.addAlbum(albumTwo);

        assertEquals(3, singerDao.findAll().size());
        singerDao.insertWithAlbums(singer);
        assertEquals(4, singerDao.findAll().size());
        displaySingersWithAlbums(singerDao.findAll());
        singerDao.delete(singer);
        assertEquals(3, singerDao.findAll().size());
    }

    private void displaySingers(List<Singer> singers) {
        singers.forEach(System.out::println);
    }

    private void displaySingersWithAlbums(List<Singer> singers) {
        singers.forEach(singer -> {
            System.out.println(singer);
            List<Album> albums = singer.getAlbums();
            if (albums != null) {
                albums.forEach(album -> {
                    System.out.println("---> " + album);
                });
            }
        });
    }

    @AfterAll
    public static void theEnd() {
        ctx.close();
    }
}
