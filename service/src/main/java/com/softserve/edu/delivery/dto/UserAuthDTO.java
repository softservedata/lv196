package com.softserve.edu.delivery.dto;

public class UserAuthDTO {

    private String email;

    private String password;

    public UserAuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAuthDTO() {}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}