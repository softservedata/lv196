package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.chat.Gender;
import com.softserve.edu.delivery.dto.valid.PasswordMatches;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.softserve.edu.delivery.dto.valid.PatternConstraints.*;

@PasswordMatches
public class UserRegistrationDTO {

    @NotNull(message = EMAIL_NOT_VALID_MESSAGE)
    @Email
    private String userEmail;

    @NotNull(message = NAME_NOT_VALID_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
	private String userFirstName;

    @NotNull(message = NAME_NOT_VALID_MESSAGE)
    @Size(min = NAME_MIN_LENGTH, max = NAME_MAX_LENGTH)
	private String userLastName;

    @NotNull(message = PASS_NOT_VALID_MESSAGE)
    @Size(min = PASS_MIN_LENGTH, max = PASS_MAX_LENGTH)
	private String userPassword;

    @NotNull(message = PASS_NOT_VALID_MESSAGE)
    @Size(min = PASS_MIN_LENGTH, max = PASS_MAX_LENGTH)
	private String userConfirmPassword;

    @NotNull
    private Gender gender;

    @NotNull(message = PHONE_NOT_VALID_MASSAGE)
    @Pattern(regexp = PHONE_REGEX)
	private String userPhoneNumber;
    
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