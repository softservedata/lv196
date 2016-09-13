package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    public boolean exists(String email) {
        return getEntityManager()
                .createQuery("select 1 from User u where u.email = :email")
                .setParameter("email", email)
                .getResultList()
                .size() > 0;
    }
}
