package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Role;
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
            throw new RuntimeException("Method is not allowed for not authenticated users");
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
    public Role getAuthenticatedUserRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Role result = null;
        if (auth == null) {
            throw new RuntimeException("Method is not allowed for not authenticated users");
        } else {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                String role = ((UserDetails) principal).getAuthorities().iterator().next().toString();
                result = Role.getRoleByString(role);
            }else if (principal instanceof SocialAuthentication) {
                String role = ((SocialAuthentication) principal).getUserRole();
                result =  Role.getRoleByString(role);
            }
            return result;
        }
    }
}
