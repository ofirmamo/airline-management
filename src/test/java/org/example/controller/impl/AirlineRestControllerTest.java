package org.example.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Airline;
import org.example.dto.Coordinate;
import org.example.entity.AirlineEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AirlineRestControllerTest {

    private static final String END_POINT = "/api/airline";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void sanity() throws Exception {
        Airline airline = new Airline("El Al Airline", 1200, new Coordinate(20.3, 50.2));

        MvcResult result = this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline))
        ).andExpect(status().isOk()).andReturn();

        AirlineEntity entity = this.mapper.readValue(result.getResponse().getContentAsString(), AirlineEntity.class);
        assertEquals(airline.getName(), entity.getName());
        assertEquals(airline.getBudget(), entity.getBudget());
        assertEquals(airline.getName(), entity.getHomeBaseLocation().getName());
        assertEquals(airline.getHomeBaseLocation().getLongitude(), entity.getHomeBaseLocation().getLocation().getLongitude());
        assertEquals(airline.getHomeBaseLocation().getAltitude(), entity.getHomeBaseLocation().getLocation().getAltitude());
    }

    @Test
    void addExistEntityTest() throws Exception {
        Airline airline = new Airline("Israel Air Airline", 1200, new Coordinate(20.7, 50.9));

        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline))
        ).andExpect(status().isOk()).andReturn();

        airline.getHomeBaseLocation().setAltitude(30);
        // expecting CONFLICT
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline))
        ).andExpect(status().isConflict()).andReturn();
    }

    @Test
    void invalidCoordinate() throws Exception {
        Airline airline1 = new Airline("invalid1", 1200, new Coordinate(1000, 50.9));
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline1))
        ).andExpect(status().isBadRequest()).andReturn();

        Airline airline2 = new Airline("invalid2", 1200, new Coordinate(35, 1000));
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline2))
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void invalidBudget() throws Exception {
        Airline airline1 = new Airline("invalid3", -10, new Coordinate(10, 50.9));
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline1))
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void invalidName() throws Exception {
        Airline airline1 = new Airline("", 30, new Coordinate(10, 50.9));
        this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(airline1))
        ).andExpect(status().isBadRequest()).andReturn();
    }
}