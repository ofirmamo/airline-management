package org.example.controller.impl;

import org.example.controller.AirlineController;
import org.example.dto.Airline;
import org.example.dto.AirlineDetails;
import org.example.dto.Distance;
import org.example.entity.AirlineEntity;
import org.example.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

    @Override
    @GetMapping("distances")
    public ResponseEntity<List<Distance>> getAllDistancesFromDestinations(@NotEmpty @RequestParam String airlineName) {
        return ResponseEntity.ok(this.service.distanceFromAllDestinations(airlineName));
    }

    @Override
    @PostMapping("/sell/{airlineName}/{aircraftId}")
    @ResponseStatus(HttpStatus.OK)
    public void sellAircraft(@PathVariable @Valid String airlineName, @PathVariable @Valid long aircraftId) {
        this.service.sellAircraft(airlineName, aircraftId);
    }

    @Override
    @PostMapping("buy/{buying}")
    @ResponseStatus(HttpStatus.OK)
    public void buyAircraft(@PathVariable @NotEmpty String buying, @RequestParam @NotEmpty String selling, @RequestParam long aircraftId) {
        this.service.buyAircraft(buying, selling, aircraftId);
    }

    @Override
    @GetMapping("/available-destinations")
    public ResponseEntity<List<Distance>> availableDestinations(@NotEmpty @RequestParam String airlineName) {
        return ResponseEntity.ok(this.service.availableDestinations(airlineName));
    }
}
