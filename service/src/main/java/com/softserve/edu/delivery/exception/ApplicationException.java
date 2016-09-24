package com.softserve.edu.delivery.exception;

/**
 * The main project unchecked exception
 */
public class ApplicationException extends RuntimeException {

    /**
     * @param message - Error message
     * */
    public ApplicationException(String message) {
        super(message);
    }
}
