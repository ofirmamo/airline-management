package org.example.service.impl;

import org.example.dto.Coordinate;
import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.example.service.DestinationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DestinationServiceV1Test {

    @Autowired
    private DestinationService service;

    @Test
    void addDestination() {
        Destination destination = new Destination("Ben Gurion Airport", new Coordinate(34.88542, 32.00569));
        DestinationEntity entity = this.service.addDestination(destination);

        assertEquals(destination.getName(), entity.getName());
        assertEquals(destination.getLocation().getLongitude(), entity.getLocation().getLongitude());
        assertEquals(destination.getLocation().getAltitude(), entity.getLocation().getAltitude());
    }
}