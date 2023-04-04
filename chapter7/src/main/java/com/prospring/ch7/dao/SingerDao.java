package com.prospring.ch7.dao;

import com.prospring.ch7.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findAllWithDetails();
    Singer findById(Integer id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
