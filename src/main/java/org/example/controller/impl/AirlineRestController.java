package org.example.controller.impl;

import org.example.controller.AirlineController;
import org.example.dto.Airline;
import org.example.entity.AirlineEntity;
import org.example.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ofir Mamo
 * created at 29/11/2020
 */
@RestController
@RequestMapping("/api/airline")
public class AirlineRestController implements AirlineController {

    private final AirlineService service;

    @Autowired
    public AirlineRestController(AirlineService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<AirlineEntity> addAirline(@RequestBody @Valid Airline airline) {
        return ResponseEntity.ok(this.service.addAirline(airline));
    }
}
