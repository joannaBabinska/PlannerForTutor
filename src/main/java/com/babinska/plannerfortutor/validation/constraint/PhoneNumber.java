package com.babinska.plannerfortutor.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface PhoneNumber {
  String message() default "Podano zły format numeru telefonu";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
