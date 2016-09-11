package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Hibernate;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    public Optional<User> findByEmail(String email) {
        return Hibernate.withTransaction(session -> {
            List<User> users = session
                    .createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email).getResultList();

            return Optional.ofNullable(users.size() > 0 ? users.get(0) : null);
        });
    }
}
