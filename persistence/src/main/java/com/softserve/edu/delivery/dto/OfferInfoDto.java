package com.softserve.edu.delivery.dto;


import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class OfferInfoDto {
    private Long offerId;
    private String driverEmail;
    private String driverName;
    private String customerEmail;
    private String customerName;
    private String cityNameFrom;
    private String cityNameTo;
    private Timestamp arrivalDate;

    public OfferInfoDto() {
    }

    public OfferInfoDto(Long offerId, User driver, User customer,
                        String cityNameFrom, String cityNameTo, Date arrivalDate) {
        this.offerId = offerId;
        this.driverEmail = driver.getEmail();
        this.driverName = driver.getFirstName() + " "  + driver.getLastName();
        this.customerEmail = customer.getEmail();
        this.customerName = customer.getFirstName() + " "  + customer.getLastName();
        this.cityNameFrom = cityNameFrom;
        this.cityNameTo = cityNameTo;
        this.arrivalDate = new Timestamp(arrivalDate.getTime());
    }

    public Long getOfferId() {
        return offerId;
    }

    public OfferInfoDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public OfferInfoDto setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public OfferInfoDto setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public OfferInfoDto setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OfferInfoDto setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCityNameFrom() {
        return cityNameFrom;
    }

    public OfferInfoDto setCityNameFrom(String cityNameFrom) {
        this.cityNameFrom = cityNameFrom;
        return this;
    }

    public String getCityNameTo() {
        return cityNameTo;
    }

    public OfferInfoDto setCityNameTo(String cityNameTo) {
        this.cityNameTo = cityNameTo;
        return this;
    }

    public Timestamp getArrivalDate() {
        return arrivalDate;
    }

    public OfferInfoDto setArrivalDate(Timestamp arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferInfoDto that = (OfferInfoDto) o;
        return Objects.equals(offerId, that.offerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId);
    }
}
