package com.softserve.edu.delivery.dto;

public class UserRegistrationDTO {

	private String email_login;
	private String firstName;
	private String lastName;
	private String password1;
	private String password2;
	private String phoneNumber;
	private String passport;
	private String photoURL;
	
	
	public UserRegistrationDTO() {
	}

	public UserRegistrationDTO(String email_login, 
			String firstName, 
			String lastName, 
			String password1,
			String password2, 
			String phoneNumber, 
			String passport, 
			String photoURL) {
		this.email_login = email_login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password1 = password1;
		this.password2 = password2;
		this.phoneNumber = phoneNumber;
		this.passport = passport;
		this.photoURL = photoURL;
	}

	public String getEmail_login() {
		return email_login;
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

	public void setEmail_login(String email_login) {
		this.email_login = email_login;
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
