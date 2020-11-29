package org.example.repository;

import org.example.entity.AirlineEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ofir Mamo
 */
public interface AirlineRepository extends CrudRepository<AirlineEntity, Long> {
}
