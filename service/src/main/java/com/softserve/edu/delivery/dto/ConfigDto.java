package com.softserve.edu.delivery.dto;

public class ConfigDto {

    private String email;
    private String wsEndpoint;
    private String googleGeocodeKey;

    public ConfigDto() {
    }

    public ConfigDto(String email, String wsEndpoint, String googleGeocodeKey) {
        this.email = email;
        this.wsEndpoint = wsEndpoint;
        this.googleGeocodeKey = googleGeocodeKey;
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

    public String getGoogleGeocodeKey() {
        return googleGeocodeKey;
    }

    public ConfigDto setGoogleGeocodeKey(String googleGeocodeKey) {
        this.googleGeocodeKey = googleGeocodeKey;
        return this;
    }
}
