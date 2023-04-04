package com.prospring.ch6.config;

import com.prospring.ch6.dao.JdbcSingerDao;
import com.prospring.ch6.dao.SingerDao;
import com.prospring.ch6.entities.Album;
import com.prospring.ch6.entities.Singer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedSingerJdbcConfig {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedSingerJdbcConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/h2/main/schema.sql", "classpath:db/h2/main/test-data.sql")
                    .build();
        } catch (Exception e) {
            logger.error("Error when creating datasource");
            return null;
        }
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SingerDao singerDao() {
        JdbcSingerDao singerDao = new JdbcSingerDao();
        singerDao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate());
        return singerDao;
    }

    @Bean
    @Scope("prototype")
    public Singer singer() {
        return new Singer();
    }

    @Bean
    @Scope("prototype")
    public Album album() {
        return new Album();
    }
}
