package com.softserve.edu.delivery.dao;

import java.util.List;

public interface Dao<T> {

    void add(T element);

    void update(T element);

    void remove(T element);

    T getById(Long id);

    List<T> getAll();

}