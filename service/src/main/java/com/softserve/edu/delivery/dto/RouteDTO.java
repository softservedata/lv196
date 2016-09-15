package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Petro Shtenovych
 * */

// Tracking information
public class RouteDTO {

    private City lastLocation;

    private Timestamp expectedArrivalTime;

    private Timestamp lastTimeVisited;

    private List<City> visitedLocations;

    private Double height;

    private Double width;

    private Double length;

    private Double weight;

    private User owner;

    private User transporter;

    private OrderStatus orderStatus;

    public RouteDTO(City lastLocation,
                    Timestamp expectedArrivalTime,
                    Timestamp lastTimeVisited,
                    List<City> visitedLocations,
                    Double height,
                    Double width,
                    Double length,
                    Double weight,
                    User owner,
                    User transporter,
                    OrderStatus orderStatus) {
        this.lastLocation = lastLocation;
        this.expectedArrivalTime = expectedArrivalTime;
        this.lastTimeVisited = lastTimeVisited;
        this.visitedLocations = visitedLocations;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
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

    public RouteDTO setExpectedArrivalTime(Timestamp expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
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

    public RouteDTO setHeight(Double height) {
        this.height = height;
        return this;
    }

    public RouteDTO setWidth(Double width) {
        this.width = width;
        return this;
    }

    public RouteDTO setLength(Double length) {
        this.length = length;
        return this;
    }

    public RouteDTO setWeight(Double weight) {
        this.weight = weight;
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

    public Timestamp getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public Timestamp getLastTimeVisited() {
        return lastTimeVisited;
    }

    public List<City> getVisitedLocations() {
        return visitedLocations;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    public Double getWeight() {
        return weight;
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

    @Override
    public String toString() {
        return "RouteDTO{" +
                "lastLocation=" + lastLocation +
                ", expectedArrivalTime=" + expectedArrivalTime +
                ", lastTimeVisited=" + lastTimeVisited +
                ", visitedLocations=" + visitedLocations +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", weight=" + weight +
                ", owner=" + owner +
                ", transporter=" + transporter +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
