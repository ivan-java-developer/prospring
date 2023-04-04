package com.prospring.ch6.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.prospring.ch6.dao"})
public class NewSingerDaoConfig {

    private static Logger logger = LoggerFactory.getLogger(NewSingerDaoConfig.class);

    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder
                    .addScripts(
                            "classpath:db/h2/main/schema.sql",
                            "classpath:db/h2/main/stored-function.sql",
                            "classpath:db/h2/main/test-data.sql"
                    )
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        } catch (Exception e) {
            logger.error("DataSource wasn't initialize", e);
            return null;
        }
    }
}
