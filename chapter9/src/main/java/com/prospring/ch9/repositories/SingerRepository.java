package com.prospring.ch9.repositories;

import com.prospring.ch9.entities.Singer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Integer> {
    @Query("select count(s) from Singer s")
    Long countAllSingers();
}
