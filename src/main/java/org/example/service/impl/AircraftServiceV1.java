package org.example.service.impl;

import org.example.dto.Aircraft;
import org.example.entity.AircraftEntity;
import org.example.entity.AirlineEntity;
import org.example.repository.AircraftRepository;
import org.example.service.AircraftService;
import org.example.service.AirlineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ofir Mamo
 * created at 01/12/2020
 */
@Service
@Transactional(readOnly = true)
public class AircraftServiceV1 implements AircraftService {

    private final AircraftRepository repository;
    private final AirlineService airlineService;
    private final ModelMapper mapper;

    @Autowired
    public AircraftServiceV1(AircraftRepository repository, AirlineService airlineService) {
        this.repository = repository;
        this.airlineService = airlineService;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public AircraftEntity addAircraft(Aircraft aircraft) {
        AirlineEntity airline = this.airlineService.getAirline(aircraft.getAirlineName());
        AircraftEntity entity = this.mapper.map(aircraft, AircraftEntity.class);

        entity.setAirline(airline);
        return this.repository.save(entity);
    }
}
