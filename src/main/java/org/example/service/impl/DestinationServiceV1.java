package org.example.service.impl;

import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.example.exception.LocationExistException;
import org.example.repository.DestinationsRepository;
import org.example.service.DestinationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Ofir Mamo
 */
@Service
@Transactional(readOnly = true)
public class DestinationServiceV1 implements DestinationService {

    private final DestinationsRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public DestinationServiceV1(DestinationsRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public DestinationEntity addDestination(Destination destination) {
        if(this.repository.findByName(destination.getName()) != null) {
            throw new LocationExistException(destination.getName());
        }

        DestinationEntity destinationEntity = this.mapper.map(destination, DestinationEntity.class);
        return this.repository.save(destinationEntity);
    }

    @Override
    public List<Destination> getAllDestinations() {
        // Only reading no locking is needed..
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(destinationEntity -> this.mapper.map(destinationEntity, Destination.class))
                .collect(Collectors.toList());
    }
}
