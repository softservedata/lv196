package com.softserve.edu.delivery.config;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;

public class ApplicationErrorPageRegistrator implements ErrorPageRegistrar {

    private static final ErrorPage NOT_FOUND = new ErrorPage(HttpStatus.NOT_FOUND, "/welcome?nf=true");

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(NOT_FOUND);
    }
}
