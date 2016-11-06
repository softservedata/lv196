package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.social.auth.SocialAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationDetailsImpl implements UserAuthenticationDetails {

    @Override
    public String getAuthenticatedUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "";
        } else {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else if (principal instanceof SocialAuthentication) {
                return ((SocialAuthentication) principal).getName();
            }
        }
        return "";
    }

    @Override
    public String getAuthenticatedUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "";
        } else {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                return  ((UserDetails) principal).getAuthorities().iterator().next().toString();
            }else if (principal instanceof SocialAuthentication) {
                return ((SocialAuthentication) principal).getUserRole();
            }
            return "";
        }
    }
}
