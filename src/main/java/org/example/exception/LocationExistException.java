package org.example.exception;

/**
 * @author Ofir Mamo
 * created at 29/11/2020
 */
public class LocationExistException extends RuntimeException {

    public LocationExistException(String name) {
        super("Locatin already exist: " + name);
    }

}
