package com.softserve.edu.delivery.domain.chat;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),;

    String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
