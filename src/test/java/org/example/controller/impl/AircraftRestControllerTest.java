package org.example.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Aircraft;
import org.example.dto.Airline;
import org.example.dto.Coordinate;
import org.example.entity.AircraftEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AircraftRestControllerTest {

    private static final String END_POINT = "/api/aircraft";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void addAircraftTest() throws Exception {

        Airline airline = new Airline("My airline 1", 1000, new Coordinate(10, 20));
        this.mvc.perform(
                post("/api/airline").contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline))
        ).andExpect(status().isOk()).andReturn();

        Aircraft aircraft = new Aircraft("My airline 1", 500, 500);
        MvcResult result = this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(aircraft))
        ).andExpect(status().isOk()).andReturn();

        AircraftEntity entity = this.mapper.readValue(result.getResponse().getContentAsString(), AircraftEntity.class);
        assertNotNull(entity.getAirline().getHomeBaseLocation());
        assertEquals(entity.getAirline().getName(), aircraft.getAirlineName());
        assertEquals(entity.getMaxKilometers(), aircraft.getMaxKilometers());
    }

    @Test
    void notExistingAirline() throws Exception {
        Aircraft aircraft = new Aircraft("Dummy", 500, 500);
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(aircraft))
        ).andExpect(status().isNotFound());
    }

    @Test
    void invalidData() throws Exception {
        Aircraft aircraft = new Aircraft("", 500, 500);
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(aircraft))
        ).andExpect(status().isBadRequest());

        aircraft.setAirlineName("Dummy");
        aircraft.setMaxKilometers(-1);
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(aircraft))
        ).andExpect(status().isBadRequest());

        aircraft.setMaxKilometers(10);
        aircraft.setPrice(-1);
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(aircraft))
        ).andExpect(status().isBadRequest());
    }

}