package com.prospring.ch8.service;

import com.prospring.ch8.entities.Singer;
import com.prospring.ch8.entities.Singer_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return entityManager.createNamedQuery(Singer.FIND_ALL, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithDetails() {
        return entityManager.createNamedQuery(Singer.FIND_ALL_WITH_DETAILS, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Integer id) {
        return entityManager.createNamedQuery(Singer.FIND_BY_ID, Singer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            entityManager.persist(singer);
            logger.info("Insert new singer");
        } else {
            entityManager.merge(singer);
            logger.info("Update singer");
        }
        logger.info("save Singer with id = {}", singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedSinger = entityManager.merge(singer);
        entityManager.remove(mergedSinger);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        String sql = "select id, first_name, last_name, birth_date, version from singer";
        return entityManager.createNativeQuery(sql, "singerResult").getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        logger.info("Finding singer with firstName = {} and lasName ={}", firstName, lastName);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = cb.createQuery(Singer.class);
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);
        criteriaQuery.select(singerRoot).distinct(true);
        Predicate criteria = cb.conjunction();
        if (firstName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.firstName), firstName);
            criteria = cb.and(criteria, p);
        }
        if (lastName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.lastName), lastName);
            criteria = cb.and(criteria, p);
        }
        criteriaQuery.where(criteria);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
