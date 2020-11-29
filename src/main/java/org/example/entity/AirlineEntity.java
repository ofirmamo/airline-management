package org.example.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ofir Mamo
 */
@Entity
@Table(name = "airlines")
@Data
public class AirlineEntity {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String name;

    private double budget;

    @OneToOne(cascade = CascadeType.ALL)
    private DestinationEntity homeBaseLocation;

}
