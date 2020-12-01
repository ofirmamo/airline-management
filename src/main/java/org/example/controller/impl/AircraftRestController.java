package org.example.controller.impl;

import org.example.controller.AircraftController;
import org.example.dto.Aircraft;
import org.example.entity.AircraftEntity;
import org.example.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Ofir Mamo
 */
@RestController
@RequestMapping("/api/aircraft")
public class AircraftRestController implements AircraftController {

    private final AircraftService service;

    @Autowired
    public AircraftRestController(AircraftService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<AircraftEntity> addAircraft(@Valid @RequestBody Aircraft aircraft) {
        return ResponseEntity.ok(this.service.addAircraft(aircraft));
    }
}
