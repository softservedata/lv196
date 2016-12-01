package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RoutesDto {

    private Point from;
    private Point to;
    private PlaceDto currentLocation;
    private String driverName;
    private String customerName;
    private String description;
    private Timestamp arrivalDate;

    public RoutesDto(Point from, Point to, PlaceDto currentLocation, String driverName, String customerName, String description, Timestamp arrivalDate) {
        this.from = from;
        this.to = to;
        this.currentLocation = currentLocation;
        this.driverName = driverName;
        this.customerName = customerName;
        this.description = description;
        this.arrivalDate = arrivalDate;
    }
    public RoutesDto() {

    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Point getIdFrom() {
        return from;
    }

    public void setIdFrom(Point idFrom) {
        this.from = idFrom;
    }

    public Point getIdTo() {
        return to;
    }

    public void setIdTo(Point idTo) {
        this.to = idTo;
    }

    public PlaceDto getPoints() {
        return currentLocation;
    }

    public void setPoints(PlaceDto currentLocation) {
        this.currentLocation = currentLocation;
    }

    public static RoutesDto convertEntity(OrderDto order, RouteCities routeCities){
        return new RoutesDto(
                new Point(order.getLocationFrom().getLatitude(), order.getLocationFrom().getLongitude()),
                new Point(order.getLocationTo().getLatitude(), order.getLocationTo().getLongitude()),
                new PlaceDto(new Point(routeCities.getX(), routeCities.getY()),
                        new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(routeCities.getVisitDate()), order.getId()),
                order.getDriverName(), order.getCustomerName(), order.getDescription(), order.getArrivalDate());
    }

    @Override
    public String toString() {
        return "RoutesDto{" +
                "idFrom=" + from +
                ", idTo=" + to +
                ", points=" + currentLocation +
                '}';
    }
}
