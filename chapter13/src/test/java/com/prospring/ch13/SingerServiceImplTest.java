package com.prospring.ch13;

import com.prospring.ch13.config.DataConfig;
import com.prospring.ch13.config.ServiceConfig;
import com.prospring.ch13.config.ServiceTestConfig;
import com.prospring.ch13.entities.Singer;
import com.prospring.ch13.services.SingerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceTestConfig.class, ServiceConfig.class, DataConfig.class})
@TestExecutionListeners(listeners = {ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class SingerServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SingerService singerService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet = "/com/prospring/ch13/SingerServiceImplTest.xls")
    @Test
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();

        assertNotNull(singers);
        assertEquals(1, singers.size());
    }

    @DataSets(setUpDataSet = "/com/prospring/ch13/SingerServiceImplTest.xls")
    @Test
    public void testFindByFirstName() {
        List<Singer> singers = singerService.findByFirstName("John");

        assertNotNull(singers);
        assertEquals(1, singers.size());
        assertEquals("John", singers.get(0).getFirstName());
    }

    @DataSets(setUpDataSet = "/com/prospring/ch13/SingerServiceImplTest.xls")
    @Test
    public void testFindByFirstNameWhenSingerNotExists() {
        List<Singer> singers = singerService.findByFirstName("BB");

        assertNotNull(singers);
        assertEquals(0, singers.size());
    }

    @Test
    public void testAddSinger() {
        deleteFromTables("SINGER");

        Singer singer = new Singer();
        singer.setFirstName("Viktor");
        singer.setLastName("Tsoy");

        singerService.save(singer);
        em.flush();

        List<Singer> singers = singerService.findAll();
        assertEquals(1, singers.size());
    }

    @Test
    public void testError() {
        Assertions.assertThrows(AssertionError.class, () -> {
            deleteFromTables("SINGER");

            Singer singer = new Singer();
            singer.setFirstName("Viktor");
            singer.setLastName("Tsoy");

            singerService.save(singer);
            em.flush();

            List<Singer> singers = singerService.findAll();
            assertEquals(0, singers.size());
        });
    }
}
