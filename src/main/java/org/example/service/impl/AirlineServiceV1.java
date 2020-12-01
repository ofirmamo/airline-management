package org.example.service.impl;

import org.apache.lucene.util.SloppyMath;
import org.example.Utils;
import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.dto.Destination;
import org.example.dto.Distance;
import org.example.dto.mappers.AirlinePropertyMap;
import org.example.entity.AircraftEntity;
import org.example.entity.AirlineEntity;
import org.example.entity.mapper.AirlineDetailsPropertyMap;
import org.example.exception.AircraftNotFoundException;
import org.example.exception.AirlineExistException;
import org.example.exception.AirlineNotExistException;
import org.example.repository.AircraftRepository;
import org.example.repository.AirlineRepository;
import org.example.service.AirlineService;
import org.example.service.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Ofir Mamo
 */
@Service
@Transactional(readOnly = true)
public class AirlineServiceV1 implements AirlineService {

    private final ModelMapper mapper;
    private final DestinationService destinationService;
    private final AirlineRepository repository;
    private final AircraftRepository aircraftRepository;

    @Autowired
    public AirlineServiceV1(AirlineRepository repository, DestinationService destinationService, AircraftRepository aircraftRepository) {
        this.repository = repository;
        this.destinationService = destinationService;
        this.aircraftRepository = aircraftRepository;
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AirlinePropertyMap());
        this.mapper.addMappings(new AirlineDetailsPropertyMap());
    }

    @Override
    @Transactional
    public AirlineEntity addAirline(Airline airline) {
        if(this.repository.findByName(airline.getName()) != null) {
            throw new AirlineExistException("Airline already exist: " + airline.getName());
        }

        AirlineEntity entity = this.mapper.map(airline, AirlineEntity.class);
        return this.repository.save(entity);
    }

    @Override
    public List<AirlineDetails> retrieveAirlines() {
        // No parallel streaming.. mapping all entities to airline details.
       return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(airlineEntity -> this.mapper.map(airlineEntity, AirlineDetails.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Distance> distanceFromAllDestinations(String airlineName) {
        AirlineEntity airlineEntity = this.repository.findByName(airlineName);
        if(airlineEntity == null) {
            throw new AirlineNotExistException(airlineName);
        }

        return this.destinationService.getAllDestinations().stream()
                .filter(destination -> !destination.getName().equals(airlineName))
                .map(destination -> new Distance(destination.getName(), getDistanceFromAirline(destination, airlineEntity)))
                .collect(Collectors.toList());
    }

    @Override
    public AirlineEntity getAirline(String name) {
        return this.repository.findByName(name);
    }

    @Override
    @Transactional
    public void sellAircraft(String airlineName, long aircraftId) {
        // get entities.
        AircraftEntity aircraft = this.aircraftRepository.findById(aircraftId).
                filter(aircraftEntity -> aircraftEntity.getAirline().getName().equals(airlineName))
                .orElseThrow(() -> new AircraftNotFoundException(aircraftId));
        AirlineEntity airline = aircraft.getAirline();

        // get airline new price, and add it to airline budget.
        double aircraftPrice = Utils.airlinePrice(aircraft.getPrice(), aircraft.getPurchased().toLocalDateTime().toLocalDate(), LocalDate.now());
        airline.setBudget(airline.getBudget() + aircraftPrice);

        // delete and update airline & aircraft.
        this.repository.save(airline);
        this.aircraftRepository.deleteById(aircraftId);
    }

    private double getDistanceFromAirline(Destination destination, AirlineEntity airlineEntity) {
        double airlineAlt = airlineEntity.getHomeBaseLocation().getLocation().getAltitude();
        double airlineLong = airlineEntity.getHomeBaseLocation().getLocation().getLongitude();

        double destinationAlt = destination.getLocation().getAltitude();
        double destinationLong = destination.getLocation().getLongitude();

        return SloppyMath.haversinMeters(destinationAlt, destinationLong, airlineAlt, airlineLong);
    }
}
