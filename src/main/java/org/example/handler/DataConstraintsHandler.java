package org.example.handler;

import org.example.exception.LocationExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ofir Mamo
 * created at 29/11/2020
 */
@ControllerAdvice
public class DataConstraintsHandler {

    @ExceptionHandler(LocationExistException.class)
    ResponseEntity<RestRequestError> handle(LocationExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestRequestError(HttpStatus.CONFLICT, e.getMessage()));
    }
}
