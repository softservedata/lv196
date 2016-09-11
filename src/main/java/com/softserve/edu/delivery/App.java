package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Just on purpose for quick testing ...
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User().setEmail("example2@site.com").setUserRole(Role.ADMIN);

        userDao.add(user);

        System.out.println(userDao.findByEmail("example2@site.com"));

        Hibernate.close();
    }
}