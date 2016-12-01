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
    private Order order;
    
    private Timestamp visitDate;
    private Double x;
    private Double y;
    
    public RouteCities() {
    }
    
    public RouteCities(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public Long getRouteCityId() {
        return routeCityId;
    }

    public Order getOrder() {
        return order;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setRouteCityId(Long routeCityId) {
        this.routeCityId = routeCityId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public RouteCities(Timestamp visitDate, Double latitude, Double longitude) {
        this.visitDate = visitDate;
        this.x = latitude;
        this.y = longitude;
    }
    public RouteCities(Long orderId, Point point) {
        this.order = new Order().setId(orderId);
        this.setX(point.getX());
        this.setY(point.getY());
    }

    public Double getX() {

        return x;
    }

    public void setX(Double latitude) {
        this.x = latitude;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double longitude) {
        this.y = longitude;
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

    @Override
    public String toString() {
        return "RouteCities{" +
                "routeCityId=" + routeCityId +
                ", order=" + order.toString() +
                ", visitDate=" + visitDate +
                ", latitude=" + x +
                ", longitude=" + y +
                '}';
    }
}
