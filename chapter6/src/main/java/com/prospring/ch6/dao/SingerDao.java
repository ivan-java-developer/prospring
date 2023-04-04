package com.prospring.ch6.dao;

import com.prospring.ch6.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findLastNameById(Integer id);
    String findFirstNameById(Integer id);
    void insert(Singer singer);
    void update(Singer singer);
    void delete(Singer singer);
    List<Singer> findAllWithDetail();
    void insertWithDetail(Singer singer);
}
