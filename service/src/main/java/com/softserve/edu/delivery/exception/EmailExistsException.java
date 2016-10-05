package com.softserve.edu.delivery.exception;


public class EmailExistsException extends ApplicationException {
    /**
     * @param email - Email which was registered
     */
    public EmailExistsException(String email) {
        super("Email " + email + " was registered");
    }
}
