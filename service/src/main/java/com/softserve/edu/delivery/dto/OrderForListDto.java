package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class OrderForListDto {
    private Long id;
    private String status;
    private Timestamp arrivalDate;
    private String description;
    private String customerName;
    private String cityNameFrom;
    private String cityNameTo;
    private String orderStatus;
    private String driverName;
    private BigDecimal weight;
    private Long numberOfOffers;

    public OrderForListDto() {
    }

    public static OrderForListDto of(Order order) {
        OrderForListDto dto = new OrderForListDto()
                .setId(order.getId())
                .setStatus(order.getOrderStatus().getName())
                .setArrivalDate(order.getArrivalDate())
                .setDescription(order.getDescription())
                .setCityNameFrom(order.getCityFrom() == null ? null : order.getCityFrom().getCityName())
                .setCityNameTo(order.getCityTo() == null ? null : order.getCityTo().getCityName())
                .setWeight(order.getWeight())
                .setNumberOfOffers(0L);

        User customer = order.getCustomer();
        if (customer != null) {
            dto.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        }

        return dto;
    }

    public Long getId() {
        return id;
    }

    public OrderForListDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderForListDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public OrderForListDto setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderForListDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderForListDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public OrderForListDto setCityNameFrom(String cityNameFrom) {
        this.cityNameFrom = cityNameFrom;
        return this;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public OrderForListDto setCityNameTo(String cityNameTo) {
        this.cityNameTo = cityNameTo;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderForListDto setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OrderForListDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OrderForListDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public Long getNumberOfOffers() {
        return numberOfOffers;
    }

    public OrderForListDto setNumberOfOffers(Long numberOfOffers) {
        this.numberOfOffers = numberOfOffers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderForListDto orderForListDto = (OrderForListDto) o;
        return Objects.equals(id, orderForListDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrderForListDto{" +
                "id=" + id +
                ", arrivalDate=" + arrivalDate +
                ", description='" + description + '\'' +
                ", customerName='" + customerName + '\'' +
                ", cityNameFrom='" + cityNameFrom + '\'' +
                ", cityNameTo='" + cityNameTo + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
