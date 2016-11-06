package com.softserve.edu.delivery.service.social;


import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.social.auth.SocialAuthentication;
import com.softserve.edu.delivery.service.social.auth.SocialAuthenticationManager;
import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LinkedInUserService implements SocialUserService{

    private final String ID_KEY = "id";
    private final String EMAIL_KEY = "emailAddress";
    private final String FIRST_NAME_KEY = "firstName";
    private final String LAST_NAME_KEY = "lastName";
    private final String PHOTO_URL_KEY = "pictureUrl";

    private final Logger logger = LoggerFactory.getLogger(LinkedInUserService.class);

    @Autowired
    UserService userService;

    @Override
    public SocialUserDTO parseResponse(String responseBody) {
        logger.info("Creating JSONObject from response body. Response body: " + responseBody);
        JSONObject jsonObject = new JSONObject(responseBody);

        SocialUserDTO socialUser = new SocialUserDTO();
        socialUser.setId(jsonObject.getString(ID_KEY));
        socialUser.setEmail(jsonObject.getString(EMAIL_KEY));
        socialUser.setFirstName(jsonObject.getString(FIRST_NAME_KEY));
        socialUser.setLastName(jsonObject.getString(LAST_NAME_KEY));
        socialUser.setPhone(null); //linkedIn doesn't have an opportunity to get a phone number
        socialUser.setPhotoUrl(jsonObject.getString(PHOTO_URL_KEY));

        logger.info("Return SocialUserDTO instance");
        return socialUser;
    }

    @Override
    @Transactional
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
            User newUser = getUser(socialUser);
            this.userService.save(newUser);
            userRole = Role.CUSTOMER.getName();
        }
        logger.info("Authenticating of social user instance");
        SocialAuthentication auth = new SocialAuthentication(socialUser.getEmail(), socialUser.getId(), userRole);
        SocialAuthenticationManager.authenticate(auth);
    }

    private User getUser(SocialUserDTO socialUser) {
        User newUser = new User();
        newUser.setEmail(socialUser.getEmail());
        newUser.setFirstName(socialUser.getFirstName());
        newUser.setLastName(socialUser.getLastName());
        newUser.setPhone(socialUser.getPhone());
        newUser.setPhotoUrl(socialUser.getPhotoUrl());
        newUser.setUserRole(Role.CUSTOMER);
        newUser.setApproved(true);

        return newUser;
    }
}
