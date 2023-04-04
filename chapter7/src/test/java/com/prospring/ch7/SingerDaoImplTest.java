package com.prospring.ch7;

import com.prospring.ch7.config.AdvancedConfig;
import com.prospring.ch7.config.AppConfig;
import com.prospring.ch7.dao.SingerDao;
import com.prospring.ch7.entities.Album;
import com.prospring.ch7.entities.Instrument;
import com.prospring.ch7.entities.Singer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingerDaoImplTest {
    private GenericApplicationContext ctx;
    private SingerDao singerDao;
    private static final Log logger = LogFactory.getLog(SingerDaoImplTest.class);

    @BeforeEach
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(AdvancedConfig.class);
//        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        ctx = new GenericXmlApplicationContext("classpath:spring/hibernate-app-context.xml");
        assertNotNull(ctx);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll() {
        List<Singer> allSingers = singerDao.findAll();
        assertEquals(3, allSingers.size());
        listSingers(allSingers);
//        listSingerWithDetails(allSingers); // LazyInitializationException
    }

    @Test
    public void testFindAllWithDetails() {
        List<Singer> allSingers = singerDao.findAllWithDetails();
        assertEquals(3, allSingers.size());
        listSingerWithDetails(allSingers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerDao.findById(2);
        assertEquals("Eric", singer.getFirstName());
        assertEquals(1, singer.getAlbums().size());
        Album album = singer.getAlbums().stream().findFirst().orElse(null);
        assertNotNull(album);
        assertEquals("From The Cradle", album.getTitle());
    }

    @Test
    public void testInsert() {
        Singer singer = new Singer();
        singer.setFirstName("Ivan");
        singer.setLastName("Ivanov");
        singer.setBirthDate(LocalDate.of(1995, 05, 25));
        Album albumOne = new Album();
        albumOne.setTitle("First");
        albumOne.setReleaseDate(LocalDate.of(2001, 1, 1));
        Album albumTwo = new Album();
        albumTwo.setTitle("Second");
        albumTwo.setReleaseDate(LocalDate.of(1967, 2, 15));
        singer.addAlbum(albumOne);
        singer.addAlbum(albumTwo);

        assertEquals(3, singerDao.findAll().size());
        singerDao.save(singer);
        assertNotNull(singer.getId());
        assertEquals(4, singerDao.findAll().size());
        listSingerWithDetails(singerDao.findAllWithDetails());
    }

    @Test
    public void testUpdate() {
        Singer singer = singerDao.findById(1);
        assertNotNull(singer);

        Album album = singer.getAlbums().stream()
                .filter(a -> "Battle Studies".equals(a.getTitle()))
                .findAny().orElse(null);
        assertNotNull(album);

        singer.setFirstName("Ivan");
        singer.removeAlbum(album);
        singerDao.save(singer);
        listSingerWithDetails(singerDao.findAllWithDetails());
    }

    @Test
    public void testDelete() {
        Singer singer = singerDao.findById(2);
        assertNotNull(singer);
        assertEquals(3, singerDao.findAll().size());
        singerDao.delete(singer);
        assertEquals(2, singerDao.findAll().size());
        listSingerWithDetails(singerDao.findAllWithDetails());
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
