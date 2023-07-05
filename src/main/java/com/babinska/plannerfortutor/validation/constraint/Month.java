package com.babinska.plannerfortutor.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = MonthValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RUNTIME)
public @interface Month {
        String  message() default "Podano złą nazwe miesiąca";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
}
