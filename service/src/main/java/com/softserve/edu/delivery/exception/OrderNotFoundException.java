package com.softserve.edu.delivery.exception;

/**
 * This exception throws then order
 * with given id not found
 */
public class OrderNotFoundException extends ApplicationException {
    /**
     * @param orderId - non-existent order id
     */
    public OrderNotFoundException(Long orderId) {
        super("The order identifier \'" + orderId + "\' does not exist");
    }
}