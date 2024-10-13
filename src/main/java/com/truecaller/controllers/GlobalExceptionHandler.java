package com.truecaller.controllers;


import com.truecaller.error.ErrorResponse;
import com.truecaller.exceptions.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<?> handleProfileNotFoundException(ProfileNotFoundException exception){
        ErrorResponse profileNotFound = new ErrorResponse
                (LocalDateTime.now(), exception.getMessage(), "Profile Not Found");
        return new ResponseEntity<>(profileNotFound, HttpStatus.NOT_FOUND);
    }
}
