package com.softserve.edu.delivery.config;

import com.softserve.edu.delivery.domain.Role;

public class SecurityConstraints {

    // login and password parameters
    public static final String USERNAME_PARAM = "email";
    public static final String PASSWORD_PARAM = "password";

    public static final String CUSTOMER_ROLE = Role.CUSTOMER.getName();
    public static final String DRIVER_ROLE = Role.DRIVER.getName();
    public static final String ADMIN_ROLE = Role.ADMIN.getName();
    public static final String MODERATOR_ROLE = Role.MODERATOR.getName();

    public static final String AUTHENTICATED = "isAuthenticated()";
    public static final String CUSTOMER = "hasAuthority('Customer')";
    public static final String DRIVER = "hasAuthority('Driver')";
    public static final String CUSTOMER_OR_DRIVER = "hasAuthority('Customer') or hasAuthority('Driver')";
    public static final String ADMIN = "hasAuthority('Admin')";
    public static final String MODERATOR = "hasAuthority('Moderator')";

    public static final String[] STATIC_RESOURCES = {
            "/app/**",
            "/bower_components/**",
            "/css/**",
            "/i18n/**",
            "/img/**"
    };

    public static final String WELCOME_PAGE = "/welcome";
    public static final String LOGIN_PAGE = "/login";
    public static final String CUSTOMER_REG_PAGE = "/registration";
    public static final String CUSTOMER_REG_PROCESS = "/register";
    public static final String DRIVER_REG_PAGE = "/driverRegistration";
    public static final String DRIVER_REG_PROCESS = "/driverRegister";
    public static final String LOGIN_PROCESS_URL = "/loginProcess";
    public static final String REDIRECT_URL = "/authRedirect";
    public static final String FAILURE_LOGIN_URL = "/login?auth=false";
    public static final String LOGOUT_URL = "/login?logout";
    public static final String ACCESS_DENIED_URL = "/accessDenied";

    public static final String CUSTOMER_PAGE = "/#/orders/open";
    public static final String DRIVER_PAGE = "/#/driver";
    public static final String ADMIN_PAGE = "/#/users";
    public static final String MODERATOR_PAGE = "/#/feedbacks";
}