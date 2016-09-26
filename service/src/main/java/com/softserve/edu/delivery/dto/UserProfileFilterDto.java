package com.softserve.edu.delivery.dto;

import java.util.Objects;
import java.util.function.Predicate;

import com.softserve.edu.delivery.domain.User;

public class UserProfileFilterDto implements Predicate<User> {
	
	private final UserProfileDto delegate;

	public UserProfileFilterDto(UserProfileDto delegate) {
		this.delegate = delegate;
	}

	@Override
	public boolean test(User user) {
		return  checkMail(user)
				&& checkRole(user)
				&& checkBlocked(user)
				&& checkLastName(user)
				&& checkFirstName(user);
	}
	
	private boolean checkMail(User user) {
		return Objects.nonNull(user.getEmail()) ? Objects.equals(user.getEmail(), delegate.getEmail()) : true;
	}

	private boolean checkBlocked(User user) {
		return Objects.equals(user.getBlocked(), delegate.getBlocked());
	}

	private boolean checkRole(User user) {
		return Objects.equals(user.getUserRole(), delegate.getRole());
	}
	
	private boolean checkFirstName(User user) {
		return Objects.equals(user.getFirstName(), delegate.getFirstName());
	}
	
	private boolean checkLastName(User user) {
		return Objects.equals(user.getLastName(), delegate.getLastName());
	}
}