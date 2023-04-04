package com.prospring.ch6.dao.support;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateSinger extends SqlUpdate {
    private static final String SQL_UPDATE_SINGER = "update singer set first_name = :firstName," +
            " last_name = :lastName, birth_date = :birthDate where id = :id";

    public UpdateSinger(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_SINGER);
        super.declareParameter(new SqlParameter("firstName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("lastName", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthDate", Types.DATE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
