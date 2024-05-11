package com.ievlev.faceit.validation;

import com.ievlev.faceit.validation.constraint.NameSymbolsAreCorrectConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameSymbolsAreCorrectValidation implements ConstraintValidator<NameSymbolsAreCorrectConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-zA-Z0-9\\s]*");
        //simple validation that can be changed if needed. I did it because I don't want to have any strange symbols in name
    }
}
