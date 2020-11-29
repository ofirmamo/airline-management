package org.example.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Coordinate;
import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
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
class DestinationRestControllerTest {

    private static final String END_POINT = "/api/destination";

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void addDestination() throws Exception {
        Destination destination = new Destination("Ben Gurion Airport", new Coordinate(34.88542, 32.00569));

        MvcResult result = this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(destination))
        ).andExpect(status().isOk()).andReturn();

        DestinationEntity entity = this.mapper.readValue(result.getResponse().getContentAsString(), DestinationEntity.class);
        assertEquals(destination.getName(), entity.getName());
    }

    @Test
    void nullCoordinate() throws Exception {
        Destination destination = new Destination("Ben Gurion Airport", null);

        MvcResult result = this.mvc.perform(
                post(END_POINT).contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(destination))
        ).andExpect(status().isBadRequest()).andReturn();
    }
}