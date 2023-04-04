package com.prospring.ch8.config;

import org.h2.server.TcpServer;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.prospring.ch8"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.prospring.ch8"})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareBean")
public class JpaConfig {
    private static Logger logger = LoggerFactory.getLogger(JpaConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder
                    .addScripts("classpath:sql/schema.sql", "classpath:sql/test-data.sql")
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        } catch(Exception e) {
            logger.error("Datasource cannot be created!", e);
            return null;
        }
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp");
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        return Server.createWebServer("-web");
    }

    private Properties hibernateProperties() {
        Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProps.setProperty("hibernate.max_fetch_depth", "3");
        hibernateProps.setProperty("hibernate.jdbc.fetch_size", "50");
        hibernateProps.setProperty("hibernate.jdbc.batch_size", "10");
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", "none");
        hibernateProps.setProperty("hibernate.format_sql", "true");
        hibernateProps.setProperty("hibernate.use_sql_comments", "true");
        hibernateProps.setProperty("org.hibernate.envers.audit_table_suffix", "_H");
        hibernateProps.setProperty("org.hibernate.envers.revision_field_name", "AUDIT_REVISION");
        hibernateProps.setProperty("org.hibernate.envers.revision_type_field_name", "ACTION_TYPE");
        hibernateProps.setProperty("org.hibernate.envers.audit_strategy",
                "org.hibernate.envers.strategy.ValidityAuditStrategy");
        hibernateProps.setProperty("org.hibernate.envers.audit_strategy_validity_end_rev_field_name",
                "AUDIT_REVISION_END");
        hibernateProps.setProperty("org.hibernate.envers.audit_strategy_validity_store_revend_timestamp",
                "true");
        hibernateProps.setProperty("org.hibernate.envers.audit_strategy_validity_revend_timestamp_field_name",
                "AUDIT_REVISION_END_TS");
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
        factoryBean.setPackagesToScan("com.prospring.ch8.entities");
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }
}
