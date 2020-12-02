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

    /**
     * Return airline by it's name
     * @param name name of the airline
     * @return airline as in the database.
     */
    AirlineEntity getAirline(String name);

    /**
     * Sells and aircraft based pn formula [price * (1 - monthsInUse * 0.02)]
     * @param aircraftId aircraft Id to sell
     */
    void sellAircraft(String airlineName, long aircraftId);

    /**
     * Option to buy aircraft from another airline.
     * @param buyingAirline buying air line
     * @param sellingAirline selling airline
     * @param aircraftId aircraft ID.
     */
    void buyAircraft(String buyingAirline, String sellingAirline, long aircraftId);

    /**
     * List all available destinations from a given airline.
     * @param airlineName airline name
     * @return List of all available destinations
     */
    List<Distance> availableDestinations(String airlineName);

}
