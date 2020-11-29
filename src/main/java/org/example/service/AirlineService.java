package org.example.service;

import org.example.dto.Airline;
import org.example.entity.AirlineEntity;

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

}
