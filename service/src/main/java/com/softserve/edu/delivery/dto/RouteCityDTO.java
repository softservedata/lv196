package com.softserve.edu.delivery.dto;

import java.sql.Date;

public class RouteCityDTO {

    private final PlaceDTO placeDTO;
    private final Date date;

    public PlaceDTO getPlaceDTO() {
        return placeDTO;
    }

    public Date getDate() {
        return date;
    }

    public RouteCityDTO(PlaceDTO placeDTO, Date date) {

        this.placeDTO = placeDTO;
        this.date = date;
    }
}
