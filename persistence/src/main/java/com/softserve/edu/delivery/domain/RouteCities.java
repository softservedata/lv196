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
    
    private Timestamp visitDate;
    private Double latitude;
    private Double longitude;
    
    public RouteCities() {
    }
    
    public RouteCities(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public Long getRouteCityId() {
        return routeCityId;
    }

    public Order getRoute() {
        return route;
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

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public RouteCities(Timestamp visitDate, Double latitude, Double longitude) {
        this.visitDate = visitDate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {

        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
    
}
