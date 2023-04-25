package com.prospring.ch12;

import com.prospring.ch12.config.RestClientConfig;
import com.prospring.ch12.entities.Singer;
import com.prospring.ch12.entities.Singers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

    private final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listData";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        assertNotNull(restTemplate);
    }

    @Test
    public void testFindAll() {
        logger.info("Testing retrieve all singers");

        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);

        assertNotNull(singers);
        assertEquals(3, singers.getSingers().size());
        listSingers(singers);
    }

    @Test
    public void testFindById() {
        logger.info("Testing retrieve singer by id");

        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);

        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testCreate() {
        logger.info("Testing create singer");
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new GregorianCalendar(1940, Calendar.SEPTEMBER, 16).getTime());

        Singer newSinger = restTemplate.postForObject(URL_CREATE_SINGER, singer, Singer.class);

        assertNotNull(newSinger);
        logger.info("Singer created successfully" + newSinger);
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        assertEquals(4, singers.getSingers().size());
        listSingers(singers);
    }

    @Test
    public void testUpdate() {
        logger.info("Testing update singer");
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 2);
        assertNotNull(singer);
        singer.setFirstName("John Clayton");

        restTemplate.put(URL_UPDATE_SINGER, singer, 2);

        singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 2);
        assertNotNull(singer);
        assertEquals("John Clayton", singer.getFirstName());
    }

    @Test
    public void testDelete() {
        logger.info("Testing delete singer");

        restTemplate.delete(URL_DELETE_SINGER, 3);

        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertNotNull(singers);
        boolean found = singers.getSingers().stream().anyMatch(singer -> singer.getId() == 3);
        assertFalse(found);
    }

    private void listSingers(Singers singers) {
        singers.getSingers().forEach(singer -> logger.info(singer.toString()));
    }
}
