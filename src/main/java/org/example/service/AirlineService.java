package org.example.service;

import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.dto.Distance;
import org.example.entity.AirlineEntity;

import java.util.List;

/**
 * @author Ofir Mamo
 */
public interface AirlineService {

    /**
     * Adds new airline
     * @param airline airline DTO
     * @return Saved airline entity.
     */
    AirlineEntity addAirline(Airline airline);

    /**
     * Retrieve all airlines and their balance
     * @return list of all existing airlines and their balance.
     */
    List<AirlineDetails> retrieveAirlines();

    /**
     * List all distances from given airline
     * @param airlineName airline name to look for
     * @return List of all distances in meters from the airline.
     */
    List<Distance> distanceFromAllDestinations(String airlineName);

}
