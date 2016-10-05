package com.softserve.edu.delivery.dto.valid;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default PatternConstraints.PASS_NOT_MATCHES_MASSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
