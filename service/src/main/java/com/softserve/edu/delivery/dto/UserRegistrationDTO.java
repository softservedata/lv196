package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.Gender;
import com.softserve.edu.delivery.dto.valid.PasswordMatches;
import com.softserve.edu.delivery.dto.valid.Validation;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserRegistrationDTO {

    @Email(message = Validation.EMAIL_NOT_VALID)
    private String userEmail;

    @Size(min = Validation.NAME_MIN_LENGTH, max = Validation.NAME_MAX_LENGTH, message = Validation.NAME_NOT_VALID)
	private String userFirstName;

    @Size(min = Validation.NAME_MIN_LENGTH, max = Validation.NAME_MAX_LENGTH, message = Validation.NAME_NOT_VALID)
	private String userLastName;

    @Size(min = Validation.PASS_MIN_LENGTH, max = Validation.PASS_MAX_LENGTH, message = Validation.PASS_NOT_VALID)
	private String userPassword;

    @Size(min = Validation.PASS_MIN_LENGTH, max = Validation.PASS_MAX_LENGTH, message = Validation.PASS_NOT_VALID)
	private String userConfirmPassword;

    @Pattern(regexp = Validation.PHONE, message = Validation.PHONE_NOT_VALID)
    private String userPhoneNumber;

    @NotNull
    private Gender gender;
    
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}