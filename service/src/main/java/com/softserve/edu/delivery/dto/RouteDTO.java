package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Baggage;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Petro Shtenovych
 * */

public class RouteDTO {

    private City lastLocation;

    private Timestamp lastTimeVisited;

    private List<City> visitedLocations;

    private Baggage baggage;

    private User owner;

    private User transporter; //todo: in Order class(domain) add User as transporter

    private OrderStatus orderStatus;

    public RouteDTO(City lastLocation,
                    Timestamp lastTimeVisited,
                    List<City> visitedLocations,
                    Baggage baggage,
                    User owner,
                    User transporter,
                    OrderStatus orderStatus) {
        this.lastLocation = lastLocation;
        this.lastTimeVisited = lastTimeVisited;
        this.visitedLocations = visitedLocations;
        this.baggage = baggage;
        this.owner = owner;
        this.transporter = transporter;
        this.orderStatus = orderStatus;
    }

    public RouteDTO() {}

    /*<-------------Setters------------------>*/

    public RouteDTO setLastLocation(City lastLocation) {
        this.lastLocation = lastLocation;
        return this;
    }

    public RouteDTO setLastTimeVisited(Timestamp lastTimeVisited) {
        this.lastTimeVisited = lastTimeVisited;
        return this;
    }

    public RouteDTO setVisitedLocations(List<City> visitedLocations) {
        this.visitedLocations = visitedLocations;
        return this;
    }

    public RouteDTO setBaggage(Baggage baggage) {
        this.baggage = baggage;
        return this;
    }

    public RouteDTO setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public RouteDTO setTransporter(User transporter) {
        this.transporter = transporter;
        return this;
    }

    public RouteDTO setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

     /*<-------------Getters------------------>*/

    public City getLastLocation() {
        return lastLocation;
    }

    public Timestamp getLastTimeVisited() {
        return lastTimeVisited;
    }

    public List<City> getVisitedLocations() {
        return visitedLocations;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public User getOwner() {
        return owner;
    }

    public User getTransporter() {
        return transporter;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
