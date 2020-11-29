package org.example.entity.mapper;

import org.example.dto.AirlineDetails;
import org.example.entity.AirlineEntity;
import org.modelmapper.PropertyMap;

/**
 * @author Ofir Mamo
 * created at 29/11/2020
 */
public class AirlineDetailsPropertyMap extends PropertyMap<AirlineEntity, AirlineDetails> {
    @Override
    protected void configure() {
        this.map().setBalance(source.getBudget());
    }
}
