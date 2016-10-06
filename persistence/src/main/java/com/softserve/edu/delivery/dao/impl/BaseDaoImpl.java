package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.BaseDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private Class<T> clazz;

    @PersistenceContext
    private EntityManager em;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T element) {
        em.persist(element);
    }

    @Override
    public T update(T element) {
         return em.merge(element);
    }

    @Override
    public void delete(T element) {
        em.remove(element);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.ofNullable(em.find(this.clazz, id));
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("From " + clazz.getSimpleName(), clazz).getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    //Don't remove this method!!! It's need for some testing
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}