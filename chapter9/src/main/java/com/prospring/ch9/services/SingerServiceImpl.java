package com.prospring.ch9.services;

import com.prospring.ch9.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.xa.XAException;
import java.util.ArrayList;
import java.util.List;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

    private final String FIND_ALL = "select s from Singer s";

    @PersistenceContext(unitName = "emfA")
    private EntityManager emA;

    @PersistenceContext(unitName = "emfB")
    private EntityManager emB;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        List<Singer> allinA = findAllinA();
        List<Singer> allInB = findAllInB();
        if (allinA.size() != allInB.size()) {
            throw new RuntimeException("XA resources does not contains the same expected data");
        }
        Singer sA = allinA.get(0);
        Singer sB = allInB.get(0);
        if (!sA.getFirstName().equals(sB.getFirstName())) {
            throw new RuntimeException("XA resources does not contains the same expected data");
        }
        List<Singer> singersFromBoth = new ArrayList<>();
        singersFromBoth.addAll(allinA);
        singersFromBoth.addAll(allInB);
        return singersFromBoth;
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Integer id) {
        throw new NotImplementedException("findAll");
    }

    @Override
    public Long countAll() {
        return 0L;
    }

    @Override
    public Singer save(Singer singer) {
        Singer singerB = new Singer();
        singerB.setFirstName(singer.getFirstName());
        singerB.setLastName(singer.getLastName());
        if (singer.getId() == null) {
            emA.persist(singer);
            emB.persist(singerB);
            if (true) {
                throw new JpaSystemException(
                        new PersistenceException("Simulation of something going wrong."));
            }
        } else {
            emA.merge(singer);
            emB.merge(singerB);
        }
        return singer;
    }



    private List<Singer> findAllinA() {
        return emA.createQuery(FIND_ALL, Singer.class).getResultList();
    }

    private List<Singer> findAllInB() {
        return emB.createQuery(FIND_ALL, Singer.class).getResultList();
    }
}
