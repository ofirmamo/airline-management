package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ofir Mamo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirlineDetails {

    private String name;

    private double balance;
}
