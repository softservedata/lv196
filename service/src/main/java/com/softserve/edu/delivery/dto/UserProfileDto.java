package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.User;

public class UserProfileDto {
	
	private String email;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	private String role;
	
	public UserProfileDto() {
	}
	
    public static UserProfileDto create(User user) {
    	UserProfileDto userProfileDto = new UserProfileDto();
    	userProfileDto.setBlocked(user.getBlocked());
    	userProfileDto.setEmail(user.getEmail());
    	userProfileDto.setFirstName(user.getFirstName());
    	userProfileDto.setLastName(user.getLastName());
    	userProfileDto.setRole(user.getUserRole() == null ? null : user.getUserRole().getName());
		return userProfileDto;
    	
    }

	public String getEmail() {
		return email;
	}

	public UserProfileDto setEmail(String email) {
		this.email = email;
		return this;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public UserProfileDto setBlocked(Boolean blocked) {
		this.blocked = blocked;
		return this;
	}

	public String getRole() {
		return role;
	}

	public UserProfileDto setRole(String role) {
		this.role = role;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserProfileDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserProfileDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

}