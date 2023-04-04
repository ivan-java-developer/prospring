package com.prospring.ch8.service;

import com.prospring.ch8.entities.SingerAudit;
import com.prospring.ch8.repositories.SingerAuditRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service("jpaSingerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<SingerAudit> findAll() {
        return singerAuditRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<SingerAudit> findById(Integer id) {
        return singerAuditRepository.findById(id);
    }

    @Override
    public SingerAudit save(SingerAudit singer) {
        return singerAuditRepository.save(singer);
    }

    @Transactional(readOnly = true)
    @Override
    public SingerAudit findByRevisionId(Integer id, Integer revision) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(SingerAudit.class, id, revision);
    }
}
