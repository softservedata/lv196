package com.softserve.edu.delivery.domain;


import java.sql.Timestamp;

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

    private Timestamp visitDate;
    
    public RouteCities() {
    }
    
    public RouteCities(City city, Timestamp visitDate) {
        this.city = city;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((route == null) ? 0 : route.hashCode());
        result = prime * result + ((routeCityId == null) ? 0 : routeCityId.hashCode());
        result = prime * result + ((visitDate == null) ? 0 : visitDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RouteCities other = (RouteCities) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (route == null) {
            if (other.route != null)
                return false;
        } else if (!route.equals(other.route))
            return false;
        if (routeCityId == null) {
            if (other.routeCityId != null)
                return false;
        } else if (!routeCityId.equals(other.routeCityId))
            return false;
        if (visitDate == null) {
            if (other.visitDate != null)
                return false;
        } else if (!visitDate.equals(other.visitDate))
            return false;
        return true;
    }
    
}
