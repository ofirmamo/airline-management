package org.example.controller.impl;

import org.example.controller.AirlineController;
import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.entity.AirlineEntity;
import org.example.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @Override
    @GetMapping("/details")
    public ResponseEntity<List<AirlineDetails>> retrieveAirlines() {
        return ResponseEntity.ok(this.service.retrieveAirlines());
    }
}
