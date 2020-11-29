package org.example.dto.mappers;

import org.example.dto.Airline;
import org.example.entity.AirlineEntity;
import org.modelmapper.PropertyMap;

/**
 * @author Ofir Mamo
 * Add custom map to airline DTOs, for example airline.name -> airlineEntity.homeBaseLocation.name.
 */
public class AirlinePropertyMap extends PropertyMap<Airline, AirlineEntity> {

    public AirlinePropertyMap() {
        super();
    }

    @Override
    protected void configure() {
        this.map().getHomeBaseLocation().setName(source.getName());
    }
}
