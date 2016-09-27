package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;

import java.math.BigDecimal;
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

    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    private BigDecimal weight;

    private User customer;

    private User transporter;

    private OrderStatus orderStatus;

    public RouteDTO(City lastLocation,
                    Timestamp expectedArrivalTime,
                    Timestamp lastTimeVisited,
                    List<City> visitedLocations,
                    BigDecimal height,
                    BigDecimal width,
                    BigDecimal length,
                    BigDecimal weight,
                    User costumer,
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
        this.customer = costumer;
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

    public RouteDTO setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public RouteDTO setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public RouteDTO setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public RouteDTO setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public RouteDTO setCustomer(User customer) {
        this.customer = customer;
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

    public BigDecimal getHeight() {
        return height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public User getCustomer() {
        return customer;
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
                ", customer=" + customer +
                ", transporter=" + transporter +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
