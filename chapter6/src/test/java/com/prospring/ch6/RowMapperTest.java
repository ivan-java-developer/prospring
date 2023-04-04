package com.prospring.ch6;

import com.prospring.ch6.config.EmbeddedSingerJdbcConfig;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RowMapperTest {
    @Test
    public void findAllTest() {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedSingerJdbcConfig.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        List<Singer> singers = singerDao.findAll();
        assertEquals(3, singers.size());

        singers.forEach(singer -> {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    System.out.println(album);
                }
            }
        });
        ctx.close();
    }
}
