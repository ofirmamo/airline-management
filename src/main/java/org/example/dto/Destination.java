package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ofir Mamo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destination {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Location is mandatory")
    @Valid
    private Coordinate location;
}
