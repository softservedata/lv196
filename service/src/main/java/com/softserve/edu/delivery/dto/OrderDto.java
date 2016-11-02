package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.domain.container.OrderContainer;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class OrderDto {
    private Long id;
    private Timestamp arrivalDate;
    private String customerName;
    private LocationDto locationFrom;
    private LocationDto locationTo;
    private String driverName;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal length;
    private BigDecimal weight;
    private String description;
    private Long amountOfOffers;
    private String carPhoto;
    private FeedbackDTO feedbackDTO;


    public OrderDto() {
    }

    public static OrderDto ofContainer(OrderContainer container) {
        return of(container.getOrder())
                .setDriverName(container.getDriverName())
                .setAmountOfOffers(container.getOffersAmount())
                .setCarPhoto(container.getCarPhoto());
    }

    public static OrderDto of(Order order) {
        OrderDto dto = new OrderDto()
                .setId(order.getId())
                .setArrivalDate(order.getArrivalDate())
                .setLocationFrom(order.getCityFrom() == null ? null : LocationDto.of(order.getCityFrom()))
                .setLocationTo(order.getCityTo() == null ? null : LocationDto.of(order.getCityTo()))
                .setWeight(order.getWeight())
                .setHeight(order.getHeight())
                .setWidth(order.getWidth())
                .setLength(order.getLength())
                .setDescription(order.getDescription());

        User customer = order.getCustomer();
        if (customer != null) {
            dto.setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        }

        return dto;
    }

    public static OrderDto orderAndFeedbackOf(Order order, FeedbackDTO feedback) {
        OrderDto dto = OrderDto.of(order);
        dto.setFeedbackDTO(feedback);
        return dto;
    }

    public Long getId() {
        return id;
    }

    public OrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public OrderDto setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public LocationDto getLocationFrom() {
        return locationFrom;
    }

    public OrderDto setLocationFrom(LocationDto locationFrom) {
        this.locationFrom = locationFrom;
        return this;
    }

    public LocationDto getLocationTo() {
        return locationTo;
    }

    public OrderDto setLocationTo(LocationDto locationTo) {
        this.locationTo = locationTo;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OrderDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public OrderDto setWeight(BigDecimal weight) {
        this.weight = weight;
        return this;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public OrderDto setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public OrderDto setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public BigDecimal getLength() {
        return length;
    }

    public OrderDto setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public Long getAmountOfOffers() {
        return amountOfOffers;
    }

    public OrderDto setAmountOfOffers(Long amountOfOffers) {
        this.amountOfOffers = amountOfOffers;
        return this;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public OrderDto setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
        return this;
    }

    public FeedbackDTO getFeedbackDTO() {
        return feedbackDTO;
    }

    public void setFeedbackDTO(FeedbackDTO feedbackDTO) {
        this.feedbackDTO = feedbackDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id);
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
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
