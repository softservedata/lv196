package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import java.sql.Timestamp;
import java.util.Objects;

public class OrderForListDto {
    private Long id;
    private Timestamp registrationDate;
    private String description;
    private String customerName;
    private String cityNameFrom;
    private String cityNameTo;
    private String orderStatus;
    private String driverName;

    public String getDriverName() {
        return driverName;
    }

    public OrderForListDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public OrderForListDto() {
    }

    public static OrderForListDto of(Order order) {
        OrderForListDto dto = new OrderForListDto()
                .setId(order.getId())
                .setRegistrationDate(order.getRegistrationDate())
                .setDescription(order.getDescription())
                .setCityNameFrom(order.getCityFrom() == null ? null : order.getCityFrom().getCityName())
                .setCityNameTo(order.getCityTo() == null ? null : order.getCityTo().getCityName());

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

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public OrderForListDto setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
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
}
