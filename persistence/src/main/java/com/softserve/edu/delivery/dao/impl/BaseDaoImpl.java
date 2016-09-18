package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.BaseDao;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private Class<T> clazz;
    private EntityManager em = Jpa.getEntityManager();

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

    protected EntityManager getEntityManager() {
        return em;
    }
}