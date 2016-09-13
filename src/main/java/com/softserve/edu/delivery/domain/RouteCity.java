package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */
@Entity
@Table(name = "ROUTECITY")

public class RouteCity {

    public RouteCity() {
    }

    private Long route_city_id;
    private Offer route;
    private City city;
    private Boolean visited;
    private Timestamp visitDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_city_id")
    public Long getRoute_city_id() {
        return route_city_id;
    }

    public void setRoute_city_id(Long route_city_id) {
        this.route_city_id = route_city_id;
    }

    @ManyToOne
    @JoinColumn(name = "route_id")
    public Offer getRoute() {
        return route;
    }

    public void setRoute(Offer route) {
        this.route = route;
    }

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column (name = "visited")
    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    @Column(name = "visit_date")
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }
}
