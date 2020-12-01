package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * @author Ofir Mamo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {

    @NotEmpty(message = "Airline must be a valid name")
    private String airlineName;
    @Min(value = 0, message = "Price of aircraft greater or equal to zero")
    private double price;
    @Min(value = 0, message = "Max kilometers for aircraft greater or equal to zero")
    private double maxKilometers;

    
}
