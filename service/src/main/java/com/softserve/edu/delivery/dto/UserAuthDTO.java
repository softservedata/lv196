package com.softserve.edu.delivery.dto;


/**
 *@author Petro Shtenovych
 */
public class UserAuthDTO {

    private String email;

    private String password;

    public UserAuthDTO(String email, String password) {
        this.email = (email != null) ? email : "null";
        this.password = (password != null) ? password : "null";
    }

    public UserAuthDTO() {
        this("", "");
    }

    public UserAuthDTO setEmail(String email) {
        this.email = (email != null) ? email : "null";
        return this;
    }

    public UserAuthDTO setPassword(String password) {
        this.password = (password != null) ? password : "null";
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