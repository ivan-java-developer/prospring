package com.prospring.ch16.services;

import com.prospring.ch16.entities.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
    Page<Singer> findAllByPage(Pageable pageable);
}
