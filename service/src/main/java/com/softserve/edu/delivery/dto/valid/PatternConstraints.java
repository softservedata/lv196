package com.softserve.edu.delivery.dto.valid;

public class PatternConstraints {

    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String EMAIL_NOT_VALID_MESSAGE = "Email cannot be empty and should looks like 'example@domain.com'";

    public static final String PHONE_REGEX = "\\d+";
    public static final String PHONE_NOT_VALID_MASSAGE = "Phone number should contains only digits";

    public static final int PASS_MIN_LENGTH = 4;
    public static final int PASS_MAX_LENGTH = 20;
    public static final String PASS_NOT_VALID_MESSAGE =
            "Password should be from " + PASS_MIN_LENGTH + " to " + PASS_MAX_LENGTH + " characters";
    public static final String PASS_NOT_MATCHES_MASSAGE = "Passwords don't match";

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_NOT_VALID_MESSAGE =
            "Name cannot be empty and should be from " + NAME_MIN_LENGTH + " to " + NAME_MAX_LENGTH+ " characters";


    public static final String NOT_NULL_VALID_MASSAGE = "Cannot be empty";
}
