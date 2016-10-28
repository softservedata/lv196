package com.softserve.edu.delivery.dto;

import java.sql.Date;

public class RouteCityDTO {

    private final PlaceDto placeDTO;
    private final Date date;

    public PlaceDto getPlaceDTO() {
        return placeDTO;
    }

    public Date getDate() {
        return date;
    }

    public RouteCityDTO(PlaceDto placeDTO, Date date) {

        this.placeDTO = placeDTO;
        this.date = date;
    }
}
