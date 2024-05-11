package com.ievlev.faceit.exceptions;

public class MealTypeNotFoundException extends RuntimeException {
    public MealTypeNotFoundException() {
    }

    public MealTypeNotFoundException(String message) {
        super(message);
    }

    public MealTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MealTypeNotFoundException(Throwable cause) {
        super(cause);
    }
}
