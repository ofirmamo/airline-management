package org.example.exception;

/**
 * @author Ofir Mamo
 */
public class AirlineExistException extends RuntimeException {

    public AirlineExistException(String name) {
        super("Airline entity already exist, name: " + name);
    }
}
