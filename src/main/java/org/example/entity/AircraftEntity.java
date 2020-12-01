package org.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp purchased;

    @OneToOne
    private AirlineEntity airline;
}
