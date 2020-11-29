package org.example.exception;

/**
 * @author Ofir Mamo
 */
public class AirlineNotExistException extends RuntimeException{

    public AirlineNotExistException(String name) {
        super("Requested airline does not exist name: " + name);
    }
}
