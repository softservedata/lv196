package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import java.util.Objects;


public class OfferDto {


    private Long offerId;
    private Car car;
    private Order order;
    private boolean approved;

    public OfferDto(){}

    public static OfferDto offerToOfferDto(Offer offer){
        OfferDto offerDto = new OfferDto();
        offerDto.setOfferId(offer.getOfferId())
                .setCar(offer.getCar())
                .setOrder(offer.getOrder())
                .setApproved(offer.isApproved());
        return offerDto;
    }

    public Long getOfferId() {
        return offerId;
    }
    public OfferDto setOfferId(Long offerId) {
        this.offerId = offerId;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public OfferDto setCar(Car car) {
        this.car = car;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OfferDto setOrder(Order order) {
        this.order = order;
        return this;
    }

    public boolean isApproved() {
        return approved;
    }

    public OfferDto setApproved(boolean approved) {
        this.approved = approved;
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
