package com.softserve.edu.delivery.service.event;

import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String email;

    private String url;

    private String token;

    public OnRegistrationCompleteEvent(String email, String url,  String token) {
        super(email);
        this.email = email;
        this.url = url;
        this.token = token;
    }

    public OnRegistrationCompleteEvent setEmail(String email) {
        this.email = email;
        return this;
    }

    public OnRegistrationCompleteEvent setUrl(String url) {
        this.url = url;
        return this;
    }

    public OnRegistrationCompleteEvent setToken(String token) {
        this.token = token;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }
}
