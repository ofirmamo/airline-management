package org.example.repository;

import org.example.entity.DestinationEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ofir Mamo
 */
public interface DestinationsRepository extends CrudRepository<DestinationEntity, Long> {
    DestinationEntity findByName(String name);
}
