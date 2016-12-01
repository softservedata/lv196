package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Point;

import java.sql.Timestamp;

public class OrderClosedDto {
    private Long id;
    private Timestamp arrivalDate;
    private Point from;
    private Point to;
    private String driverName;
    private String customerName;
    private String description;

    public OrderClosedDto() {
    }

    public OrderClosedDto(Long id, Timestamp arrivalDate, Point from, Point to,String driverName,String customerName, String description) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.from = from;
        this.to = to;
        this.customerName = customerName;
        this.description = description;
        this.driverName = driverName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "OrderClosedDto{" +
                "id=" + id +
                ", arrivalDate=" + arrivalDate +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
    public static OrderClosedDto convertEntity(OrderDto order){
        System.out.println(order.toString());
        return new OrderClosedDto(order.getId(), order.getArrivalDate(),
                new Point(order.getLocationFrom().getLatitude(), order.getLocationFrom().getLongitude()),
                new Point(order.getLocationTo().getLatitude(), order.getLocationTo().getLongitude()),
                order.getDriverName(), order.getCustomerName(), order.getDescription()

        );
    }
}
