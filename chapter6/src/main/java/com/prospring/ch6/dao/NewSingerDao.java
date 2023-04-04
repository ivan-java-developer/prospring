package com.prospring.ch6.dao;

import com.prospring.ch6.entities.Singer;

import java.util.List;

public interface NewSingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    void update(Singer singer);
    void insert(Singer singer);
    void delete(Singer singer);
    void insertWithAlbums(Singer singer);
}
