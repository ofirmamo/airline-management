package org.example.service;

import org.example.dto.Aircraft;
import org.example.entity.AircraftEntity;

/**
 * @author Ofir Mamo
 */
public interface AircraftService {

    /**
     * Adds aircraft to databases, airline name must be exist.
     * @param aircraft Aircraft to add.
     * @return Aircraft as saved as in the database.
     */
    AircraftEntity addAircraft(Aircraft aircraft);
}
