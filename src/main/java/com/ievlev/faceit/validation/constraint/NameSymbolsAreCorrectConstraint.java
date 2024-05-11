package com.ievlev.faceit.validation.constraint;

import com.ievlev.faceit.validation.NameSymbolsAreCorrectValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameSymbolsAreCorrectValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameSymbolsAreCorrectConstraint {
    String message() default "symbols aren't correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
