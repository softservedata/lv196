package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.utils.Hibernate;

import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    private Class<T> clazz;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void add(T element) {
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
    public void remove(T element) {
        Hibernate.withTransaction(session -> {
            session.delete(element);
            return null;
        });
    }

    @Override
    public T getById(Long id) {
        return Hibernate.withTransaction(session -> session.get(this.clazz, id));
    }

    @Override
    public List<T> getAll() {
        return Hibernate.withTransaction(session ->
            session.createQuery("From " + clazz.getSimpleName(), clazz).getResultList()
        );
    }
}