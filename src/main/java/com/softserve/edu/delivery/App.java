package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.impl.UserServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;

// Just on purpose for quick testing ...
public class App {
    public static void main(String[] args) {
        try {
            UserService userService = new UserServiceImpl(new UserDaoImpl());
            System.out.println(userService.exists("example@site.com"));
        } finally {
            Jpa.close();
        }
    }
}