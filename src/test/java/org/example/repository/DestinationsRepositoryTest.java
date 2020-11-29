package org.example.repository;

import org.example.entity.CoordinateEntity;
import org.example.entity.DestinationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DestinationsRepositoryTest {

    @Autowired
    private DestinationsRepository repository;

    @Test
    void addDestinationTest() {
        CoordinateEntity coordinate = new CoordinateEntity();
        coordinate.setAltitude(32.00569);
        coordinate.setLongitude(34.88542);

        DestinationEntity destination = new DestinationEntity();
        destination.setLocation(coordinate);
        destination.setName("Ben Gurion Airport");

        DestinationEntity result = this.repository.save(destination);
        assertEquals(result.getName(), destination.getName());
    }
}