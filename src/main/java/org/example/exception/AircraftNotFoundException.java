package org.example.exception;

/**
 * @author Ofir Mamo
 */
public class AircraftNotFoundException extends RuntimeException {

    public AircraftNotFoundException(long id) {
        super("Cannot find aircraft with ID: " + id);
    }
}
