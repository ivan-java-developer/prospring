package com.prospring.ch13.services;

import com.prospring.ch13.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
