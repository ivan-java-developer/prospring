package com.prospring.ch8.repositories;

import com.prospring.ch8.entities.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Integer> {
    List<Singer> findByFirstName(String firstName);
}
