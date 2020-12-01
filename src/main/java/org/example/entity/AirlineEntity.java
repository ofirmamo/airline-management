package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Ofir Mamo
 */
@Entity
@Table(name = "airlines")
@Data
@NoArgsConstructor
public class AirlineEntity {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    private double budget;

    @OneToOne(cascade = CascadeType.ALL)
    private DestinationEntity homeBaseLocation;

}
