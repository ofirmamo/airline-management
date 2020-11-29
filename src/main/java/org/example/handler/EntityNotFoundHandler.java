package org.example.handler;

import org.example.exception.AirlineNotExistException;
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
public class EntityNotFoundHandler {

    private final Logger logger = LoggerFactory.getLogger(EntityNotFoundHandler.class);

    @ExceptionHandler(AirlineNotExistException.class)
    public ResponseEntity<RestRequestError> handle(AirlineNotExistException e) {
        this.logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new RestRequestError(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}
