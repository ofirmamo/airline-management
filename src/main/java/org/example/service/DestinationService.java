package org.example.service;

import org.example.dto.Destination;
import org.example.entity.DestinationEntity;

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
}
