package com.softserve.edu.delivery.service;


import com.softserve.edu.delivery.domain.Role;

public interface UserAuthenticationDetails {

    String getAuthenticatedUserEmail();

    Role getAuthenticatedUserRole();
}
