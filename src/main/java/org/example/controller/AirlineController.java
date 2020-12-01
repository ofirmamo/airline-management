package org.example.controller;

import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.dto.Distance;
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

    /**
     * Retrieve all available airlines
     * @return all airlines in the system.
     */
    ResponseEntity<List<AirlineDetails>> retrieveAirlines();

    /**
     * Retrieve all distances from a given airline
     * @param airlineName airline name.
     * @return list of name of destination and distance from airline.
     */
    ResponseEntity<List<Distance>> getAllDistancesFromDestinations(String airlineName);

    /**
     * Sells an aircraft of air line company.
     * @param airlineName air line company name.
     * @param aircraftId aircraft id.
     */
    void sellAircraft(String airlineName, long aircraftId);
}
