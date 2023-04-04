package com.prospring.ch6.dao.support;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteAlbum extends SqlUpdate {
    private static final String SQL_DELETE_SINGER = "delete from album where id = :id";

    public DeleteAlbum(DataSource dataSource) {
        super(dataSource, SQL_DELETE_SINGER);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
