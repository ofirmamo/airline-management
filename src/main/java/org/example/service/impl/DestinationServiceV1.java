package org.example.service.impl;

import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.example.exception.LocationExistException;
import org.example.repository.DestinationsRepository;
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
public class DestinationServiceV1 implements DestinationService {

    private final DestinationsRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public DestinationServiceV1(DestinationsRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    @Override
    public DestinationEntity addDestination(Destination destination) {
        DestinationEntity destinationEntity = this.mapper.map(destination, DestinationEntity.class);
        try {
            return this.repository.save(destinationEntity);
        } catch (DataIntegrityViolationException e) {
            throw new LocationExistException(destinationEntity.getName());
        }
    }

    @Override
    public List<Destination> getAllDestinations() {
        // Only reading no locking is needed..
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(destinationEntity -> this.mapper.map(destinationEntity, Destination.class))
                .collect(Collectors.toList());
    }
}
