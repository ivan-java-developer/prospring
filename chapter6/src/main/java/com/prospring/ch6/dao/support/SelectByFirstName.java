package com.prospring.ch6.dao.support;

import com.prospring.ch6.entities.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SelectByFirstName extends MappingSqlQuery<Singer> {
    private static final String SQL_SELECT_BY_FIRST_NAME =
            "select id, first_name, last_name, birth_date from singer where first_name = :firstName";

    public SelectByFirstName(DataSource dataSource) {
        super(dataSource , SQL_SELECT_BY_FIRST_NAME);
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getInt("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        return singer;
    }
}
