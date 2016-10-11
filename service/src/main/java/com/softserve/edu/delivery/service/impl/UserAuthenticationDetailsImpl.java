package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

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
                Iterator<? extends GrantedAuthority> roles = ((UserDetails) principal).getAuthorities().iterator();
                if (roles.hasNext()) {
                    return roles.next().toString();
                }
            }
            return "";
        }
    }
}
