package com.prospring.ch8.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerSummaryUntype")
@Repository
@Transactional
public class SingerSummaryUntypeImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public void displayAllSummary() {
        String jpql = "select s.firstName, s.lastName, a.title " +
                "from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate = " +
                "(select max(a2.releaseDate) from Album a2 where s.id = a2.singer.id)";
        List list = entityManager.createQuery(jpql).getResultList();
        for (Object item : list) {
            Object[] values = (Object[]) item;
            System.out.println(values[0] + " " + values[1] + " - " + values[2]);
        }

    }
}
