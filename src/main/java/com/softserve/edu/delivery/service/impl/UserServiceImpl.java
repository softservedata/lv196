package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.utils.TransactionManager;

public class UserServiceImpl implements UserService{
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean exists(String email) {
        return TransactionManager.withTransaction(() -> userDao.exists(email));
    }
}
