package org.example.repository;

import org.example.entity.AirlineEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Ofir Mamo
 */
public interface AirlineRepository extends CrudRepository<AirlineEntity, Long> {

    /**
     * Finds airline by it's name
     * @param name name of the airline
     * @return airline entity saved in server
     */
    AirlineEntity findByName(String name);
}
