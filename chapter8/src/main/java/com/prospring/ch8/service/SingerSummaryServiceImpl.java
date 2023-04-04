package com.prospring.ch8.service;

import com.prospring.ch8.view.SingerSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<SingerSummary> findAll() {
        String jpql = "select new com.prospring.ch8.view.SingerSummary(s.firstName, s.lastName, a.title) " +
                "from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate = " +
                "(select max(a2.releaseDate) from Album a2 where s.id = a2.singer.id)";
        return entityManager.createQuery(jpql, SingerSummary.class).getResultList();
    }
}
