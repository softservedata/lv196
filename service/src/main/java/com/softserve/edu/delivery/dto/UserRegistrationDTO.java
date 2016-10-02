package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.dto.valid.PasswordMatches;
import com.softserve.edu.delivery.dto.valid.PatternConstraints;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserRegistrationDTO {

    //@Pattern(regexp = PatternConstraints.EMAIL_REGEX)
    @NotNull(message = PatternConstraints.EMAIL_NOT_VALID_MESSAGE)
    @Email
    private String email;

    @NotNull(message = PatternConstraints.NAME_NOT_VALID_MESSAGE)
    @Size(min = PatternConstraints.NAME_MIN_LENGTH, max = PatternConstraints.NAME_MAX_LENGTH)
	private String firstName;

    @NotNull(message = PatternConstraints.NAME_NOT_VALID_MESSAGE)
    @Size(min = PatternConstraints.NAME_MIN_LENGTH, max = PatternConstraints.NAME_MAX_LENGTH)
	private String lastName;

    @NotNull(message = PatternConstraints.PASS_NOT_VALID_MESSAGE)
    @Size(min = PatternConstraints.PASS_MIN_LENGTH, max = PatternConstraints.PASS_MAX_LENGTH)
	private String password;

    @NotNull(message = PatternConstraints.PASS_NOT_VALID_MESSAGE)
    @Size(min = PatternConstraints.PASS_MIN_LENGTH, max = PatternConstraints.PASS_MAX_LENGTH)
	private String confirmPassword;

    @NotNull(message = PatternConstraints.PHONE_NOT_VALID_MASSAGE)
    @Pattern(regexp = PatternConstraints.PHONE_REGEX)
	private String phoneNumber;

    @NotNull(message = PatternConstraints.NOT_NULL_VALID_MASSAGE)
	private String passport;

    @NotNull(message = PatternConstraints.NOT_NULL_VALID_MASSAGE)
	private String photoUrl;

    public UserRegistrationDTO(String email,
                               String firstName,
                               String lastName,
                               String password,
                               String confirmPassword,
                               String phoneNumber,
                               String passport,
                               String photoUrl) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.passport = passport;
        this.photoUrl = photoUrl;
    }

    public UserRegistrationDTO() {}
    
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassport() {
        return passport;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}