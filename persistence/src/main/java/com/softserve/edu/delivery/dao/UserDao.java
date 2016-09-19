package com.softserve.edu.delivery.dao;

import java.util.List;

import com.softserve.edu.delivery.domain.User;

public interface UserDao extends BaseDao<User, String> {
    boolean exists(String email);
    
    List<User> getAllUsersInRange(int page, int size);
}
