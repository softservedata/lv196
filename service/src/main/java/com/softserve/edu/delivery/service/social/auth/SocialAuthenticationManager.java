package com.softserve.edu.delivery.service.social.auth;


import org.springframework.security.core.context.SecurityContextHolder;

public class SocialAuthenticationManager {

    public static void authenticate(SocialAuthentication auth) {
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
