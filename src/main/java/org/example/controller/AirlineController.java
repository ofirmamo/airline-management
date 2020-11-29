package org.example.controller;

import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.entity.AirlineEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Ofir Mamo
 */
public interface AirlineController {

    /**
     * Adds new airline.
     * @param airline airline to add
     * @return airline as saved in server.
     */
    ResponseEntity<AirlineEntity> addAirline(Airline airline);

    ResponseEntity<List<AirlineDetails>> retrieveAirlines();
}
