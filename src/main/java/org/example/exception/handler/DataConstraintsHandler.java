package org.example.exception.handler;

import org.example.exception.AirlineExistException;
import org.example.exception.ForbiddenRequestException;
import org.example.exception.LocationExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ofir Mamo
 */
@ControllerAdvice
public class DataConstraintsHandler {

    private final Logger logger = LoggerFactory.getLogger(DataConstraintsHandler.class);

    @ExceptionHandler(LocationExistException.class)
    public ResponseEntity<RestRequestError> handle(LocationExistException e) {
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(new RestRequestError(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(AirlineExistException.class)
    public ResponseEntity<RestRequestError> handle(AirlineExistException e) {
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).
                body(new RestRequestError(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(ForbiddenRequestException.class)
    public ResponseEntity<RestRequestError> handle(ForbiddenRequestException e) {
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new RestRequestError(HttpStatus.FORBIDDEN, e.getMessage()));
    }
}
