package com.softserve.edu.delivery.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "route_city")
public class RouteCities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeCityId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order route;

    private Double x;

    private Double y;

    private Timestamp visitDate;

    public RouteCities(){}
    public Long getRouteCityId() {
        return routeCityId;
    }
    public RouteCities(Double x,Double y, Timestamp visitDate) {
        this.x = x;
        this.y = y;
        this.visitDate = visitDate;
    }

    public RouteCities setRouteCityId(Long routeCityId) {
        this.routeCityId = routeCityId;
        return this;
    }

    public Order getRoute() {
        return route;
    }

    public RouteCities setRoute(Order route) {
        this.route = route;
        return this;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public RouteCities setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteCities that = (RouteCities) o;
        return Objects.equals(routeCityId, that.routeCityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeCityId);
    }
}
