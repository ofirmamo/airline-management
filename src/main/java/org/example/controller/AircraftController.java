package org.example.controller;

import org.example.dto.Aircraft;
import org.example.entity.AircraftEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author Ofir Mamo
 */
public interface AircraftController {

    /**
     * Adds new aircraft to the system.
     * @param aircraft aircrat details (name of airline, km and price).
     * @return Airline as saved in the Database.
     */
    ResponseEntity<AircraftEntity> addAircraft(Aircraft aircraft);
}
