package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.User;

public interface UserService {
    boolean exists(String email);
    void register(User user);

}
