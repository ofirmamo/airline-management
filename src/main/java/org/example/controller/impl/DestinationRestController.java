package org.example.controller.impl;

import org.example.controller.DestinationController;
import org.example.dto.Destination;
import org.example.entity.DestinationEntity;
import org.example.service.DestinationService;
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
@RequestMapping("/api/destination")
public class DestinationRestController implements DestinationController {

    private final DestinationService service;

    @Autowired
    public DestinationRestController(DestinationService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<DestinationEntity> addDestination(@Valid @RequestBody Destination destination) {
        return ResponseEntity.ok(this.service.addDestination(destination));
    }
}
