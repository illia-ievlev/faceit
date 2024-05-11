package com.ievlev.faceit.exception_handling;

import com.ievlev.faceit.dto.AppErrorStatusDto;
import com.ievlev.faceit.exceptions.*;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AddToOrderException.class})
    public ResponseEntity<?> handleAddToOrderException(AddToOrderException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstrainViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleBadCredentialsAuthorizeException(BadCredentialsException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.UNAUTHORIZED.value(), "login or password is not correct"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.UNAUTHORIZED.value(), "authentication exception"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({CuisineNotFoundException.class})
    public ResponseEntity<?> handleCuisineNotFoundException(CuisineNotFoundException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MealNotFoundException.class})
    public ResponseEntity<?> handleMealNotFoundException(MealNotFoundException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MealTypeNotFoundException.class})
    public ResponseEntity<?> handleMealTypeNotFoundException(MealTypeNotFoundException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NameAlreadyExistsException.class})
    public ResponseEntity<?> handleNameAlreadyExistsException(NameAlreadyExistsException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserIdNotFoundException.class})
    public ResponseEntity<?> handleUserIdNotFoundException(UserIdNotFoundException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ResponseEntity<>(new AppErrorStatusDto(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
