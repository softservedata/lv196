package com.softserve.edu.delivery.dto;

import java.util.Objects;

import com.softserve.edu.delivery.domain.User;

public class UserProfileDto {
	
	private String email;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	private String role;
	private String phone;
	private Integer rate;
    private String photoUrl;
    private String passport;
    private Boolean approved;
	
	public UserProfileDto() {
	}
	
    public static UserProfileDto create(User user) {
    	UserProfileDto userProfileDto = new UserProfileDto()
    			.setEmail(user.getEmail())
    			.setFirstName(user.getFirstName())
    			.setLastName(user.getLastName())
    			.setBlocked(user.getBlocked())	
    			.setRole(user.getUserRole() == null ? null : user.getUserRole().getName())
    			.setRate(user.getRate())
    			.setPhone(user.getPhone())
    			.setPhotoUrl(user.getPhotoUrl())
    			.setPassport(user.getPassport())
    			.setApproved(user.getApproved());
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
	
	public String getPhone() {
		return phone;
	}

	public UserProfileDto setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Integer getRate() {
		return rate;
	}

	public UserProfileDto setRate(Integer rate) {
		this.rate = rate;
		return this;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public UserProfileDto setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		return this;
	}

	public String getPassport() {
		return passport;
	}

	public UserProfileDto setPassport(String passport) {
		this.passport = passport;
		return this;
	}

	public Boolean getApproved() {
		return approved;
	}

	public UserProfileDto setApproved(Boolean approved) {
		this.approved = approved;
		return this;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileDto dto = (UserProfileDto) o;
        return Objects.equals(email, dto.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    
    @Override
    public String toString() {
        return "UserProfileDto {" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}