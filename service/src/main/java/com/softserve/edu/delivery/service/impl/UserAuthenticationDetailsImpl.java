package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationDetailsImpl implements UserAuthenticationDetails {

    @Override
    public String getAuthenticatedUserEmail() {
        String userName;
        Object principal;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "";
        } else {
            principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                userName = ((UserDetails) principal).getUsername();
                return userName;
            }
        }
        return "";
    }
}
