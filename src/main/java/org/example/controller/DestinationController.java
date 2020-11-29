package org.example.controller;

import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author Ofir Mamo
 */
public interface DestinationController {

    /**
     * Adds new destination.
     * @param destination destination to add
     * @return he destination as saved at the server side.
     */
    ResponseEntity<DestinationEntity> addDestination(Destination destination);
}
