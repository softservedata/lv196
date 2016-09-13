package com.softserve.edu.delivery.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author  Ivan Rudnytskyi, 11.09.2016.
 */
@Entity
@Table(name = "ROUTECITIES")

public class RouteCities {

    public RouteCities() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_city_id")
    private Long route_city_id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order route;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Column(name = "visit_date")
    private Timestamp visitDate;


    public Long getRoute_city_id() {
        return route_city_id;
    }

    public void setRoute_city_id(Long route_city_id) {
        this.route_city_id = route_city_id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Order getRoute() {
        return route;
    }

    public void setRoute(Order route) {
        this.route = route;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }
}
