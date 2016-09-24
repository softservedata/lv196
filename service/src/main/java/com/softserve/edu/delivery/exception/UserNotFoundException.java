package com.softserve.edu.delivery.exception;

/**
 * This exception throws when user's email
 * isn't exist in database
 */
public class UserNotFoundException extends ApplicationException {
    /**
     * @param email - non-existent email
     */
    public UserNotFoundException(String email) {
        super("Email " + email + " is not registered");
    }
}
