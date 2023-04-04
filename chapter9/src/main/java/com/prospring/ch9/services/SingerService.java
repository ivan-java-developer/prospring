package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;

import java.util.List;

public interface SingerService  {
    List<Singer> findAll();
    Singer findById(Integer id);
    Singer save(Singer singer);
    Long countAll();
}
