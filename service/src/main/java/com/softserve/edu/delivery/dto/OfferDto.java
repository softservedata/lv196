package com.softserve.edu.delivery.dto;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.User;

import java.util.Objects;


public class OfferDto {


    private Long offerId;
    private Long carId;
    private Long orderId;
    private boolean approved;
    private String driverName;
    private String carPhoto;
    private Integer rate;
    private String customerEmail;
    private String driverEmail;
    private String customerName;

    public OfferDto(){}

    public static OfferDto offerToOfferDto(Offer offer){
        OfferDto offerDto = new OfferDto();
        User customer = offer.getOrder().getCustomer();
        offerDto.setOfferId(offer.getOfferId())
                .setCarId(offer.getCar().getCarId())
                .setOrderId(offer.getOrder().getId())
                .setApproved(offer.isApproved())
                .setDriverEmail(offer.getCar().getDriver().getEmail())
                .setCustomerEmail(customer.getEmail())
                .setRate(offer.getCar().getDriver().getRate())
                .setCarPhoto(offer.getCar().getVehicleFrontPhotoURL())
                .setDriverName(offer.getCar().getDriver().getFirstName() + " " + offer.getCar().getDriver().getLastName())
                .setCustomerName(customer.getFirstName() + " " + customer.getLastName());
        return offerDto;
    }

    public Long getOfferId() {
        return offerId;
    }

    public OfferDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public OfferDto setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OfferDto setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public OfferDto setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OfferDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public OfferDto setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
        return this;
    }

    public Integer getRate() {
        return rate;
    }

    public OfferDto setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public OfferDto setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OfferDto setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OfferDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDto offerDto = (OfferDto) o;
        return Objects.equals(offerId, offerDto.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
