package com.softserve.edu.delivery.dto;


import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data transport object which service layer gets
 * from controller(login page)
 *@author Petro Shtenovych
 */
public class UserAuthDTO {

    @NotNull(message = "Email cannot be empty and should looks like example@domain.com")
    @Email
    private String email;

    @NotNull(message = "Password should be from 8 to 20 characters")
    @Size(min = 8, max = 20)
    private String password;

    public UserAuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserAuthDTO() {}

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

}