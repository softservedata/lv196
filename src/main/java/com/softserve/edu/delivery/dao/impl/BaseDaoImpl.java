package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.BaseDao;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;


public abstract class BaseDaoImpl<T> implements BaseDao<T> {

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
    public void update(T element) {
        em.merge(element);
    }

    @Override
    public void delete(T element) {
        em.remove(element);
    }

    @Override
    public T findById(Serializable id) {
        return em.find(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) em.createNamedQuery("Select o from " + clazz.getSimpleName() + " o");
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}