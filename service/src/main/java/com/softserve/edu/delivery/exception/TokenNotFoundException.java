package com.softserve.edu.delivery.exception;

public class TokenNotFoundException extends ApplicationException {

    private static String message = "You've tried to follow non-existent link";

    public TokenNotFoundException() {
        super(message);
    }
}
