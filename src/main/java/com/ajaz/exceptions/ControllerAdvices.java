package com.ajaz.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(CurrencyMismatchException.class)
    public ResponseEntity<String> handleCurrencyMismatchException(CurrencyMismatchException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleCurrencyMismatchException(InsufficientBalanceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
