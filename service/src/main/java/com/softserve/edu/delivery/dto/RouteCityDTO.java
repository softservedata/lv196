package com.softserve.edu.delivery.dto;

import java.sql.Date;

public class RouteCityDTO {

    private final LocationDto cityDto;
    private final Date date;
    
    public RouteCityDTO(LocationDto cityDto, Date date) {
        this.cityDto = cityDto;
        this.date = date;
    }

    public LocationDto getCityDto() {
        return cityDto;
    }

    public Date getDate() {
        return date;
    }
    
}
