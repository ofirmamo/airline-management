package org.example.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Ofir Mamo
 */
@Entity
@Table(name = "destinations")
@Data
public class DestinationEntity {

    @Id
    private int id;

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private CoordinateEntity location;
}
