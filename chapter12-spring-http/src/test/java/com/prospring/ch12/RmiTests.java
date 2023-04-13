package com.prospring.ch12;

import com.prospring.ch12.config.RmiClientConfig;
import com.prospring.ch12.entities.Singer;
import com.prospring.ch12.services.SingerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = { RmiClientConfig.class })
@ExtendWith(SpringExtension.class)
public class RmiTests {

    private Logger logger = LoggerFactory.getLogger(RmiTests.class);

    @Autowired
    private SingerService singerService;

    @Test
    public void testRmiAll() {
        List<Singer> allSingers = singerService.findAll();
        assertEquals(3, allSingers.size());
        listSingers(allSingers);
    }

    @Test
    public void testRmiJohn() {
        List<Singer> singers = singerService.findByFirstName("John");
        assertEquals(2, singers.size());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers) {
        singers.forEach(s -> logger.info(s.toString()));
    }
}
