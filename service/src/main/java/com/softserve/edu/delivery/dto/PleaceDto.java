package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.service.LocationService;
import com.softserve.edu.delivery.service.TransporterService;

/**
 * Created by Natalia on 02.10.2016.
 */
public class PleaceDto {
    private Long id;
    //private Order order;
    private CityDto city;
    private String date;

    public PleaceDto(){}

    public PleaceDto(CityDto city, String date/*, Order order*/) {
        this.city = city;
        this.date = date;
        //this.order = order;
    }

   /* public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityDto getCity() {
        return city;
    }

    public void setCity(CityDto city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   public static PleaceDto convertEntity(RouteCities routeCities){
        return new PleaceDto(CityDto.convertEntity(routeCities.getCity()), routeCities.getVisitDate().toString());
    }

    @Override
    public String toString() {
        return "PleaceDto{" +
                "id=" + id +
                ", city=" + city.toString()+
                ", date=" + date +
                '}';
    }
}
