package org.example.mapper;

import org.example.dto.Airline;
import org.example.dto.Coordinate;
import org.example.dto.Destination;
import org.example.dto.mappers.AirlinePropertyMap;
import org.example.entity.AirlineEntity;
import org.example.entity.DestinationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ofir Mamo
 */
class MapperTests {

    private ModelMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ModelMapper();
        this.mapper.addMappings(new AirlinePropertyMap());
    }

    @Test
    void destinationToDestinationEntityTest() {
        Destination destination = new Destination("Ben Gurion Airport", new Coordinate(34.88542, 32.00569));
        DestinationEntity entity = this.mapper.map(destination, DestinationEntity.class);

        assertEquals(destination.getName(), entity.getName());
        assertEquals(destination.getLocation().getAltitude(), entity.getLocation().getAltitude());
        assertEquals(destination.getLocation().getLongitude(), entity.getLocation().getLongitude());
    }

    @Test
    void airlineToAirlineEntityTest() {
        String name = "Ben Gurion Airport";
        Airline airline = new Airline(name, 1000, new Coordinate(34.88542, 32.00569));

        AirlineEntity entity = this.mapper.map(airline, AirlineEntity.class);

        assertEquals(airline.getName(), entity.getName());
        assertEquals(airline.getBudget(), entity.getBudget());
        assertEquals(airline.getHomeBaseLocation().getAltitude(), entity.getHomeBaseLocation().getLocation().getAltitude());
        assertEquals(airline.getHomeBaseLocation().getLongitude(), entity.getHomeBaseLocation().getLocation().getLongitude());
        assertEquals(airline.getName(), entity.getHomeBaseLocation().getName());
    }
}
