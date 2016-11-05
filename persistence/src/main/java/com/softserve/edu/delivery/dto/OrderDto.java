package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.util.Objects;

public class OrderDto {
    private Long id;
    private Timestamp arrivalDate;
    private String customerName;
    private LocationDto locationFrom;
    private LocationDto locationTo;
    private String driverName;
    private Long approvedOfferId;
    private Double height;
    private Double width;
    private Double length;
    private Double weight;
    private String description;
    private Long amountOfOffers;
    private String carPhoto;
    private FeedbackDto feedbackDto;

    public OrderDto(Order order, Long offersAmount) {
        this(order);
        this.amountOfOffers = offersAmount;
    }

    public OrderDto(Order order, String driverName, Long approvedOfferId) {
        this(order);
        this.driverName = driverName;
        this.approvedOfferId = approvedOfferId;
    }

    public OrderDto(Order order, String driverName, String carPhoto) {
        this(order);
        this.driverName = driverName;
        this.carPhoto = carPhoto;
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.arrivalDate = order.getArrivalDate();
        this.locationFrom = order.getCityFrom() == null ? null : LocationDto.of(order.getCityFrom());
        this.locationTo = order.getCityTo() == null ? null : LocationDto.of(order.getCityTo());
        this.weight = order.getWeight();
        this.height = order.getHeight();
        this.width = order.getWidth();
        this.length = order.getLength();
        this.description = order.getDescription();

        User customer = order.getCustomer();
        if (customer != null) {
            this.customerName = customer.getFirstName() + " " + customer.getLastName();
        }
    }

    public OrderDto() {

    }

    public static OrderDto of(Order order) {
        return new OrderDto(order);
    }

    public static OrderDto orderAndFeedbackOf(Order order, FeedbackDto feedback) {
        return new OrderDto(order)
                .setFeedbackDto(feedback);
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

    public Long getApprovedOfferId() {
        return approvedOfferId;
    }

    public OrderDto setApprovedOfferId(Long offerId) {
        this.approvedOfferId = offerId;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public OrderDto setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getHeight() {
        return height;
    }

    public OrderDto setHeight(Double height) {
        this.height = height;
        return this;
    }

    public Double getWidth() {
        return width;
    }

    public OrderDto setWidth(Double width) {
        this.width = width;
        return this;
    }

    public Double getLength() {
        return length;
    }

    public OrderDto setLength(Double length) {
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

    public FeedbackDto getFeedbackDto() {
        return feedbackDto;
    }

    public OrderDto setFeedbackDto(FeedbackDto feedbackDto) {
        this.feedbackDto = feedbackDto;
        return this;
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
