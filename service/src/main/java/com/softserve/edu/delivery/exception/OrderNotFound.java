package com.softserve.edu.delivery.exception;

/**
 * This exception throws then order
 * with given id not found
 */
public class OrderNotFound extends ApplicationException {
    /**
     * @param orderId - non-existent order id
     */
    public OrderNotFound(Long orderId) {
        super("Order with " + orderId + " id isn't exist");
    }
}
