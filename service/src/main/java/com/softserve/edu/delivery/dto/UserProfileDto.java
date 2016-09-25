package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.User;

public class UserProfileDto {

	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String passport;
	private String role;
	private String approved;

	public static UserProfileDto createInstance(User user) {
		UserProfileDto dto = new UserProfileDto();
		dto.email = user.getEmail();
		dto.firstName = (user.getFirstName() != null) ? user.getFirstName() : "";
		dto.lastName = (user.getLastName() != null) ? user.getLastName() : "";
		dto.phoneNumber = (user.getPhone() != null) ? user.getPhone() : "";
		dto.phoneNumber = user.getUserRole().getName();
		dto.phoneNumber = (user.getApproved()) ? "Yes" : "No";
		return dto;
	}

	private UserProfileDto() {}

	//<---------------------SETTERS----------------->

	public UserProfileDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserProfileDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public UserProfileDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public UserProfileDto setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}

	public UserProfileDto setPassport(String passport) {
		this.passport = passport;
		return this;
	}

	public UserProfileDto setRole(String role) {
		this.role = role;
		return this;
	}

	public UserProfileDto setApproved(String approved) {
		this.approved = approved;
		return this;
	}

	//<-----------------------Getters---------------->

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassport() {
		return passport;
	}

	public String getRole() {
		return role;
	}

	public String getApproved() {
		return approved;
	}

}