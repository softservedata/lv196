package com.softserve.edu.delivery.config.error;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@Configuration
public class ErrorHandleConfiguration {

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new ErrorPageRegister();
    }

    static class ErrorPageRegister implements ErrorPageRegistrar{

        private static final ErrorPage NOT_FOUND = new ErrorPage(HttpStatus.NOT_FOUND, PAGE_NOT_FOUND_URL);
        private static final ErrorPage SERVER_ERROR = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_URL);
        private static final ErrorPage ACCESS_DENIED = new ErrorPage(HttpStatus.FORBIDDEN, ACCESS_DENIED_URL);

        @Override
        public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
            errorPageRegistry.addErrorPages(NOT_FOUND);
            errorPageRegistry.addErrorPages(SERVER_ERROR);
            errorPageRegistry.addErrorPages(ACCESS_DENIED);
        }
    }
}