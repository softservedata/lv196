package com.softserve.edu.delivery.domain;

public enum NotificationStatus {
    INFO("Info"),
    WARNING("Warning"),
    SUCCESS("Success");

    String name;

    NotificationStatus(String name){this.name = name;}

    public String getName() {
        return name;
    }
}
