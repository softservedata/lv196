package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Hibernate;

public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User findByEmail(String email) {
        return Hibernate.withTransaction(session -> {
            return session.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        });
    }
}
