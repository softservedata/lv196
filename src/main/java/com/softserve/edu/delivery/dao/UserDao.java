package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    Optional<User> findByEmail(String email);
}
