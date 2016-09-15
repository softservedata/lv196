package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;

public interface UserService {
    boolean exists(String email);
    void register(User user);
    boolean verificationLogin(UserAuthDTO user);

}
