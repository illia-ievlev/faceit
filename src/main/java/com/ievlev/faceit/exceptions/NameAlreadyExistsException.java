package com.ievlev.faceit.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException() {
    }

    public NameAlreadyExistsException(String message) {
        super(message);
    }

    public NameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
