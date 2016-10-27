package com.softserve.edu.delivery.dto;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.User;

import java.util.Objects;


public class OfferDtoForList {


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

    public OfferDtoForList(){}

    public static OfferDtoForList offerToOfferDto(Offer offer){
        OfferDtoForList offerDto = new OfferDtoForList();
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

    public OfferDtoForList setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public OfferDtoForList setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OfferDtoForList setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public OfferDtoForList setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OfferDtoForList setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getCarPhoto() {
        return carPhoto;
    }

    public OfferDtoForList setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
        return this;
    }

    public Integer getRate() {
        return rate;
    }

    public OfferDtoForList setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public OfferDtoForList setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OfferDtoForList setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OfferDtoForList setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferDtoForList offerDtoForList = (OfferDtoForList) o;
        return Objects.equals(offerId, offerDtoForList.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
