package com.softserve.edu.delivery.dto;


/**
 *@author Petro Shtenovych
 */
public class UserAuthDTO {

    private String email;

    private String password;

    public UserAuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAuthDTO() {
        this("", "");
    }

    public UserAuthDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserAuthDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserAuthDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}