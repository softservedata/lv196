package com.softserve.edu.delivery.service.social.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class SocialAuthentication implements Authentication{

    private String email;

    private String userSocialId;

    private String userRole;

    public SocialAuthentication(String email, String userSocialId, String userRole) {
        this.email = email;
        this.userSocialId = userSocialId;
        this.userRole = userRole;
        this.authenticated = true;
    }

    private boolean authenticated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public Object getCredentials() {
        return userSocialId;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.email;
    }

    public String getUserRole() {
        return userRole;
    }
}
