package com.softserve.edu.delivery.service.social;


import com.softserve.edu.delivery.service.social.dto.SocialUserDTO;

public interface SocialUserService {

    SocialUserDTO parseResponse(String responseBody);

    void signIn(SocialUserDTO socialUser);

}
