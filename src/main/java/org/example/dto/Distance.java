package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ofir Mamo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distance {

    private String destination;
    private double distanceInMeters;
}
