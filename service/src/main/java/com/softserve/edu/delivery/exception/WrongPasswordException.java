package com.softserve.edu.delivery.exception;

/**
 * This exception throws when user type wrong password
 * */
public class WrongPasswordException extends ApplicationException{

    public WrongPasswordException() {
        super("Sorry, you've written wrong password");
    }
}
