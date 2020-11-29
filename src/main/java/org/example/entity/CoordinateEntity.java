package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ofir Mamo
 */
@Table(name = "coordinates")
@IdClass(CoordinateEntity.class)
@Entity
@Data
public class CoordinateEntity implements Serializable {

    @Id private double longitude;
    @Id private double altitude;

}
