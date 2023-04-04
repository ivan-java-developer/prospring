package com.prospring.ch8.service;

import com.prospring.ch8.entities.SingerAudit;

import java.util.List;
import java.util.Optional;

public interface SingerAuditService {
    List<SingerAudit> findAll();
    Optional<SingerAudit> findById(Integer id);
    SingerAudit save(SingerAudit singer);
    SingerAudit findByRevisionId(Integer id, Integer revision);
}
