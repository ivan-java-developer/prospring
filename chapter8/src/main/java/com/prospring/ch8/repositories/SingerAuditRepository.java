package com.prospring.ch8.repositories;

import com.prospring.ch8.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Integer> {
    List<SingerAudit> findAll();
    Optional<SingerAudit> findById(Integer id);
    SingerAudit save(SingerAudit singer);
}
