package com.softserve.edu.delivery.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    void save(T element);

    void update(T element);

    void delete(T element);

    T findById(Serializable id);

    List<T> findAll();
}