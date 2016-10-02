package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;

import java.sql.Timestamp;

/**
 * Created by Natalia on 02.10.2016.
 */
public class PleaceDto {
/*    private Long id;*/
    private CityDto city;
    private Timestamp date;

    public PleaceDto(){}

    public PleaceDto(CityDto city, Timestamp date) {
        this.city = city;
        this.date = date;
    }

 /*   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PleaceDto{" +
             /*   "id=" + id +*/
                ", city=" + city.toString() +
                ", date=" + date +
                '}';
    }
}
