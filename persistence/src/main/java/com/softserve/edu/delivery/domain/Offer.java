package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private boolean approved;

    public Long getOfferId() {
        return offerId;
    }

    public Offer setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Offer setCar(Car car) {
        this.car = car;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Offer setOrder(Order order) {
        this.order = order;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public Offer setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(offerId, offer.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
