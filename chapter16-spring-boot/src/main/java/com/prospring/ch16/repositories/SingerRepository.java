package com.prospring.ch16.repositories;

import com.prospring.ch16.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
