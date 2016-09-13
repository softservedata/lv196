package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Hibernate;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    public boolean exists(String email) {
        return Hibernate.withTransaction(session ->
                session.createQuery("select u from User u where u.email = :email", User.class)
                        .setParameter("email", email)
                        .getResultList()
                        .size() > 0
        );
    }
}
