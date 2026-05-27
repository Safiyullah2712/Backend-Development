package com.student.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class GlobalExceptionHandler {

    //Validation Errors
    @ExceptionHandler(
            MethodArgumentNotValidException.class)

    public ResponseEntity<Map<String, String>>
    handleValidationException(

            MethodArgumentNotValidException ex) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()

                .getFieldErrors()

                .forEach(error ->

                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    //Resource Not Found
    @ExceptionHandler(
            ResourceNotFoundException.class)

    public ResponseEntity<String>
    handleNotFound(

            ResourceNotFoundException ex) {

        return new ResponseEntity<>(

                ex.getMessage(),

                HttpStatus.NOT_FOUND
        );
    }

    //Duplicate Resource
    @ExceptionHandler(
            DuplicateResourceException.class)

    public ResponseEntity<String>
    handleDuplicate(

            DuplicateResourceException ex) {

        return new ResponseEntity<>(

                ex.getMessage(),

                HttpStatus.CONFLICT
        );
    }

    //General Exception
    @ExceptionHandler(Exception.class)

    public ResponseEntity<String>
    handleGeneral(Exception ex) {

        return new ResponseEntity<>(

                ex.getMessage(),

                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}