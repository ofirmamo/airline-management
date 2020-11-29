package org.example.service.impl;

import org.example.dto.Airline;
import org.example.dto.mappers.AirlinePropertyMap;
import org.example.entity.AirlineEntity;
import org.example.exception.AirlineExistException;
import org.example.repository.AirlineRepository;
import org.example.service.AirlineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * @author Ofir Mamo
 */
@Service
public class AirlineServiceV1 implements AirlineService {

    private final ModelMapper mapper;
    private final AirlineRepository repository;

    @Autowired
    public AirlineServiceV1(AirlineRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AirlinePropertyMap());
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
}
