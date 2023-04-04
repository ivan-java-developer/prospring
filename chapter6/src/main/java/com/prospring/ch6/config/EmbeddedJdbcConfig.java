package com.prospring.ch6.config;

import com.prospring.ch6.dao.EmployeeDao;
import com.prospring.ch6.dao.EmployeeDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedJdbcConfig {
    private static final Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder
                    .addScripts("classpath:db/h2/schema.sql", "classpath:db/h2/test-data.sql")
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        } catch (Exception e) {
            logger.error("Datasource was not created!");
            return null;
        }
    }

    @Bean
    public EmployeeDao employeeDao() {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
        employeeDao.setDataSource(dataSource());
        return employeeDao;
    }
}
