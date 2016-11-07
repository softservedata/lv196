package com.softserve.edu.delivery.service.social;


import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.social.auth.SocialAuthentication;
import com.softserve.edu.delivery.service.social.auth.SocialAuthenticationManager;
import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialUserServiceImpl implements SocialUserService{

    private final Logger logger = LoggerFactory.getLogger(SocialUserServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public void signIn(SocialUserDTO socialUser) {
        logger.info("Signing in social user, email:" + socialUser.getEmail());
        String userRole;
        if (userService.exists(socialUser.getEmail())) {
            logger.info("User exists in database");
            User dbUser = userService.findOne(socialUser.getEmail());
            dbUser.setPhotoUrl(socialUser.getPhotoUrl());
            userRole = dbUser.getUserRole().getName();
            userService.save(dbUser);
        } else {
            logger.info("User doesn't exist in database. Creating and saving User instance");
            User newUser = SocialUserDTO.getUser(socialUser);
            this.userService.save(newUser);
            userRole = Role.CUSTOMER.getName();
        }
        logger.info("Authenticating of social user instance");
        SocialAuthentication auth = new SocialAuthentication(socialUser.getEmail(), socialUser.getId(), userRole);
        SocialAuthenticationManager.authenticate(auth);
    }
}
