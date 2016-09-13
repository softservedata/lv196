package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Jpa;
import com.softserve.edu.delivery.utils.TransactionManager;

// Just on purpose for quick testing ...
public class App {
    public static void main(String[] args) {
        try {
            UserDao userDao = new UserDaoImpl();
            User user = new User().setEmail("example1213@site.com").setUserRole(Role.ADMIN);

            TransactionManager.withTransaction(() -> {
                userDao.save(user);
                userDao.findAll().forEach(System.out::println);
            });
        } finally {
            Jpa.close();
        }
    }
}