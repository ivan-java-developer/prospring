package com.prospring.ch7.dao;

import com.prospring.ch7.entities.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s", Singer.class)
                .list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithDetails() {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("Singer.findAllWithDetails", Singer.class)
                .list();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Integer id) {
        return sessionFactory.getCurrentSession()
                .createNamedQuery("Singer.findById", Singer.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        logger.info("Singer with id = {} saved!", singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        sessionFactory.getCurrentSession().delete(singer);
        logger.info("Singer with id = {} was deleted!", singer.getId());
    }
}
