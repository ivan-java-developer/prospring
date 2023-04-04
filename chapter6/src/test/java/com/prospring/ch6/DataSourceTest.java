package com.prospring.ch6;

import com.prospring.ch6.config.DBConfig;
import com.prospring.ch6.config.EmbeddedJdbcConfig;
import com.prospring.ch6.dao.EmployeeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

public class DataSourceTest {
    private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

    @Test
    public void testOne() {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/datasource-config-01.xml");
        DataSource datasource = ctx.getBean("datasource", DataSource.class);
//        Assert.notNull(datasource, "Datasource is null!");
        assertNotNull(datasource, "Datasource is null!");
        testDataSource(datasource);
        ctx.close();
    }

    @Test
    public void testStoredFunction() {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/postgres-employee-cfg.xml");
        EmployeeDao employeeDao = ctx.getBean(EmployeeDao.class);
        assertNotNull(employeeDao);
        String name = employeeDao.findNameByIdWithStoredFunction(1L);
        assertEquals("John", name);
        ctx.close();
    }

    @Test
    public void testTwo() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
        DataSource datasource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(datasource, "Datasource is null!");
        testDataSource(datasource);
        ctx.close();
    }

    @Test
    public void testThree() {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext("classpath:spring/embedded-h2-cfg.xml");
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource, "Datasource is null");
        testEmbeddedDatasource(dataSource);
        ctx.close();
    }

    @Test
    public void testFour() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource, "Datasource is null");
        testEmbeddedDatasource(dataSource);
        ctx.close();
    }

    @Test
    public void testFive() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);
        EmployeeDao employeeDao = ctx.getBean("employeeDao", EmployeeDao.class);
        String name = employeeDao.findNameById(1L);
        assertEquals("John", name, "find employee with another name");
        ctx.close();
    }

    private void testEmbeddedDatasource(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("select id, name from employee")) {
            ResultSet resultSet = ps.executeQuery();
            assertTrue(resultSet.next());
            String name = resultSet.getString("name");
            assertEquals("John", name, "Select returns wrong result!");
            assertFalse(resultSet.next());
        } catch(Exception e) {
            Assertions.fail("Something unexpected happen!", e);
        }
    }

    private void testDataSource(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("select 1")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt(1);
//                Assert.state(resultSet.getInt(1) == 1, "Select returns wrong result!");
                assertEquals(1, mockVal, "Select returns wrong result!");
            }
        } catch(Exception e) {
            Assertions.fail("Something unexpected happen!", e);
        }
    }
}
