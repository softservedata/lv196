package com.softserve.edu.delivery.dto.valid;

public class Validation {

    public static final String EMAIL_NOT_VALID = "Email cannot be empty and should looks like 'example@domain.com'";

    public static final String PHONE = "^[0-9]{9,14}$";
    public static final String PHONE_NOT_VALID = "Phone number should contains only digits from and size must be from 9 to 14";

    public static final int PASS_MIN_LENGTH = 4;
    public static final int PASS_MAX_LENGTH = 20;
    public static final String PASS_NOT_VALID =
            "Password should be from " + PASS_MIN_LENGTH + " to " + PASS_MAX_LENGTH + " characters";

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_NOT_VALID =
            "Name cannot be empty and should be from " + NAME_MIN_LENGTH + " to " + NAME_MAX_LENGTH + " characters";
}
