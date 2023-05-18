package com.prospring.ch13;

import com.prospring.ch13.config.DataConfig;
import com.prospring.ch13.config.ServiceConfig;
import com.prospring.ch13.config.ServiceTestConfig;
import com.prospring.ch13.entities.Singer;
import com.prospring.ch13.services.SingerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = {ServiceTestConfig.class, ServiceConfig.class, DataConfig.class})
@DisplayName("Integration SingerService Test")
@ActiveProfiles("test")
public class SingerServiceTest {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceTest.class);

    @Autowired
    private SingerService singerService;

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = {"classpath:db/test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--")),
            @Sql(value = {"classpath:db/clean-up.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"))
    })
    public void testFindAll() {
        List<Singer> singers = singerService.findAll();

        assertNotNull(singers);
        assertEquals(1, singers.size());
    }

    @Test
    @DisplayName("should return singer 'John Mayer'")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--")),
            @Sql(value = "classpath:db/clean-up.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"))
    })
    public void testFindByFirstName() {
        List<Singer> singers = singerService.findByFirstName("John");

        assertNotNull(singers);
        assertEquals(1, singers.size());
        Singer singer = singers.get(0);
        assertEquals("John", singer.getFirstName());
        assertEquals("Mayer", singer.getLastName());

    }
}
