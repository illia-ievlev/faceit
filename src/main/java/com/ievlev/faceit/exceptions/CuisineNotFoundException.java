package com.ievlev.faceit.exceptions;

public class CuisineNotFoundException extends RuntimeException {
    public CuisineNotFoundException() {
    }

    public CuisineNotFoundException(String message) {
        super(message);
    }

    public CuisineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CuisineNotFoundException(Throwable cause) {
        super(cause);
    }
}
