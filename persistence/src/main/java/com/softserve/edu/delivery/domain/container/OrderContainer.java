package com.softserve.edu.delivery.domain.container;


import com.softserve.edu.delivery.domain.Order;

import java.util.Objects;

public class OrderContainer {
    private Order order;
    private Long offersAmount;
    private String driverName;
    private String carPhoto;

    public OrderContainer(Order order, Long offersAmount) {
        this.order = order;
        this.offersAmount = offersAmount;
    }


    public OrderContainer(Order order, String driverName) {
        this.order = order;
        this.driverName = driverName;
    }

    public OrderContainer(Order order, String driverName, String carPhoto) {
        this(order, driverName);
        this.carPhoto = carPhoto;
    }

    public Order getOrder() {
        return order;
    }

    public OrderContainer setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Long getOffersAmount() {
        return offersAmount;
    }

    public OrderContainer setOffersAmount(Long offersAmount) {
        this.offersAmount = offersAmount;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OrderContainer setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public OrderContainer setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderContainer that = (OrderContainer) o;
        return Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order);
    }
}
