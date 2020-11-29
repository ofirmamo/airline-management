package org.example.mapper;

import org.example.dto.Coordinate;
import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ofir Mamo
 */
class MapperTests {

    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ModelMapper();
    }

    @Test
    void destinationToDestinationEntityTest() {
        Destination destination = new Destination("Ben Gurion Airport", new Coordinate(34.88542, 32.00569));
        DestinationEntity entity = this.mapper.map(destination, DestinationEntity.class);

        assertEquals(destination.getName(), entity.getName());
        assertEquals(destination.getLocation().getAltitude(), entity.getLocation().getAltitude());
        assertEquals(destination.getLocation().getLongitude(), entity.getLocation().getLongitude());
    }
}
