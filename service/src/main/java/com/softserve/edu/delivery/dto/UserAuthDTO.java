package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.dto.valid.PatternConstraints;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data transport object which service layer gets
 * from controller(login page)
 *@author Petro Shtenovych
 */
public class UserAuthDTO {

    @NotNull(message = PatternConstraints.EMAIL_NOT_VALID_MESSAGE)
    @Pattern( regexp = PatternConstraints.EMAIL_REGEX)
    private String email;

    @NotNull(message = PatternConstraints.PASS_NOT_VALID_MESSAGE)
    @Size(min = PatternConstraints.PASS_MIN_LENGTH, max = PatternConstraints.PASS_MAX_LENGTH)
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