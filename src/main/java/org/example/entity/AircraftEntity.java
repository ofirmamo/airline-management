package org.example.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Ofir Mamo
 */
@Entity
@Table(name = "aircrafts")
@Data
public class AircraftEntity {

    @Id @GeneratedValue
    private long id;

    private double price;

    private double maxKilometers;

    @CreationTimestamp
    private Timestamp purchased;

    @OneToOne
    private AirlineEntity airline;
}
