package com.softserve.edu.delivery.dto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.softserve.edu.delivery.dto.valid.PatternConstraints.*;

public class UserAuthDTO {

    @NotNull(message = EMAIL_NOT_VALID_MESSAGE)
    @Email(message = EMAIL_NOT_VALID_MESSAGE)
    private String email;

    @NotNull(message = PASS_NOT_VALID_MESSAGE)
    @Size(min = PASS_MIN_LENGTH, max = PASS_MAX_LENGTH, message = PASS_NOT_VALID_MESSAGE)
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