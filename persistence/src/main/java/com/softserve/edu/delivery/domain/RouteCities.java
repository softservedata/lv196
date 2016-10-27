package com.softserve.edu.delivery.domain;


import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route_city")
public class RouteCities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeCityId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order route;
    
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Double latitude;
    private Double longitude;

    private Timestamp visitDate;
    
    public RouteCities() {
    }


    public RouteCities(Order route, City city, Double latitude, Double longitude, Timestamp visitDate) {
        this.route = route;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.visitDate = visitDate;
    }

    public Long getRouteCityId() {
        return routeCityId;
    }

    public Order getRoute() {
        return route;
    }

    public City getCity() {
        return city;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setRouteCityId(Long routeCityId) {
        this.routeCityId = routeCityId;
    }

    public void setRoute(Order route) {
        this.route = route;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeCityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteCities routeCities = (RouteCities) o;
        return Objects.equals(routeCityId, routeCities.routeCityId);
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
