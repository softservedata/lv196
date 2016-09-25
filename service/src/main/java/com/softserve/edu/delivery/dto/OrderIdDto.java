package com.softserve.edu.delivery.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This class is wrapper for Long id value
 * with validation annotations
 * Instance of this class controller receives
 * from user(tracking page) and then delivers
 * to service layer
 */
public class OrderIdDto {

    @NotNull(message = "Order is should be positive number")
    @Min(value = 1)
    @Max(value = Long.MAX_VALUE)
    private Long orderId;

    public OrderIdDto(Long orderId) {
        this.orderId = orderId;
    }

    public OrderIdDto() {}

    public Long getOrderId() {
        return orderId;
    }

    public OrderIdDto setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
}
