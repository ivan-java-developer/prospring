package com.prospring.ch6.dao.support;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class StoredFunctionNameById extends SqlFunction<String> {
    private static final String SQL_STORED_FUNCTION_GET_FIRST_NAME_BY_ID = "select get_first_name_by_id(?)";

    public StoredFunctionNameById(DataSource dataSource) {
        super(dataSource, SQL_STORED_FUNCTION_GET_FIRST_NAME_BY_ID);
        declareParameter(new SqlParameter(Types.BIGINT));
        compile();
    }
}
