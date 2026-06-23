package com.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            WalletNotFoundException.class)
    public ResponseEntity<Map<String,Object>>
    handleWalletNotFound(
            WalletNotFoundException ex) {

        Map<String,Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                LocalDateTime.now());

        response.put(
                "status",
                HttpStatus.NOT_FOUND.value());

        response.put(
                "message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(
            InsufficientBalanceException.class)
    public ResponseEntity<Map<String,Object>>
    handleInsufficientBalance(
            InsufficientBalanceException ex) {

        Map<String,Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                LocalDateTime.now());

        response.put(
                "status",
                HttpStatus.BAD_REQUEST.value());

        response.put(
                "message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            InvalidTransferException.class)
    public ResponseEntity<Map<String,Object>>
    handleInvalidTransfer(
            InvalidTransferException ex) {

        Map<String,Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                LocalDateTime.now());

        response.put(
                "status",
                HttpStatus.BAD_REQUEST.value());

        response.put(
                "message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>
    handleGeneric(
            Exception ex) {

        Map<String,Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                LocalDateTime.now());

        response.put(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        response.put(
                "message",
                ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}