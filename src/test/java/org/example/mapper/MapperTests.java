package org.example.mapper;

import org.example.dto.*;
import org.example.dto.mappers.AirlinePropertyMap;
import org.example.entity.AircraftEntity;
import org.example.entity.AirlineEntity;
import org.example.entity.DestinationEntity;
import org.example.entity.mapper.AirlineDetailsPropertyMap;
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
        this.mapper.addMappings(new AirlinePropertyMap());
        this.mapper.addMappings(new AirlineDetailsPropertyMap());
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

    @Test
    void airlineDetailsMapper() {
        AirlineEntity entity = new AirlineEntity();
        entity.setId(0);
        entity.setBudget(10);
        entity.setName("Airline Testi KUKU");

        AirlineDetails details = this.mapper.map(entity, AirlineDetails.class);
        assertEquals(entity.getName(), details.getName());
        assertEquals(entity.getBudget(), details.getBalance());
    }

    @Test
    void aircraftToEntityTest() {
        Aircraft airCraft = new Aircraft("my air line", 1000, 10000);
        AircraftEntity entity = this.mapper.map(airCraft, AircraftEntity.class);

        assertEquals(airCraft.getPrice(), entity.getPrice());
        assertEquals(airCraft.getMaxKilometers(), entity.getMaxKilometers());
        assertNull(entity.getPurchased());
    }
}
