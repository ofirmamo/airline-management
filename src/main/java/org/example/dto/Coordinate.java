package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author Ofir Mamo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {

    @Range(min = -180, max = 180, message = "Longitude must be in range of [-180, 180]")
    private double longitude;

    @Range(min = -90, max = 90, message = "Altitude must be in range of [-90, 90]")
    private double altitude;
}
