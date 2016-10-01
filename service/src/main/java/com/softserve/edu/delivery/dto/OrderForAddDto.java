package com.softserve.edu.delivery.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderForAddDto {

    private LocationDto locationFrom;
    private LocationDto locationTo;
    private Timestamp arrivalDate;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal weight;
    private String description;

    public OrderForAddDto() {}

    public LocationDto getLocationFrom() {
        return locationFrom;
    }

    public OrderForAddDto setLocationFrom(LocationDto locationFrom) {
        this.locationFrom = locationFrom;
        return this;
    }

    public LocationDto getLocationTo() {
        return locationTo;
    }

    public OrderForAddDto setLocationTo(LocationDto locationTo) {
        this.locationTo = locationTo;
        return this;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public OrderForAddDto setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public OrderForAddDto setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public OrderForAddDto setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public BigDecimal getLength() {
        return length;
    }

    public OrderForAddDto setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OrderForAddDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderForAddDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
