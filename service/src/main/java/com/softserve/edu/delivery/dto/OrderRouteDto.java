package com.softserve.edu.delivery.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Petro Shtenovych
 * */

public class OrderRouteDto {

    private String cityFrom;

    private String cityTo;

    private String lastLocation;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate expectedArrivalTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate lastTimeVisited;

    private BigDecimal height;

    private BigDecimal width;

    private BigDecimal length;

    private BigDecimal weight;

    private String customerName;

    private String transporterName;

    private String receiverName;

    private String orderStatus;

    public OrderRouteDto(String cityFrom,
                         String cityTo,
                         String lastLocation,
                         LocalDate expectedArrivalTime,
                         LocalDate lastTimeVisited,
                         BigDecimal height,
                         BigDecimal width,
                         BigDecimal length,
                         BigDecimal weight,
                         String customerName,
                         String transporterName,
                         String receiverName,
                         String orderStatus) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.lastLocation = lastLocation;
        this.expectedArrivalTime = expectedArrivalTime;
        this.lastTimeVisited = lastTimeVisited;
        this.height = height;
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.customerName = customerName;
        this.transporterName = transporterName;
        this.receiverName = receiverName;
        this.orderStatus = orderStatus;
    }

    public OrderRouteDto() {}

    public OrderRouteDto setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
        return this;
    }

    public OrderRouteDto setCityTo(String cityTo) {
        this.cityTo = cityTo;
        return this;
    }

    public OrderRouteDto setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
        return this;
    }

    public OrderRouteDto setExpectedArrivalTime(LocalDate expectedArrivalTime) {
        this.expectedArrivalTime = expectedArrivalTime;
        return this;
    }

    public OrderRouteDto setLastTimeVisited(LocalDate lastTimeVisited) {
        this.lastTimeVisited = lastTimeVisited;
        return this;
    }

    public OrderRouteDto setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public OrderRouteDto setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public OrderRouteDto setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public OrderRouteDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public OrderRouteDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public OrderRouteDto setTransporterName(String transporterName) {
        this.transporterName = transporterName;
        return this;
    }

    public OrderRouteDto setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public OrderRouteDto setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public LocalDate getExpectedArrivalTime() {
        return expectedArrivalTime;
    }

    public LocalDate getLastTimeVisited() {
        return lastTimeVisited;
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

    public String getCustomerName() {
        return customerName;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
