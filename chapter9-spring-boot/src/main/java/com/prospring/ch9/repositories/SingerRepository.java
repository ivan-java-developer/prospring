package com.prospring.ch9.repositories;

import com.prospring.ch9.entities.Singer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends CrudRepository<Singer, Integer> {
}
