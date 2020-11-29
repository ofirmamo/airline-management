package org.example.service.impl;

import org.example.dto.Airline;
import org.example.dto.Coordinate;
import org.example.entity.AirlineEntity;
import org.example.exception.AirlineExistException;
import org.example.service.AirlineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AirlineServiceV1Test {


    @Autowired
    private AirlineService service;

    @Test
    void addAirline() {
        Airline airline = new Airline("Ben Gurion Airline", 1000, new Coordinate(20.5, 30.5));

        AirlineEntity entity = this.service.addAirline(airline);
        assertEquals(airline.getName(), entity.getName());
        assertEquals(airline.getBudget(), entity.getBudget());
        assertEquals(airline.getName(), entity.getHomeBaseLocation().getName());
    }

    @Test
    void addTwiceEntity() {
        Airline airline = new Airline("Ben Gurion Airline2", 1000, new Coordinate(21.5, 31.5));

        AirlineEntity entity = this.service.addAirline(airline);
        assertEquals(airline.getName(), entity.getName());
        assertEquals(airline.getBudget(), entity.getBudget());
        assertEquals(airline.getName(), entity.getHomeBaseLocation().getName());

        try {
            this.service.addAirline(airline);
            fail("Exception should be thrown here, duplicate entry");
        }catch (AirlineExistException e) {
            assert true;
        }
    }
}