package com.prospring.ch8;

import com.prospring.ch8.config.JpaConfig;
import com.prospring.ch8.service.SingerService;
import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Instrument;
import com.prospring.ch8.entities.Singer;
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

public class SingerServiceImplTest {
    private GenericApplicationContext ctx;
    private SingerService singerService;
    private static final Log logger = LogFactory.getLog(SingerServiceImplTest.class);

    @BeforeEach
    public void setUp() {
//        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        ctx = new GenericXmlApplicationContext("classpath:spring/jpa-app-context.xml");
        assertNotNull(ctx);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @Test
    public void testFindAll() {
        List<Singer> allSingers = singerService.findAll();
        assertEquals(3, allSingers.size());
        listSingers(allSingers);
//        listSingerWithDetails(allSingers); // LazyInitializationException
    }

    @Test
    public void testFindAllByNativeQuery() {
        List<Singer> allSingers = singerService.findAllByNativeQuery();
        assertEquals(3, allSingers.size());
        listSingers(allSingers);
//        listSingerWithDetails(allSingers); // LazyInitializationException
    }

    @Test
    public void testFindAllWithDetails() {
        List<Singer> allSingers = singerService.findAllWithDetails();
        assertEquals(3, allSingers.size());
        listSingerWithDetails(allSingers);
    }

    @Test
    public void testFindById() {
        Singer singer = singerService.findById(2);
        assertEquals("Eric", singer.getFirstName());
        assertEquals(1, singer.getAlbums().size());
        Album album = singer.getAlbums().stream().findFirst().orElse(null);
        assertNotNull(album);
        assertEquals("From The Cradle", album.getTitle());
    }

    @Test
    public void testFindByCriteriaQuery() {
        List<Singer> list = singerService.findByCriteriaQuery("John", "Butler");
        assertEquals(1, list.size());
        list = singerService.findByCriteriaQuery("John", null);
        assertEquals(2, list.size());
        listSingerWithDetails(list);
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

        assertEquals(3, singerService.findAll().size());
        singerService.save(singer);
        assertNotNull(singer.getId());
        assertEquals(4, singerService.findAll().size());
        listSingerWithDetails(singerService.findAllWithDetails());
    }

    @Test
    public void testUpdate() {
        Singer singer = singerService.findById(1);
        assertNotNull(singer);

        Album album = singer.getAlbums().stream()
                .filter(a -> "Battle Studies".equals(a.getTitle()))
                .findAny().orElse(null);
        assertNotNull(album);

        singer.setFirstName("Ivan");
        singer.removeAlbum(album);
        singerService.save(singer);
        listSingerWithDetails(singerService.findAllWithDetails());
    }

    @Test
    public void testDelete() {
        Singer singer = singerService.findById(2);
        assertNotNull(singer);
        assertEquals(3, singerService.findAll().size());
        singerService.delete(singer);
        assertEquals(2, singerService.findAll().size());
        listSingerWithDetails(singerService.findAllWithDetails());
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
