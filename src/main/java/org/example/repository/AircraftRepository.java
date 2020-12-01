package org.example.repository;

import org.example.entity.AircraftEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ofir Mamo
 */
public interface AircraftRepository extends CrudRepository<AircraftEntity, Long> { }
