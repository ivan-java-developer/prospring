package com.prospring.ch6.dao;

import com.prospring.ch6.MySqlErrorCodesTranslator;
import com.prospring.ch6.dao.support.StoredFunctionNameById;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private StoredFunctionNameById storedFunctionNameById;

    @Override
    public String findNameById(Long id) {
        String res = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select name from employee where id = ?")) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getString("name");
            }
        } catch (SQLException e) {
            logger.warn("exception when findNameById({})", id);
        }
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null || jdbcTemplate == null) {
            throw new BeanCreationException("Datasource and JdbcTemplate must not be null");
        }
    }

    @Override
    public String findNameByIdWithStoredFunction(Long id) {
        List<String> list = storedFunctionNameById.execute(id);
        return list.get(0);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        MySqlErrorCodesTranslator sqlErrorTranslator = new MySqlErrorCodesTranslator();
        sqlErrorTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(sqlErrorTranslator);
        storedFunctionNameById = new StoredFunctionNameById(dataSource);
        this.jdbcTemplate = jdbcTemplate;
    }
}
