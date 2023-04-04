package com.prospring.ch6;

import com.prospring.ch6.config.EmbeddedSingerJdbcConfig;
import com.prospring.ch6.dao.SingerDao;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JdbcCfgTest {
    @Test
    public void testH2DB() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        assertEquals("Mayer", singerDao.findLastNameById(1));
        assertEquals("Eric", singerDao.findFirstNameById(2));
        ctx.close();
    }
}
