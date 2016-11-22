package com.softserve.edu.delivery.dto;

public class ConfigDto {

    private String email;
    private String wsEndpoint;

    public ConfigDto() {
    }

    public ConfigDto(String email, String wsEndpoint) {
        this.email = email;
        this.wsEndpoint = wsEndpoint;
    }

    public String getEmail() {
        return email;
    }

    public ConfigDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWsEndpoint() {
        return wsEndpoint;
    }

    public ConfigDto setWsEndpoint(String wsEndpoint) {
        this.wsEndpoint = wsEndpoint;
        return this;
    }
}
