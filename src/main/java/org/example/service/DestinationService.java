package org.example.service;

import org.example.dto.Destination;
import org.example.entity.DestinationEntity;

import java.util.List;

/**
 * @author Ofir Mamo
 */
public interface DestinationService {

    /**
     *  Adds new destination.
     * @param destination destination to add
     * @return The entity represent this destination at the server side.
     */
    DestinationEntity addDestination(Destination destination);

    /**
     * Retrieve all destination.
     * @return List of all destinations.
     */
    List<Destination> getAllDestinations();
}
