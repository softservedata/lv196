package com.softserve.edu.delivery.exception;

/**
 * This exception throws when database errors occur
 * */
public class DatabaseException extends ApplicationException {

    public DatabaseException() {
        super("Some problems occur. Please try later.");
    }
}
