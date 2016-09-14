package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.utils.Jpa;
import com.softserve.edu.delivery.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


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

    public void register(User user, boolean withoutLambda) {
        if (!withoutLambda) {
            register(user);
        } else {
            EntityTransaction tx = null;
            try {
                EntityManager entityManager = Jpa.getEntityManager();
                tx = entityManager.getTransaction();
                tx.begin();
                if (! userDao.exists(user.getEmail())) {
                    throw new IllegalArgumentException("User with given email already exists.");
                } else {
                    userDao.save(user);
                    tx.commit();
                }
            }catch (Exception rollback) {
                if (tx != null) tx.rollback();
            }
        }
    }
}
