package com.softserve.edu.delivery.dto;

public class UserRegistrationDto {

	private String email;
	private String firstName;
	private String lastName;
	private String password1;
	private String password2;
	private String phoneNumber;
	private String passport;
	private String photoURL;
	
    public UserRegistrationDto() {
    }
    
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword1() {
        return password1;
    }
    public String getPassword2() {
        return password2;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassport() {
        return passport;
    }
    public String getPhotoURL() {
        return photoURL;
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
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassport(String passport) {
        this.passport = passport;
    }
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
	
}