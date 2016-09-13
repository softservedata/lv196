package com.softserve.edu.delivery.domain;

public enum  OrderStatus {
    NEW("New"),
    PENDING("Pending"),
    APPROVED("Approved"),
    ACTIVE("Active"),
    SUBMITTED("Submitted"),
    FINISHED("Finished");

    String name;

    OrderStatus(String name){this.name = name;}

    public String getName() {
        return name;
    }
}
