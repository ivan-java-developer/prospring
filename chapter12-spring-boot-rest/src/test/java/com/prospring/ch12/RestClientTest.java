package com.prospring.ch12;

import com.prospring.ch12.entities.Singer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class RestClientTest {

    private final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listData";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";

    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testFindAll() {
        logger.info("Testing retrieve all singers");

        Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);

        assertNotNull(singers);
        assertEquals(3, singers.length);
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
        Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
        assertNotNull(singers);
        assertEquals(4, singers.length);
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

        Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
        assertNotNull(singers);
        boolean found = Arrays.stream(singers).anyMatch(singer -> singer.getId() == 3);
        assertFalse(found);
    }

    private void listSingers(Singer[] singers) {
        Arrays.stream(singers).forEach(singer -> logger.info(singer.toString()));
    }
}
