package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.utils.TransactionManager;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean exists(String email) {
        return TransactionManager.withTransaction(() -> userDao.exists(email));
    }

    @Override
    public void register(User user) {
        TransactionManager.withTransaction(() -> {
            if (!userDao.exists(user.getEmail())) {
                userDao.save(user);
            } else {
                throw new IllegalArgumentException("User with given email already exists.");
            }
        });
    }
}
