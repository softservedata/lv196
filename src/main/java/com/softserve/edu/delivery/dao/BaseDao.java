package com.softserve.edu.delivery.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    void add(T element);

    void update(T element);

    void remove(T element);

    Optional<T> getById(Long id);

    List<T> getAll();

}