package com.softserve.edu.delivery.domain;

public enum  OrderStatus {
    OPEN("Open"),
    IN_PROGRESS("In progress"),
    APPROVED("Approved"),
    CLOSED("Closed");

    String name;

    OrderStatus(String name){this.name = name;}

    public String getName() {
        return name;
    }
}
