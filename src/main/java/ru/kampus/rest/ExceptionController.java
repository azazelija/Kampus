package ru.kampus.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiError> handleConflict(Exception ex) {
        ApiError apiError = new ApiError(ex);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
