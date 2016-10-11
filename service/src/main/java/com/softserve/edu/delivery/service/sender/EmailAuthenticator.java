package com.softserve.edu.delivery.service.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Component
public class EmailAuthenticator extends Authenticator {

    @Autowired
    Environment env;

    String email ;
    String password;

    @PostConstruct
    public void init() {
        this.email = env.getProperty("mail.sender.email");
        this.password = env.getProperty("mail.sender.password");
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }

    public String getEmail() {
        return email;
    }
}