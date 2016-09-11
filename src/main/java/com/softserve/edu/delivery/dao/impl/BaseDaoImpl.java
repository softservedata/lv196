package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.BaseDao;
import com.softserve.edu.delivery.utils.Hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseDaoImpl<T, ID extends Serializable> implements BaseDao<T, ID> {

    private Class<T> clazz;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void save(T element) {
        Hibernate.withTransaction(session -> session.save(element));
    }

    @Override
    public void update(T element) {
        Hibernate.withTransaction(session -> {
            session.update(element);
            return null;
        });
    }

    @Override
    public void delete(T element) {
        Hibernate.withTransaction(session -> {
            session.delete(element);
            return null;
        });
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Hibernate.withTransaction(session -> Optional.ofNullable(session.get(this.clazz, id)));
    }

    @Override
    public List<T> findAll() {
        return Hibernate.withTransaction(session ->
            session.createQuery("From " + clazz.getSimpleName(), clazz).getResultList()
        );
    }
}