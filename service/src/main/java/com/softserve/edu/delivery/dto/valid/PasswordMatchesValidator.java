package com.softserve.edu.delivery.dto.valid;

import com.softserve.edu.delivery.dto.UserRegistrationDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegistrationDTO> {
    @Override
    public void initialize(PasswordMatches passwordMatches) {
        //NOP
    }

    @Override
    public boolean isValid(UserRegistrationDTO user, ConstraintValidatorContext constraintValidatorContext) {
        return user.getPassword().equals(user.getConfirmPassword());
    }
}
