package org.example.service.impl;

import org.apache.lucene.util.SloppyMath;
import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.dto.Destination;
import org.example.dto.Distance;
import org.example.dto.mappers.AirlinePropertyMap;
import org.example.entity.AirlineEntity;
import org.example.entity.mapper.AirlineDetailsPropertyMap;
import org.example.exception.AirlineExistException;
import org.example.exception.AirlineNotExistException;
import org.example.repository.AirlineRepository;
import org.example.service.AirlineService;
import org.example.service.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Ofir Mamo
 */
@Service
public class AirlineServiceV1 implements AirlineService {

    private final ModelMapper mapper;
    private final DestinationService destinationService;
    private final AirlineRepository repository;

    @Autowired
    public AirlineServiceV1(AirlineRepository repository, DestinationService destinationService) {
        this.repository = repository;
        this.destinationService = destinationService;
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AirlinePropertyMap());
        this.mapper.addMappings(new AirlineDetailsPropertyMap());
    }

    @Override
    public AirlineEntity addAirline(Airline airline) {
        AirlineEntity entity = this.mapper.map(airline, AirlineEntity.class);
        try {
            return this.repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new AirlineExistException(entity.getName());
        }
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
        // Read only no locking is needed ..
        AirlineEntity airlineEntity = this.repository.findByName(airlineName);
        if(airlineEntity == null) {
            throw new AirlineNotExistException(airlineName);
        }

        return this.destinationService.getAllDestinations().stream()
                .filter(destination -> !destination.getName().equals(airlineName))
                .map(destination -> new Distance(destination.getName(), getDistanceFromAirline(destination, airlineEntity)))
                .collect(Collectors.toList());
    }

    private double getDistanceFromAirline(Destination destination, AirlineEntity airlineEntity) {
        double airlineAlt = airlineEntity.getHomeBaseLocation().getLocation().getAltitude();
        double airlineLong = airlineEntity.getHomeBaseLocation().getLocation().getLongitude();

        double destinationAlt = destination.getLocation().getAltitude();
        double destinationLong = destination.getLocation().getLongitude();

        return SloppyMath.haversinMeters(destinationAlt, destinationLong, airlineAlt, airlineLong);
    }
}
