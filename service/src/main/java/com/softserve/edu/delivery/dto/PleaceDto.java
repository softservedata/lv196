package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.RouteCities;

import java.sql.Timestamp;

/**
 * Created by Natalia on 02.10.2016.
 */
public class PleaceDto {
    private Long id;
    private City city;
    private String date;

    public PleaceDto(){}

    public PleaceDto(City city, String date) {
        this.city = city;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static PleaceDto convertEntity(RouteCities routeCities){
        return new PleaceDto(routeCities.getCity(), routeCities.getVisitDate().toString());
    }

    @Override
    public String toString() {
        return "PleaceDto{" +
                "id=" + id +
                ", city=" + city.toString() +
                ", date=" + date +
                '}';
    }
}
