package com.prospring.ch9.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.prospring.ch9.repositories"})
public class DataJpaConfig {
    private static Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = Class.forName("org.h2.Driver").asSubclass(Driver.class);
            dataSource.setDriverClass(driver);
            dataSource.setUrl("jdbc:h2:mem:musicdb;DB_CLOSE_DELAY=-1");
            dataSource.setUsername("prospring");
            dataSource.setPassword("password");
            return dataSource;
        } catch(Exception e) {
            logger.error("Datasource cannot be created!", e);
            return null;
        }
    }

    private Properties hibernateProperties() {
        Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProps.setProperty("hibernate.max_fetch_depth", "3");
        hibernateProps.setProperty("hibernate.jdbc.fetch_size", "50");
        hibernateProps.setProperty("hibernate.jdbc.batch_size", "10");
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", "update");
//        hibernateProps.setProperty("hibernate.format_sql", "true");
//        hibernateProps.setProperty("hibernate.use_sql_comments", "true");
        return hibernateProps;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.prospring.ch9.entities");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }
}
