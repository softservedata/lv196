package com.softserve.edu.delivery.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T, ID extends Serializable> {

    void save(T element);

    void update(T element);

    void delete(T element);

    Optional<T> findOne(ID id);

    List<T> findAll();
}