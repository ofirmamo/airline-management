package org.example.exception;

/**
 * @author Ofir Mamo
 */
public class ForbiddenRequestException extends RuntimeException {

    public ForbiddenRequestException(String message) {
        super(message);
    }
}
