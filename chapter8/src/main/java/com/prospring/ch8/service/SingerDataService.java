package com.prospring.ch8.service;

import com.prospring.ch8.entities.Singer;

import java.util.List;

public interface SingerDataService {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
