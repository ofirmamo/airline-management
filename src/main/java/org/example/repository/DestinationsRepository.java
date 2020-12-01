package org.example.repository;

import org.example.entity.DestinationEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ofir Mamo
 */
public interface DestinationsRepository extends CrudRepository<DestinationEntity, Long> {

    /**
     * Finds destination by it's name
     * @param name name of the destination
     * @return destination entity.
     */
    DestinationEntity findByName(String name);
}
