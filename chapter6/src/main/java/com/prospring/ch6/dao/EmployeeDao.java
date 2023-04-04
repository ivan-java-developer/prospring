package com.prospring.ch6.dao;

import java.sql.SQLException;

public interface EmployeeDao {
    String findNameById(Long id);
    String findNameByIdWithStoredFunction(Long id);
}
