package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Ofir Mamo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @NotEmpty(message = "Airline company name muse be not empty.")
    private String name;

    @Min(value = 0, message = "Budget always positive.")
    private double budget;

    @NotNull
    @Valid
    private Coordinate homeBaseLocation;

}
